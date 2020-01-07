package com.github.curriculeon.alphadocument;

import com.github.curriculeon.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class AlphaDocumentWriteTest {
    private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeNumericValuesToFile() throws IOException {
        // given
        String contentToBeWritten = "123";
        Document documentWriter = new Document(fileName);

        // when
        documentWriter.write(contentToBeWritten);
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeSpecialCharacter() throws IOException {
        // given
        String contentToBeWritten = "()";
        Document documentWriter = new Document(fileName);

        // when
        documentWriter.write(contentToBeWritten);
    }


    @Test
    public void writeAlphaValuesTest() throws IOException {
        // given
        String expected = "The quick brown foxy";
        Document documentWriter = new Document(fileName);

        // when
        documentWriter.write(expected);
        String actual = documentWriter.read();

        // then
        Assert.assertEquals(expected, actual);
    }
}
