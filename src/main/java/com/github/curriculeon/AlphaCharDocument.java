package com.github.curriculeon;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class AlphaCharDocument extends Document {
    public AlphaCharDocument(String fileName) throws IOException {
        super(fileName);

    }


    @Override
    public void write(String contentToBeWritten) throws IOException  {
        if(!isAlpha(contentToBeWritten)){throw new IllegalArgumentException();}
        super.write(contentToBeWritten);
    }

    private Boolean isAlpha(String s) {

        return s.matches("[a-z][A-Z]+");
    }
}
