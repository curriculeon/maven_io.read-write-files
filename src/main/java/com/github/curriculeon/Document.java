package com.github.curriculeon;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author leon on 16/11/2018.
 */
public class Document implements DocumentInterface {

    private FileWriter fileWriter;
    private final String fileNameForTest;
    private final File file;
    private final Reader reader;
    private Scanner scanner;
    private String contents;
    private List<String> stringList;

//    public Document(String fileName) throws IOException {
//        this(new File(fileName));
//    }

    public Document(String fileName) throws IOException {
        this.file = new File(fileName);
        this.fileWriter = new FileWriter(getFile());
        this.reader = new FileReader(getFile());
        this.scanner = new Scanner(this.reader);
        this.fileNameForTest = fileName;
    }

    @Override
    public void write(String contentToBeWritten) throws IOException{
        this.fileWriter.write(contentToBeWritten);
        this.fileWriter.close();
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) throws IOException {
        toList().set(lineNumber, valueToBeWritten);
        this.fileWriter.write(toString());
        this.fileWriter.close();
    }

    @Override
    public String read(Integer lineNumber) throws IOException {
        return this.toList().get(lineNumber);
    }

    @Override
    public String read()  {
        this.contents = scanner.nextLine();
        while(scanner.hasNextLine()){
            this.contents += '\n'+ scanner.nextLine();
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new Error(e);
        }
        return this.contents;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) throws IOException {
        this.contents = read().replaceAll(stringToReplace, replacementString);
        this.fileWriter.write(this.contents);
        this.fileWriter.close();
    }

    @Override
    public void overWrite(String content) throws IOException {
        this.fileWriter = new FileWriter(getFile(), false);
        this.fileWriter.write(content);
        this.fileWriter.close();
    }

    public List<String> toList() throws IOException {
        this.stringList = Arrays.asList(read().split("\n"));
        return this.stringList;
    }

    @Override
    public File getFile() throws FileNotFoundException {
        return this.file;
    }

    @Override
    public String toString() {
        read();
     return fileNameForTest+'{'+this.contents+'}';
    }
}
