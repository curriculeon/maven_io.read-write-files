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
public class DocumentWriteToLineTest {
    private String fileName;

    @Before
    public void setup() {
        this.fileName = "target/file.txt";
        new File(fileName).delete();
    }


    @Test
    public void writeToLineTest1() throws IOException {
        // given
        String contentToBeWritten = "The\nquick\nbrown\nfox";
        String replacement = "quicker";
        String expected = contentToBeWritten.replaceAll("quick", replacement);

        Document documentWriter = new Document(fileName);
        documentWriter.write(contentToBeWritten);

        // when
        documentWriter.write(1, replacement);
        documentWriter.closeWriteStream();

        // then
        Assert.assertEquals(expected, documentWriter.read());
        documentWriter.closeReadStream();
    }

    @Test
    public void writeToLineTest2() throws IOException {
        // given
        String contentToBeWritten = "The\nquick\nbrown\nfox";
        String replacement = "quickest";
        String expected = contentToBeWritten.replaceAll("The", replacement);

        Document documentWriter = new Document(fileName);
        documentWriter.write(contentToBeWritten);

        // when
        documentWriter.write(0, replacement);
        documentWriter.closeWriteStream();

        // then
        Assert.assertEquals(expected, documentWriter.read());
        documentWriter.closeReadStream();
    }
}