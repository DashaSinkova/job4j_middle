package ru.job4j.thread;

import java.util.List;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("main");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        user.setName("first");
                    }
                    System.out.println(cache.findById(1).getName());
                    List<User> users = cache.findAll();

                    users.get(0).setName("Имя в кеше не поменялось");
                    System.out.println(users);
                    System.out.println(cache.findAll().toString());
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        user.setName("second");
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
