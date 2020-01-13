package com.github.curriculeon;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class AlphaCharDocument extends Document {
    FileWriter fileWriter;
    public AlphaCharDocument(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void write(String contentToBeWritten) {
        if(!isAlpha(contentToBeWritten)) {
            throw new IllegalArgumentException ("It should be letters");
        }
        super.write(contentToBeWritten);
    }

    private Boolean isAlpha(String s) {
        for(int i=0; i< s.length(); i++){
         if(!Character.isLetter(s.charAt(i)) && !Character.isWhitespace(s.charAt(i))){
             return false;
            }
        }
        return true;
    }
}
