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
    private Scanner scanner;
    String contents;
    private List<String> stringList;

    public Document(String fileName) throws IOException {
        this.file = new File(fileName);
        this.fileNameForTest = fileName;
    }

    @Override
    public void write(String contentToBeWritten){
        try {
            this.fileWriter = new FileWriter(getFile());
            this.fileWriter.write(contentToBeWritten);
            this.fileWriter.flush();
            this.fileWriter.close();
        }catch (IOException e){
            throw new Error(e);
        }
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten){
        List<String> writeToLine = toList();
        writeToLine.set(lineNumber, valueToBeWritten);
        String writtenLine = new String();
        for(String i:writeToLine){
           writtenLine+=i +'\n';
        }
        write(writtenLine);
    }

    @Override
    public String read(Integer lineNumber){
        return this.toList().get(lineNumber);
    }

    @Override
    public String read()  {
        try {
            this.scanner = new Scanner(this.file);
            this.contents = new String();
            while (scanner.hasNextLine()) {
                this.contents += scanner.nextLine();
                if(scanner.hasNextLine()) this.contents+='\n';
            }

        }catch (IOException e){
            throw new Error(e);
        }
               return this.contents;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString){
        String replacedContents = read().replaceAll(stringToReplace, replacementString);
        overWrite(replacedContents);
    }

    @Override
    public void overWrite(String content){
        try {
            FileWriter overWriter = new FileWriter(getFile(), false);
            overWriter.write(content);
            overWriter.flush();
            overWriter.close();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public List<String> toList(){
        this.stringList = Arrays.asList(read().split("\n"));
        return this.stringList;
    }

    @Override
    public File getFile(){
        return this.file;
    }

    @Override
    public String toString() {
     return fileNameForTest+'{'+read()+'}';
    }
}
