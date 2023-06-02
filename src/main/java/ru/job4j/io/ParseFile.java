package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized void saveContent(String content) throws IOException {
        WriteFile writeFile = new WriteFile(file);
        writeFile.saveContent(content);
    }

    public synchronized String getContent() throws IOException {
        ReadFile readFile = new ReadFile(file);
        return readFile.content(character -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        ReadFile readFile = new ReadFile(file);
        return readFile.content(character -> character < 0x80);
    }
}
