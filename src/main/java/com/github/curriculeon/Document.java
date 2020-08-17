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
    public void write(Integer lineNumber, String valueToBeWritten) throws IOException {
        String result = toList().set(lineNumber,valueToBeWritten);
        this.write(result);
    }

    @Override
    public String read(Integer lineNumber) {
        return this.toList().get(lineNumber);
    }

    @Override
    public String read() {
        StringBuffer content = new StringBuffer();
        try {
            FileInputStream fs = new FileInputStream(file);
            while (true) {
                int value = 0;

                value = fs.read();

                if (value == -1) {
                    break;
                }
                content.append((char) value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = content.toString();
        return result;
    }
        
    @Override
    public void replaceAll(String stringToReplace, String replacementString) throws IOException {
        String result = read().replaceAll(stringToReplace, replacementString);
        fileWriter.write(result);
        fileWriter.close();
    }

    @Override
    public void overWrite(String content) throws IOException {
        this.write(content);
    }

    public List<String> toList() {
        List<String> result = null;
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
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
