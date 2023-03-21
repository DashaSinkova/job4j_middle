package ru.job4j.thread;

public class FirstThread extends Thread {
    @Override
    public void run() {
        ThreadLocalDemo.threadLocal.set("1 поток");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }
}
