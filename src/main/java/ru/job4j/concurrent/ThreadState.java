package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {}
        );
        System.out.println(first.getState() + " " + first.getName());

        Thread second = new Thread(
                () -> {}
        );
        System.out.println(second.getState() + " " + second.getName());

        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState() + " " + first.getName());
            System.out.println(second.getState() + " " + second.getName());
        }

        if (second.getState() == Thread.State.TERMINATED & first.getState() == Thread.State.TERMINATED) {
            System.out.println(Thread.currentThread().getName() + " Работа завершена");
        }
    }
}
