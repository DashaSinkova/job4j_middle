/**
 8. Скачивание файла с ограничением. [#144271]
 */
package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    public static final int SIZE = 1024;
    private final long timeInMillis;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
        timeInMillis = SIZE / speed * 1000;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[SIZE];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, SIZE)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            if (time < timeInMillis) {
                Thread.sleep(timeInMillis - time);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Wget wget = new Wget("https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml", 80);
        wget.run();
    }
}
