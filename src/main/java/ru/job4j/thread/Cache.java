package ru.job4j.thread;

public class Cache {
    private static Cache cache;

    public static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            Cache.instOf();
            System.out.println(Cache.cache.hashCode());
        });
        Thread thread2 = new Thread(() -> {
            Cache.instOf();
            System.out.println(Cache.cache.hashCode());
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println();
    }
}
