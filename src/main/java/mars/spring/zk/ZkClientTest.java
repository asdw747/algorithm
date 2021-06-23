package mars.spring.zk;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

@Slf4j
public class ZkClientTest {

    //zookeeper地址。集群时使用逗号分隔
    private static final String zkServers = "localhost:2181";

    @Test
    public void test() {
        try {
            //创建会话
            ZkClient zkClient = new ZkClient(zkServers, 30000);

            //创建节点
            String path = "/coupon/b";
            String data = "hello";
//            zkClient.create(path, data, CreateMode.PERSISTENT);

            log.info("是否存在mynode节点： {}", zkClient.exists(path));
            //列出根下所有节点
            log.info("根下拥有的子节点： {}", zkClient.getChildren("/"));

            //读取节点的数据
            log.info(zkClient.readData(path));

            zkClient.subscribeChildChanges(path, new MyZkWatcher("child listener"));

            //修改节点数据
            zkClient.writeData(path, "world");
            log.info(zkClient.readData(path));
            zkClient.create(path + "/" + System.currentTimeMillis(), data, CreateMode.PERSISTENT);

            //修改节点数据
            zkClient.writeData(path, "world1");
            log.info(zkClient.readData(path));
            zkClient.create(path + "/" + System.currentTimeMillis(), data, CreateMode.PERSISTENT);

            //删除节点
//            zkClient.delete(path);
//            log.info("删除节点后：" + zkClient.getChildren("/"));

            //关闭客户端连接
            Thread.sleep(10000);
            zkClient.close();
        } catch (Exception e) {
            log.error("error.", e);
        }

    }

}
