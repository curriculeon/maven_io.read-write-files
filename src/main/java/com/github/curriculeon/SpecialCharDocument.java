package com.github.curriculeon;

import java.io.IOException;

/**
 * @author leon on 18/11/2018.
 */
public class SpecialCharDocument extends Document {
    public SpecialCharDocument(String fileName) throws IOException {
        super(fileName, fileReader);
    }

    @Override
    public void write(String contentToBeWritten) {
    }

    private Boolean isSpecialCharacters(String s) {
        return null;
    }
}
