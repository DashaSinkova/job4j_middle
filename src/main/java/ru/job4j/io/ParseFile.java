package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        WriteFile writeFile = new WriteFile(file);
        writeFile.saveContent(content);
    }

    public synchronized String content(Predicate<Character> filter) throws IOException {
        String output = "";
        ReadFile readFile = new ReadFile(file);
        output = readFile.content(filter);
        return output;
    }
}
