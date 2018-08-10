package mars.advanced.ThreadPool;

public class ThreadT {

    public static void main(String [] args) {

        try {
//            MyThread thread = new MyThread();
//            thread.start();
//            Thread.sleep(10);
//            thread.interrupt();

            final Synchronized syn = new Synchronized();
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    syn.test();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    syn.test1();
                }
            });

            thread1.start();
            thread2.start();

        } catch (Exception e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");

    }

}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50000; i++) {
            System.out.println("i=" + (i + 1));
            if (interrupted()) {
                System.out.println("已经是停止状态了!我要退出了!");
                //可以直接抛出异常中断进程
//                throw new InterruptedException();
                break;
            }
        }
    }
}

class Synchronized {

    public synchronized void test() {
        try {
            Thread.sleep(5000);
            System.out.println("test");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void test1() {
        System.out.println("test1");
    }

}
