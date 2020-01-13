package com.github.curriculeon;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

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

    //    public static void main(String[] args) throws IOException {
//    Document doc=new Document("target/file.txt");
//    doc.write("Hello \nWorld");
//    String text=doc.read(0);
//    System.out.println(text);
//        doc.write(1,"MadWorld");
// }
    @Override
    public void write(String contentToBeWritten) throws IOException {
        fileWriter.write(contentToBeWritten);
        fileWriter.flush();

    }

    @Override
    public void write(Integer lineNumber, String valueToBeWritten) throws IOException {
        List<String> list=this.toList();
        list.set(lineNumber,valueToBeWritten);
        this.overWrite(listToString(list));
    }

    @Override
    public String read(Integer lineNumber) throws IOException {
        return this.toList().get(lineNumber);

    }

    @Override
    public String read() throws IOException {
        return listToString(toList());
    }

    public String listToString(List<String> list) {
        StringBuilder result=new StringBuilder();
        int count=0;
        for (String str: list) {
            count++;
            result.append(str);
            if(count!=list.size()){
                result.append("\n");
            }

        }
        return result.toString();
    }

    @Override
    public void replaceAll(String stringToReplace, String replacementString) throws IOException {
        String text=listToString(toList()).replaceAll(stringToReplace, replacementString);
        overWrite(text);
    }

    @Override
    public void overWrite(String content) throws IOException {
        FileWriter overWriter=new FileWriter(file,false);
        overWriter.write(content);
        overWriter.flush();
        overWriter.close();

    }

    public List<String> toList() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String> contentList    = new ArrayList<>();
        while ( scanner.hasNextLine() ) {
            contentList.add(scanner.nextLine());
        }
        scanner.close();
        return contentList ;
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public String toString() {

        try {
            return "target/"+file.getName()+"{"+read()+"}";
        } catch (IOException e) {
            e.printStackTrace();
        }
return null;
    }
}
