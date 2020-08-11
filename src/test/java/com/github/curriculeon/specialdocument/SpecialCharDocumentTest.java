package com.github.curriculeon.specialdocument;

import com.github.curriculeon.Document;
import com.github.curriculeon.SpecialCharDocument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author leon on 18/11/2018.
 */
public class SpecialCharDocumentTest {
    private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }



    @Test(expected = IllegalArgumentException.class)
    public void writeNumericValuesToFile() throws IOException {
        // given
        String fileName = "target/file.txt";
        String contentToBeWritten = "123";
        Document documentWriter = new SpecialCharDocument(fileName);

        // when
        documentWriter.write(contentToBeWritten);

    }

    @Test
    public void writeSpecialCharacter1() throws IOException {
        // given
        String fileName = "target/file.txt";
        String contentToBeWritten = "()";
        Document documentWriter = new SpecialCharDocument(fileName);

        // when
        documentWriter.write(contentToBeWritten);
    }


    @Test
    public void writeSpecialCharacter2() throws IOException {
        // given
        String fileName = "target/file.txt";
        String contentToBeWritten = "()_*";
        Document documentWriter = new SpecialCharDocument(fileName);

        // when
        documentWriter.write(contentToBeWritten);
    }


    @Test(expected = IllegalArgumentException.class)
    public void writeAlphaValuesTest() throws IOException {
        // given
        String fileName = "target/file.txt";
        String content = "The quick brown foxy";
        Document documentWriter = new SpecialCharDocument(fileName);

        // when
        documentWriter.write(content);


    }
}
