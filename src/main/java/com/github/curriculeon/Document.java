package com.github.curriculeon;

import java.io.*;
import java.util.List;

/**
 * @author leon on 16/11/2018.
 */
public class Document implements DocumentInterface {

    private final FileWriter fileWriter;
    private final FileReader fileReader;
    private final File file;

    public Document(String fileName) throws IOException {
        this(new File(fileName));
    }

    public Document(File file) throws IOException {
        this.file = file;
        this.fileWriter = new FileWriter(file);
        this.fileReader = new FileReader(file);
    }

    @Override
    public void write(String contentToBeWritten) {
        try {
            fileWriter.write(contentToBeWritten);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) {

            int lineNum = 0;
            int startIndex = 0;
            int endIndex = valueToBeWritten.indexOf("\n");

            String valueToReplace = valueToBeWritten.substring(startIndex, endIndex);

            while (endIndex != -1 ) {
                if (lineNum == lineNumber) {
                    valueToBeWritten.replaceAll(valueToReplace, valueToBeWritten);
                    break;
                }
                startIndex = ++endIndex;
                endIndex = valueToBeWritten.indexOf("\n");
                lineNum++;
            }
            if (endIndex == -1)
                valueToBeWritten.replaceAll(valueToBeWritten.substring(startIndex, valueToBeWritten.length()), valueToBeWritten);

            write(valueToBeWritten);
    }

    @Override
    public String read(Integer lineNumber) {

        String temp = read();
        String result;
        int lineNum = 0;
        int startIndex = 0;
        int endIndex = temp.indexOf("\n");

        while (lineNum != lineNumber){
            startIndex = ++endIndex;
            endIndex = temp.indexOf("\n");
            lineNum++;
        }

        result = temp.substring(startIndex, endIndex);
        return result;
    }

    @Override
    public String read() {
        String result = "";

        try {
            int data = fileReader.read();
            while (data != -1)
            {
                result += Character.toString(Character.forDigit(data, 10));
                data = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) {
    }

    @Override
    public void overWrite(String content) {
    }

    public List<String> toList() {
        return null;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
