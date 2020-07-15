package com.github.curriculeon;

import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class NumericCharDocument extends Document {
    public NumericCharDocument(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void write(String contentToBeWritten) throws IOException {
        if(!isNumeric(contentToBeWritten)){
            throw new IllegalArgumentException(contentToBeWritten);
        }
        super.write(contentToBeWritten);
    }

    private Boolean isNumeric(String s) {
        char[] text=s.toCharArray();
        for (int index=0; index<s.length(); index++) {
            if(Character.isDigit(text[index])){
                return true;
            }
        }
        return false;
    }
}
