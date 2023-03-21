package ru.job4j.thread;

public class ThreadLocalDemo {
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread firstThread = new FirstThread();
        Thread secondThread = new SecondThread();
        threadLocal.set("main поток");
        System.out.println(threadLocal.get());

        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
    }
}

