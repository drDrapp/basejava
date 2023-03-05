package ru.drdrapp.webapp.testdata;

public class TestThreadDeadLock {
    public static void main(String[] args) throws InterruptedException {
        Process process1 = new Process("Process1");
        Process process2 = new Process("Process2");
        Process process3 = new Process("Process3");

        Thread thread1 = new Thread(new SyncThread(process1, process2), "Thread1");
        Thread thread2 = new Thread(new SyncThread(process2, process3), "Thread2");
        Thread thread3 = new Thread(new SyncThread(process3, process1), "Thread3");

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(1000);
        thread3.start();

    }
}

class Process{
    public String Title;

    public Process(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return Title;
    }
}

class SyncThread implements Runnable{
    private Process process1;
    private Process process2;

    public SyncThread(Process p1, Process p2){
        this.process1 = p1;
        this.process2 = p2;
    }
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " try lock on " + process1);
        synchronized (process1) {
            System.out.println(name + " locked " + process1);
            work();
            System.out.println(name + " try to lock " + process2);
            synchronized (process2) {
                System.out.println(name + " locked " + process2);
                work();
            }
            System.out.println(name + " released lock on " + process2);
        }
        System.out.println(name + " released lock on " + process1);
        System.out.println(name + " finished execution.");
    }
    private void work() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}