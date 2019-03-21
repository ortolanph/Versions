package org.pho.versions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MajorMinorBugfixVersionTest {

    private static final MajorMinorBugfixVersion[] VERSION_LIST = new MajorMinorBugfixVersion[]{
            MajorMinorBugfixVersion.newVersion(1, 0, 0),
            MajorMinorBugfixVersion.newVersion(2, 2),
            MajorMinorBugfixVersion.newVersion(7, 9, 2),
            MajorMinorBugfixVersion.newVersion(2),
            MajorMinorBugfixVersion.newVersion(7),
            MajorMinorBugfixVersion.newVersion(6, 5, 4),
            MajorMinorBugfixVersion.newVersion(6, 3, 5),
            MajorMinorBugfixVersion.newVersion(5, 2, 6),
            MajorMinorBugfixVersion.newVersion(4),
            MajorMinorBugfixVersion.newVersion(3, 11, 1123)
    };

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
        Assertions.assertThrows(Exception.class, () -> MajorMinorVersion.parseFromText("7.a.$"));
    }

    @Test
    public void shouldNotParseVersionWithMajorOnly() {
        Assertions.assertThrows(Exception.class, () -> MajorMinorVersion.parseFromText("7"));
    }

    @Test
    public void shouldNotParseVersionWithMinorOnly() {
        Assertions.assertThrows(Exception.class, () -> MajorMinorVersion.parseFromText(".3"));
    }

    @Test
    public void shouldNotParseVersionWithBugfixOnly() {
        Assertions.assertThrows(Exception.class, () -> MajorMinorVersion.parseFromText("..3"));
    }

    @Test
    public void newMajorFromVersion() {
        MajorMinorBugfixVersion old = MajorMinorBugfixVersion.newVersion(1, 2, 3);
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
        MajorMinorBugfixVersion old = MajorMinorBugfixVersion.newVersion(1, 2, 3);
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
        MajorMinorBugfixVersion old = MajorMinorBugfixVersion.newVersion(1, 2, 3);
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

    @Test
    public void sameVersion() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(1, 2, 3);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(1, 2, 3);

        int expected = 0;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void majorGreater() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(2, 2, 3);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(1, 2, 3);

        int expected = 1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void majorLower() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(1, 2, 3);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(2, 2, 3);

        int expected = -1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void minorGreater() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(1, 3, 3);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(1, 2, 3);

        int expected = 1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void minorLower() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(1, 2, 3);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(1, 3, 3);

        int expected = -1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void BugfixGreater() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(1, 2, 4);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(1, 2, 3);

        int expected = 1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void bugfixLower() {
        MajorMinorBugfixVersion version1 = MajorMinorBugfixVersion.newVersion(1, 2, 3);
        MajorMinorBugfixVersion version2 = MajorMinorBugfixVersion.newVersion(1, 2, 4);

        int expected = -1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void listsShouldBeEquals() {
        MajorMinorBugfixVersion[] orderedVersionList = new MajorMinorBugfixVersion[]{
                MajorMinorBugfixVersion.newVersion(1, 0, 0),
                MajorMinorBugfixVersion.newVersion(2),
                MajorMinorBugfixVersion.newVersion(2, 2),
                MajorMinorBugfixVersion.newVersion(3, 11, 1123),
                MajorMinorBugfixVersion.newVersion(4),
                MajorMinorBugfixVersion.newVersion(5, 2, 6),
                MajorMinorBugfixVersion.newVersion(6, 3, 5),
                MajorMinorBugfixVersion.newVersion(6, 5, 4),
                MajorMinorBugfixVersion.newVersion(7),
                MajorMinorBugfixVersion.newVersion(7, 9, 2)
        };

        List<MajorMinorBugfixVersion> actual = Arrays.asList(VERSION_LIST).stream().sorted().collect(Collectors.toList());
        List<MajorMinorBugfixVersion> expected = Arrays.asList(orderedVersionList);

        Assertions.assertEquals(expected, actual);
    }
}