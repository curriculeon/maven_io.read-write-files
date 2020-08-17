package com.github.curriculeon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author leon on 16/11/2018.
 */
public class Document implements DocumentInterface {

    private final FileWriter fileWriter;
    private final File file;

    public Document(String fileName) throws IOException {
        this(new File(fileName));
    }

    public Document(File file) throws IOException {
        this.file = file;
        this.fileWriter = new FileWriter(file);
    }

    @Override
    public void write(String contentToBeWritten) throws IOException {
        fileWriter.write(contentToBeWritten);
        fileWriter.flush();
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) {
    }

    @Override
    public String read(Integer lineNumber) {

        return null;
    }

    @Override
    public String read() {
        StringBuffer content = new StringBuffer();
        FileInputStream fs = new FileInputStream(file);
        while (true){
            int value = 0;
            try {
                value = fs.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(value == -1){
                break;
            }
            content.append((char)value);
        }
        String result = content.toString();
        return result;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) {
    }

    @Override
    public void overWrite(String content) {
        this.write(content);
    }

    public List<String> toList() {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
