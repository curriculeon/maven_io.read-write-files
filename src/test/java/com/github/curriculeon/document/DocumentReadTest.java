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
public class DocumentReadTest {
    private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }

    @Test
    public void testRead1() throws IOException {
        // given
        Document documentWriter = new Document(fileName);
        String expected = "The\nquick\nbrown\nfox";
        documentWriter.write(expected);

        // when
        String actual = documentWriter.read();

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRead2() throws IOException {
        // given
        Document documentWriter = new Document(fileName);
        String expected = "The\nquicker\nbrown\nfox";
        documentWriter.write(expected);

        // when
        String actual = documentWriter.read();

        // then
        Assert.assertEquals(expected, actual);
    }

}