package com.github.curriculeon;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author leon on 16/11/2018.
 */
public interface DocumentInterface {
    void write(String contentToBeWritten) throws IOException;

    void write(Integer lineNumber, String valueToBeWritten);

    String read(Integer lineNumber);

    String read();

    void replaceAll(String stringToReplace, String replacementString);

    void overWrite(String content);

    List<String> toList();

    File getFile();

    @Override
    String toString();
}
