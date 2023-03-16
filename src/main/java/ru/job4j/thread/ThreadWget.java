/**
 8. Скачивание файла с ограничением. [#144271]
 */

package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
/**
 * @author Sinkova D
 *
 */
public class ThreadWget implements Runnable {
    private final String url;
    private final int speed;
    public static final String FILE_NAME = "pom_tmp.xml";
    public ThreadWget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            int bytesRead;
            byte[] dataBuffer = new byte[1024];
            int downloadData = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (downloadData >= speed) {
                    long endTime = System.currentTimeMillis();
                    if (endTime - startTime < 1000) {
                        Thread.sleep(1000 - (endTime - startTime));
                    }
                    startTime = System.currentTimeMillis();
                    downloadData = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread wget = new Thread(new ThreadWget(args[0], Integer.parseInt(args[1])));
        wget.start();
        wget.join();
    }
}
