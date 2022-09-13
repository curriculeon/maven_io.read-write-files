package com.github.curriculeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author leon on 16/11/2018.
 */
public interface DocumentInterface {
    void write(String contentToBeWritten) throws IOException;

    void write(Integer lineNumber, String valueToBeWritten) throws IOException;

    String read(Integer lineNumber) throws IOException;

    String read() throws IOException;

    void replaceAll(String stringToReplace, String replacementString) throws IOException;

    void overWrite(String content) throws IOException;

    List<String> toList() throws IOException;

    File getFile();

    @Override
    String toString();
}
