package mars.spring.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZookeeperTest {

    //单一节点
    public static final String zkServerConnect = "localhost:2181";

    //超时毫秒数
    public static final int timeout = 30000;

    @Test
    public void test() {
        try {
            //建立连接
            ZooKeeper zk = connect();
            //zk.close();//关闭后不支持重连
            log.info("zk 状态："+zk.getState());

            /**恢复会话连接**/
            //long sessionId = zk.getSessionId();
            //byte[] sessionPasswd = zk.getSessionPasswd();
            //zk2会话重连后，zk会话将失效，不再支持做增删改查操作。
            //ZooKeeper zk2 = reconnect(sessionId, sessionPasswd);

            /**创建节点**/
//            create(zk, "/coupon/a", "myzk");

            /**查询节点Data**/
            queryData(zk, "/coupon/a");

            /**修改节点data**/
            update(zk, "/coupon/a", "myzk-update");

            /**修改节点data**/
            update(zk, "/coupon/a", "myzk-update-1");

            /**删除节点**/
            //delete(zk, "/myzk");

            System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ZooKeeper connect() throws IOException, InterruptedException{
        CountDownLatch cdl = new CountDownLatch(1);
        log.info("准备建立zk服务");
        ZooKeeper zk = new ZooKeeper(zkServerConnect, timeout, new MyZkWatcher(cdl,"建立连接"));
        log.info("完成建立zk服务");
        cdl.await();//这里为了等待wather监听事件结束
        return zk;
    }

    public static ZooKeeper reconnect(long sessionId, byte[] sessionPasswd) throws IOException, InterruptedException{
        CountDownLatch cdl = new CountDownLatch(1);
        log.info("准备重新连接zk服务");
        ZooKeeper zk = new ZooKeeper(zkServerConnect, timeout, new MyZkWatcher(cdl,"重新连接"), sessionId, sessionPasswd);
        log.info("完成重新连接zk服务");
        cdl.await();//这里为了等待wather监听事件结束
        return zk;
    }

    public static void create(ZooKeeper zk,String nodePath,String nodeData) throws KeeperException, InterruptedException{
        log.info("开始创建节点：{}， 数据：{}",nodePath,nodeData);
        List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        CreateMode createMode = CreateMode.PERSISTENT;
        String result = zk.create(nodePath, nodeData.getBytes(), acl, createMode);
        //创建节点有两种，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
        log.info("创建节点返回结果：{}",result);
        log.info("完成创建节点：{}， 数据：{}",nodePath,nodeData);
    }

    public static Stat queryStat(ZooKeeper zk, String nodePath) throws KeeperException, InterruptedException{
        log.info("准备查询节点Stat，path：{}", nodePath);
        Stat stat = zk.exists(nodePath, false);
        log.info("结束查询节点Stat，path：{}，version：{}", nodePath, stat.getVersion());
        return stat;
    }

    public static String queryData(ZooKeeper zk,String nodePath) throws KeeperException, InterruptedException{
        log.info("准备查询节点Data,path：{}", nodePath);
        CountDownLatch cdl = new CountDownLatch(1);
        Watcher watcher = new MyZkWatcher(cdl,"查询数据");
        String data = new String(zk.getData(nodePath, watcher, queryStat(zk, nodePath)));
        log.info("结束查询节点Data,path：{}，Data：{}", nodePath, data);
        return data;
    }

    public static Stat update(ZooKeeper zk,String nodePath,String nodeData) throws KeeperException, InterruptedException{
        //修改节点前先查询该节点信息
        Stat stat = queryStat(zk, nodePath);
        log.info("准备修改节点，path：{}，data：{}", nodePath, nodeData);
        Stat newStat = zk.setData(nodePath, nodeData.getBytes(), stat.getVersion());
        //修改节点值有两种方法，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
        //zk.setData(path, data, version, cb, ctx);
        log.info("完成修改节点，path：{}，data：{}", nodePath, nodeData);
        return stat;
    }

    public static void delete(ZooKeeper zk,String nodePath) throws InterruptedException, KeeperException{
        //删除节点前先查询该节点信息
        Stat stat = queryStat(zk, nodePath);
        log.info("准备删除节点，path：{}，原version：{}", nodePath, stat.getVersion());
        zk.delete(nodePath, stat.getVersion());
        //修改节点值有两种方法，上面是第一种，还有一种可以使用回调函数及参数传递，与上面方法名称相同。
        //zk.delete(path, version, cb, ctx);
        log.info("完成删除节点，path：{}", nodePath);
    }

}
