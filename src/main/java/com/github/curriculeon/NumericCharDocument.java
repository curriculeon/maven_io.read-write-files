package com.github.curriculeon;

import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class NumericCharDocument extends Document {
    public NumericCharDocument(String fileName) throws IOException {
        super(fileName, fileReader);
    }

    @Override
    public void write(String contentToBeWritten) {
    }

    private Boolean isNumeric(String s) {
        return null;
    }
}
