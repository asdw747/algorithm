package mars.spring.zk;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class MyZkWatcher implements Watcher, IZkChildListener {

    //异步锁
    private CountDownLatch cdl;
    //标记
    private String mark;

    public MyZkWatcher(String mark) {
        this.mark = mark;
    }

    public MyZkWatcher(CountDownLatch cdl,String mark) {
        this.cdl = cdl;
        this.mark = mark;
    }

    //监听事件处理方法
    public void process(WatchedEvent event) {
        log.info(mark+" watcher监听事件：{}",event);
        cdl.countDown();
    }

    @Override
    public void handleChildChange(String s, List<String> list) throws Exception {
        log.info(mark+" child change监听事件：{}, {}", s, list);
    }
}
