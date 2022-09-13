package com.github.curriculeon;

import java.io.IOException;

/**
 * @author leon on 18/11/2018.
 */
public class SpecialCharDocument extends Document {
    public SpecialCharDocument(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void write(String contentToBeWritten) throws IOException {
        if(!isSpecialCharacters(contentToBeWritten))
            throw new IllegalArgumentException();
        super.write(contentToBeWritten);
    }

    private Boolean isSpecialCharacters(String s) {

        return s.matches("[" + "-/@#!*$%^&.'_+={}()"+ "]+");
    }
}
