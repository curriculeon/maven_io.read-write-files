package com.github.curriculeon;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class NumericCharDocument extends Document {
    FileWriter fileWriter;
    public NumericCharDocument(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void write(String contentToBeWritten) {
        if(!isNumeric(contentToBeWritten)) {
            throw new IllegalArgumentException ("It should be numbers");
        }
        super.write(contentToBeWritten);
    }

    private Boolean isNumeric(String s) {
        for(int i=0; i< s.length(); i++){
            if(!Character.isDigit(s.charAt(i)) && !Character.isWhitespace(s.charAt(i)) ){
                return false;
            }
        }
        return true;
    }
}
