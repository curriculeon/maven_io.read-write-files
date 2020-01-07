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
    public void write(String contentToBeWritten) {
        try {
            fileWriter.write(contentToBeWritten);
            fileWriter.flush();
            //fileWriter.close();
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
    }

    @Override
    public void overWrite(String content) {
        getFile().delete();
        //write(content);
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
        return null;
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

   public static void main(String [] args)
    {
        String s = "The quick brown fox";
        try {
            Document document = new Document("target/file.txt");
            document.write(s);
            document.overWrite(s);
            document.write("test");
            //System.out.println(document.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
