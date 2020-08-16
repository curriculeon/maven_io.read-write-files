package com.github.curriculeon;

import java.io.*;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
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
        this.fileWriter = new FileWriter(file, false);
    }

    @Override
    public void write(String contentToBeWritten) throws IOException {
        this.file.createNewFile();
        System.out.println(contentToBeWritten);
        this.fileWriter.write(contentToBeWritten); //Adding the file contents to buffer
        fileWriter.flush();     // Moving the contents of the buffer to destination and makes the buffer empty
    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) throws IOException {
        //creating a list and storing the values in the list based on line number and values
        List<String> temp = this.toList();
        temp.set(lineNumber,valueToBeWritten);
        this.write(temp.toString());
        //writing the values in the line numbers
        //fileWriter.flush();
        //fileWriter.close();
    }

    @Override
    public String read(Integer lineNumber) throws IOException
    {
        //return null;
        return this.toList().get(lineNumber);  //reading the file and putting values in the list of the given line number

    }

    @Override
    public String read() throws IOException
    {
        //return null;
        StringBuilder fileReader = new StringBuilder(); //FileReader created
     //Getting the path and appending s and new line char
        Files.lines(Paths.get(file.getPath())).forEach(s -> fileReader.append(s).append("\n"));
        return fileReader.toString().trim(); //trimming extra space
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) throws IOException{
        //taking the file contents from specified path and putting in content
        String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
        //replacing the contents of the file based on the passed strings
        content = content.replaceAll(stringToReplace, replacementString);
        //writing the file on that particular path
        Files.write(Paths.get(file.getPath()), content.getBytes());
    }

    @Override
    public void overWrite(String content) throws IOException {
        this.write(content);
    }

    public List<String> toList() throws IOException
    {
        //return null;
        //returning all the file contents into the collection list
        return Files.lines(Paths.get(file.getPath())).collect(Collectors.toList());
    }

    @Override
    public File getFile()
    {
        //return null;
        return file;
    }

    @Override
    public String toString() {
       // return null;
        String s = "";
        try {
            s = read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString() + s;
    }
}
