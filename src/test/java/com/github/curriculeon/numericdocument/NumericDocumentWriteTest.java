package com.github.curriculeon.numericdocument;

import com.github.curriculeon.Document;
import com.github.curriculeon.NumericCharDocument;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class NumericDocumentWriteTest {
    private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }



    @Test
    public void writeNumericValuesToFile() throws IOException {
        // given
        String contentToBeWritten = "123";
        Document documentWriter = new Document(fileName);

        // when
        documentWriter.writeNumbers(contentToBeWritten);
        String actual = documentWriter.read();

        //then
        Assert.assertEquals(contentToBeWritten, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeSpecialCharacter() throws IOException {
        // given
        String contentToBeWritten = "()";
        Document documentWriter = new Document(fileName);

        // when
        documentWriter.writeNumbers(contentToBeWritten);
    }

    @Test(expected = IllegalArgumentException.class)
    public void writeAlphaValuesTest() throws IOException {
        // given
        String expected = "The quick brown foxy";
        Document documentWriter = new Document(fileName);

        // when
        documentWriter.writeNumbers(expected);
        String actual = documentWriter.read();

        // then
        Assert.assertEquals(expected, actual);
    }

}
