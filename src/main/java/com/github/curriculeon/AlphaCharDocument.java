package com.github.curriculeon;

import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class AlphaCharDocument extends Document {
    public AlphaCharDocument(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void write(String contentToBeWritten) throws IOException {
        if(!isAlpha(contentToBeWritten)){
            throw new IllegalArgumentException(contentToBeWritten);
        }
        super.write(contentToBeWritten);
    }


    private Boolean isAlpha(String s) {
        char[] text=s.toCharArray();
        for (int index=0; index<s.length(); index++) {
            if(Character.isDigit(text[index]) || (!Character.isLetter(text[index]) && !Character.isWhitespace(text[index]))){
                return false;
            }
        }
        return true;
    }
}
