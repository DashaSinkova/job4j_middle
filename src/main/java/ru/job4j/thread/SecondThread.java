package ru.job4j.thread;

public class SecondThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.threadLocal.set("2 поток");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
