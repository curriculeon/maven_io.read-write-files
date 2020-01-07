package com.github.curriculeon.document;

import com.github.curriculeon.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author leon on 16/11/2018.
 */
public class DocumentReadLineTest {
       private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }

    @Test
    public void testReadFirstLine() throws IOException {
        // given
        String expected = "The";
        Document documentWriter = new Document(fileName);
        String contentToBeWritten = "The\nquick\nbrown\nfox";
        documentWriter.write(contentToBeWritten);

        // when
        String actual = documentWriter.read(0);

        // then
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testReadSecondLine() throws IOException {
        // given
        String expected = "quick";
        Document documentWriter = new Document(fileName);
        String contentToBeWritten = "The\nquick\nbrown\nfox";
        documentWriter.write(contentToBeWritten);

        // when
        String actual = documentWriter.read(1);

        // then
        Assert.assertEquals(expected, actual);
    }
}

