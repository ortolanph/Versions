package org.pho.versions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MajorMinorBugfixVersionTest {

    @Test
    public void shouldCrateDefaultMajorMinorBugfixVersion() {
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newVersion();

        int expectedMajor = 0;
        int expectedMinor = 0;
        int expectedBugfix = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void shouldCreateNewMajor() {
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newVersion(1);

        int expectedMajor = 1;
        int expectedMinor = 0;
        int expectedBugfix = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void shouldCreateNewMajorMinor() {
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newVersion(1, 2);

        int expectedMajor = 1;
        int expectedMinor = 2;
        int expectedBugfix = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void shouldCreateNewMajorMinorBugfix() {
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newVersion(1, 2, 10);

        int expectedMajor = 1;
        int expectedMinor = 2;
        int expectedBugfix = 10;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void parseText() {
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.parseFromText("1.2.10");

        int expectedMajor = 1;
        int expectedMinor = 2;
        int expectedBugfix = 10;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void shouldNotParseVersionWithLetters() {
        Assertions.assertThrows(Exception.class, () ->MajorMinorVersion.parseFromText("7.a.$"));
    }

    @Test
    public void shouldNotParseVersionWithMajorOnly() {
        Assertions.assertThrows(Exception.class, () ->MajorMinorVersion.parseFromText("7"));
    }

    @Test
    public void shouldNotParseVersionWithMinorOnly() {
        Assertions.assertThrows(Exception.class, () ->MajorMinorVersion.parseFromText(".3"));
    }

    @Test
    public void shouldNotParseVersionWithBugfixOnly() {
        Assertions.assertThrows(Exception.class, () ->MajorMinorVersion.parseFromText("..3"));
    }

    @Test
    public void newMajorFromVersion() {
        MajorMinorBugfixVersion old = MajorMinorBugfixVersion.newVersion(1, 2,3);
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newMajorFromVersion(old);

        int expectedMajor = 2;
        int expectedMinor = 0;
        int expectedBugfix = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void newMinorFromVersion() {
        MajorMinorBugfixVersion old = MajorMinorBugfixVersion.newVersion(1, 2,3);
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newMinorFromVersion(old);

        int expectedMajor = 1;
        int expectedMinor = 3;
        int expectedBugfix = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }

    @Test
    public void newBugfixFromVersion() {
        MajorMinorBugfixVersion old = MajorMinorBugfixVersion.newVersion(1, 2,3);
        MajorMinorBugfixVersion actual = MajorMinorBugfixVersion.newBugfixFromVersion(old);

        int expectedMajor = 1;
        int expectedMinor = 2;
        int expectedBugfix = 4;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor()),
                () -> Assertions.assertEquals(expectedBugfix, actual.getBugfix())
        );
    }
}