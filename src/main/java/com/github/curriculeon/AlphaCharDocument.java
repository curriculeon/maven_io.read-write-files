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
    public void write(String contentToBeWritten) {
        if(isAlpha(contentToBeWritten)) {
            super.write(contentToBeWritten);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private Boolean isAlpha(String s) {
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z' || ch == ' ')) {
                return false;
            }
        }
        return true;
    }
}
