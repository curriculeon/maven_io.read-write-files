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
public class DocumentToStringTest {
    private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }


    @Test
    public void toStringTest1() throws IOException {
        // given
        String contentToBeWritten = "The quick brown fox";
        Document documentWriter = new Document(fileName);
        String expected = new StringBuilder(fileName)
                .append("{")
                .append(contentToBeWritten)
                .append("}")
                .toString();

        // when
        documentWriter.write(contentToBeWritten);
        String actual = documentWriter.toString();

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toStringTest2() throws IOException {
        // given
        String contentToBeWritten = "The quicker browner fox";
        Document documentWriter = new Document(fileName);
        String expected = new StringBuilder(fileName)
                .append("{")
                .append(contentToBeWritten)
                .append("}")
                .toString();

        // when
        documentWriter.write(contentToBeWritten);
        String actual = documentWriter.toString();

        // then
        Assert.assertEquals(expected, actual);
    }
}
