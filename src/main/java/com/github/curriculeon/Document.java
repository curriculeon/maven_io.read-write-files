package com.github.curriculeon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leon on 16/11/2018.
 */
public class Document implements DocumentInterface {

    private final FileWriter fileWriter;

    public FileWriter getFileWriter() {
        return fileWriter;
    }

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
        try {
            this.fileWriter.write(contentToBeWritten);
        } catch (IOException e) {
            System.out.println("Filewriter can't write to " + file);
        } finally {
            fileWriter.flush();
        }
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) {
    }

    @Override
    public String read(Integer lineNumber) throws IOException {
  List<String> list=this.toList();
        return list.get(lineNumber);//Originally used buffered reader, but I decided to go with the list method
    }

    @Override
    public String read() throws IOException {
        FileReader fileReader = new FileReader(file);
        try {
            char[] chars = new char[(int) file.length()];
            fileReader.read(chars);
            return String.valueOf(chars);
        } catch (IOException e) {
            System.out.println("Filewriter can't write to " + file);
        }
        return null;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
        content = content.replaceAll(stringToReplace, replacementString);
        Files.write(Paths.get(file.getPath()), content.getBytes());
    }

    @Override
    public void overWrite(String content) throws IOException {

        replaceAll(this.read(), content);
    }


    public List<String> toList() throws IOException {
        return Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());  //Got from Chris Fulton
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public String toString() {
        try {
            return file.toString()+"{"+this.read()+"}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

