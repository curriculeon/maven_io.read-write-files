package com.github.curriculeon;

import javax.print.Doc;
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
        //this(new File(fileName));
        file = new File(fileName);
        fileWriter = new FileWriter(fileName);
        fileReader = new FileReader(fileName);

    }

    public Document(File file) throws IOException {
        this.file = file;
        this.fileWriter = new FileWriter(file.getPath());
        this.fileReader = new FileReader(file);
    }

    @Override
    public void write(String contentToBeWritten) throws IllegalArgumentException {

        try {
            fileWriter.write(contentToBeWritten);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNew(String contentToBeWritten) throws IllegalArgumentException {
        int index = 0;
        while (index < contentToBeWritten.length()) {
            if (Character.isDigit(contentToBeWritten.charAt(index)))
                throw new IllegalArgumentException("This is IllegalArgumentException");
            if (!Character.isLetterOrDigit(contentToBeWritten.charAt(index))) {
                if (contentToBeWritten.charAt(index) == ' ') {
                } else
                    throw new IllegalArgumentException("This is IllegalArgumentException");
            }
            index++;
        }

        try {
            fileWriter.write(contentToBeWritten);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNumbers(String valueToBeWritten)
    {
        int index = 0;
        while (index < valueToBeWritten.length()) {
            if (Character.isLetter(valueToBeWritten.charAt(index)))
                throw new IllegalArgumentException("This is IllegalArgumentException");
            if (!Character.isLetterOrDigit(valueToBeWritten.charAt(index))) {
                if (valueToBeWritten.charAt(index) == ' ') {
                } else
                    throw new IllegalArgumentException("This is IllegalArgumentException");
            }
            index++;
        }

        try {
            fileWriter.write(valueToBeWritten);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSpecialChars(String valueToBeWritten)
    {
        int index = 0;
        while (index < valueToBeWritten.length()) {

            if (Character.isLetterOrDigit(valueToBeWritten.charAt(index)) || (valueToBeWritten.charAt(index) == ' '))
                throw new IllegalArgumentException("This is IllegalArgumentException");
            index++;
        }

        try {
            fileWriter.write(valueToBeWritten);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) {

        String value = read();
        String newValue = "";
        int lineNum = 0;
        int startIndex = 0;
        int endIndex = value.indexOf("\n");

        String valueToReplace = value.substring(startIndex, endIndex);

        while (endIndex != -1 ) {
            if (lineNum == lineNumber) {
                newValue = value.replaceAll(valueToReplace, valueToBeWritten);
                break;
            }
            startIndex = endIndex + 1;
            endIndex += (value.substring(startIndex, value.length()).indexOf("\n") + 1);
            valueToReplace = value.substring(startIndex, endIndex);
            lineNum++;
        }
        if (endIndex == -1)
            value.replaceAll(value.substring(startIndex, value.length()), valueToBeWritten);

        write(newValue);
    }

    @Override
    public String read(Integer lineNumber) {

        String temp = read();
        String result;
        int lineNum = 0;
        int startIndex = 0;
        int endIndex = temp.indexOf("\n");

        while (lineNum != lineNumber){
            startIndex = endIndex + 1;
            endIndex += (temp.substring(startIndex).indexOf("\n") + 1);
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
                result += Character.toString((char)data);
                data = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) {
        String temp = read();
        String replacedString = temp.replaceAll(stringToReplace, replacementString);
        overWrite(replacedString);
    }

    @Override
    public void overWrite(String content) {
        try {
            new FileWriter(file.getPath(), false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        write(content);

    }

    public List<String> toList() {
        return null;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        String valueToBeRead = read();
        StringBuilder stringBuilder = new StringBuilder(file.getPath())
                .append("{")
                .append(valueToBeRead)
                .append("}");
        return stringBuilder.toString();
    }

    public void closeWriteStream()
    {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeReadStream()
    {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
