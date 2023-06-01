package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Predicate;

public class ReadFile {
    private final File file;

    public ReadFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) throws IOException {
        String output = "";
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            int data = 0;
            while ((data = in.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        }
        return output;
    }
}
