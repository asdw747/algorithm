package mars.advanced.thread;

import org.junit.Test;

public class ObjectLockCase {

    @Test
    public void testWait() {
        ObjectLockCase objectLockCase1 = new ObjectLockCase();
        try {
            objectLockCase1.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWaitAndNotify() {
        System.out.println("begin");
        ObjectLockCase objectLockCase1 = new ObjectLockCase();

        Thread thread1 = new Thread(()-> {
            try {
                synchronized (objectLockCase1) {
                    System.out.println("1 get lock");
                    objectLockCase1.wait();
                    System.out.println("1 ok");
                }
            } catch (InterruptedException e) {
                System.out.println("1 error");
                e.printStackTrace();
            }
        });
        thread1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(()-> {
            try {
                synchronized (objectLockCase1) {
                    System.out.println("2 get lock");
                    objectLockCase1.notify();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("2 ok");
                }
            } catch (Exception e) {
                System.out.println("2 error");
                e.printStackTrace();
            }
        });
        thread2.start();


        //
        for(int i=0; i<1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }

    @Test
    public void testWaitTimeAndNotify() {
        System.out.println("begin");
        ObjectLockCase objectLockCase1 = new ObjectLockCase();

        Thread thread1 = new Thread(()-> {
            try {
                synchronized (objectLockCase1) {
                    System.out.println("1 get lock");
                    objectLockCase1.wait(1000);
                    System.out.println("1 ok");
                }
            } catch (InterruptedException e) {
                System.out.println("1 error");
                e.printStackTrace();
            }
        });
        thread1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(()-> {
            try {
                synchronized (objectLockCase1) {
                    System.out.println("2 get lock");
                    objectLockCase1.notify();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("2 ok");
                }
            } catch (Exception e) {
                System.out.println("2 error");
                e.printStackTrace();
            }
        });
        thread2.start();


        //
        for(int i=0; i<1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }

}
