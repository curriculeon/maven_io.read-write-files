package com.github.curriculeon;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author leon on 18/11/2018.
 */
public class SpecialCharDocument extends Document {
    FileWriter fileWriter;
    public SpecialCharDocument(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void write(String contentToBeWritten) {
            if(!isSpecialCharacters(contentToBeWritten)) {
               throw new IllegalArgumentException ("It should be special characters");
            }
            super.write(contentToBeWritten);
    }

    private Boolean isSpecialCharacters(String s) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9\\s]");
        Matcher matcher = pattern.matcher(s);
        boolean bool = matcher.find();
        if(!bool) {
            return false;
        }
        return true;
    }
}
