package com.github.curriculeon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
        this.fileWriter.write(contentToBeWritten);
        fileWriter.flush();


    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) throws IOException {
        List<String> temp = this.toList();
        temp.set(lineNumber,valueToBeWritten);
        this.write(temp.toString());
    }

    @Override
    public String read(Integer lineNumber) throws IOException {

        return this.toList().get(lineNumber);
    }

    @Override
    public String read() throws IOException {
        StringBuilder fileReader = new StringBuilder();

        Files.lines(Paths.get(file.getPath())).forEach(s -> fileReader.append(s).append("\n"));
        return fileReader.toString().trim();
    }


    @Override
    public void replaceAll(String stringToReplace, String replacementString) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
        content = content.replaceAll(stringToReplace, replacementString);
        Files.write(Paths.get(file.getPath()), content.getBytes());
    }

    @Override
    public void overWrite(String content) throws IOException {
        this.write(content);

    }

    public List<String> toList() throws IOException {
        return Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        String s = "";
        try {
             s = read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString() + s;
    }
}
