package com.study.tdd.chap05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

public class TempDirTest {

    //모든 테스트가 같이 사용하는 경우
    @TempDir
    static File staticTempFile;

    @TempDir
    static Path staticTempPath;

    //각 테스트가 별도로 사용하는 경우
    @TempDir
    File eachTestTempFile;

    @TempDir
    Path eachTestTempPath;

    @Test
    void tempFile() {
        //static temp
        Assertions.assertTrue(staticTempFile.exists());
        Assertions.assertTrue(staticTempPath.isAbsolute());

        //each test temp
        Assertions.assertTrue(eachTestTempFile.exists());
        Assertions.assertTrue(eachTestTempPath.isAbsolute());
    }

    @Test
    void tempFileInOnlyThisTestMethod(@TempDir File methodTempFile, @TempDir Path methodTempPath) {
        //static temp
        Assertions.assertTrue(staticTempFile.exists());
        Assertions.assertTrue(staticTempPath.isAbsolute());

        //each test temp
        Assertions.assertTrue(eachTestTempFile.exists());
        Assertions.assertTrue(eachTestTempPath.isAbsolute());

        //this test temp
        Assertions.assertTrue(methodTempFile.exists());
        Assertions.assertTrue(methodTempPath.isAbsolute());
    }
}
