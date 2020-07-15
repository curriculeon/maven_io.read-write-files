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
        if(!isSpecialCharacters(contentToBeWritten)){
            throw new IllegalArgumentException(contentToBeWritten);
        }
        super.write(contentToBeWritten);
    }

    private Boolean isSpecialCharacters(String s) {
        char[] text=s.toCharArray();
        for (int index=0; index<s.length(); index++) {
            if(Character.isDigit(text[index]) || Character.isLetter(text[index]) || Character.isWhitespace(text[index])){
                return false;
            }
        }
        return true;
    }
}
