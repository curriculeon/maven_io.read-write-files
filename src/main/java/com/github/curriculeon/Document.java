package com.github.curriculeon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        this.fileWriter = new FileWriter(file,false);
    }

    @Override
    public void write(String contentToBeWritten) {
        try {
            fileWriter.write(contentToBeWritten);
            fileWriter.flush(); //IOE Exception: stream closed when call the next write(), use flush() instead
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) {
        try {
            List<String> myList = this.toList();
            myList.set(lineNumber, valueToBeWritten); //replace/overwrite the value in list of string
            String string = "";
            for(String s:myList){
                string = string + s + "\n";
            }
            this.overWrite(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(Integer lineNumber) {
        try {
            return this.toList().get(lineNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String read() {
        String string = "";
        try {
            List<String> myList = this.toList();
            for(String s:myList){
                string = string + s + "\n";
            }
            return string.substring(0,string.length()-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) {
        this.overWrite(this.read().replaceAll(stringToReplace,replacementString));
    }

    @Override
    public void overWrite(String content) {
        FileWriter outputStream = null;
        try {
            outputStream = new FileWriter(file);
            outputStream.write(content);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> toList() throws IOException{
        List<String> myList = new ArrayList<>();
        FileReader inputStream = null;
        try {
            inputStream = new FileReader(file);
            BufferedReader bufferStream = new BufferedReader(inputStream);
            String l;
            while((l = bufferStream.readLine()) != null){
                myList.add(l);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                inputStream.close();
            }
        }
        return myList;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return file.toString() + "{" + read() + "}";
    }
}
