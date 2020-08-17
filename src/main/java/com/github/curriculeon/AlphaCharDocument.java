package com.github.curriculeon;

import java.io.File;
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
        super.write(contentToBeWritten);
    }

    private Boolean isAlpha(String s) {
        return null;
    }
}
