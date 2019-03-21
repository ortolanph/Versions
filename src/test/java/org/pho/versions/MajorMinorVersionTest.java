package org.pho.versions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MajorMinorVersionTest {

    private static final MajorMinorVersion[] VERSION_LIST = new MajorMinorVersion[]{
            MajorMinorVersion.newVersion(1, 0),
            MajorMinorVersion.newVersion(2, 2),
            MajorMinorVersion.newVersion(7, 9),
            MajorMinorVersion.newVersion(2),
            MajorMinorVersion.newVersion(7),
            MajorMinorVersion.newVersion(6, 5),
            MajorMinorVersion.newVersion(6, 3),
            MajorMinorVersion.newVersion(5, 2),
            MajorMinorVersion.newVersion(4),
            MajorMinorVersion.newVersion(3, 11)
    };

    @Test
    public void shouldCreateMajorMinorVersionDefault() {
        MajorMinorVersion actual = MajorMinorVersion.newVersion();

        int expectedMajor = 0;
        int expectedMinor = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void shouldCreateMajorMinorVersionWithoutMinor() {
        MajorMinorVersion actual = MajorMinorVersion.newVersion(1);

        int expectedMajor = 1;
        int expectedMinor = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void shouldCompleteCreateMajorMinorVersion() {
        MajorMinorVersion actual = MajorMinorVersion.newVersion(1, 2);

        int expectedMajor = 1;
        int expectedMinor = 2;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void shoulParseVersion() {
        MajorMinorVersion actual = MajorMinorVersion.parseFromText("7.3");

        int expectedMajor = 7;
        int expectedMinor = 3;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void shouldNotParseVersionWithLetters() {
        Assertions.assertThrows(Exception.class, () -> MajorMinorVersion.parseFromText("7.a"));
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
    public void shouldParseVersion() {
        MajorMinorVersion actual = MajorMinorVersion.parseFromText("7.3");

        int expectedMajor = 7;
        int expectedMinor = 3;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void shouldIncrementMinorVersion() {
        MajorMinorVersion oldVersion = MajorMinorVersion.newVersion(5, 0);
        MajorMinorVersion actual = MajorMinorVersion.newMinorFromVersion(oldVersion);

        int expectedMajor = 5;
        int expectedMinor = 1;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void shouldIncrementMajorVersion() {
        MajorMinorVersion oldVersion = MajorMinorVersion.newVersion(5, 7);
        MajorMinorVersion actual = MajorMinorVersion.newMajorFromVersion(oldVersion);

        int expectedMajor = 6;
        int expectedMinor = 0;

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMajor, actual.getMajor()),
                () -> Assertions.assertEquals(expectedMinor, actual.getMinor())
        );
    }

    @Test
    public void versionsShouldBeTheSame() {
        MajorMinorVersion version1 = MajorMinorVersion.newVersion(1, 0);
        MajorMinorVersion version2 = MajorMinorVersion.newVersion(1, 0);

        int expected = 0;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void laterVersionByMajor() {
        MajorMinorVersion version1 = MajorMinorVersion.newVersion(2, 0);
        MajorMinorVersion version2 = MajorMinorVersion.newVersion(1, 0);

        int expected = 1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void previousVersionByMajor() {
        MajorMinorVersion version1 = MajorMinorVersion.newVersion(1, 0);
        MajorMinorVersion version2 = MajorMinorVersion.newVersion(2, 0);

        int expected = -1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void laterVersionByMinor() {
        MajorMinorVersion version1 = MajorMinorVersion.newVersion(1, 1);
        MajorMinorVersion version2 = MajorMinorVersion.newVersion(1, 0);

        int expected = 1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void previousVersionByMinor() {
        MajorMinorVersion version1 = MajorMinorVersion.newVersion(1, 0);
        MajorMinorVersion version2 = MajorMinorVersion.newVersion(1, 1);

        int expected = -1;

        Assertions.assertEquals(expected, version1.compareTo(version2));
    }

    @Test
    public void listsShouldBeEquals() {
        MajorMinorVersion[] orderedVersionList = new MajorMinorVersion[]{
                MajorMinorVersion.newVersion(1, 0),
                MajorMinorVersion.newVersion(2),
                MajorMinorVersion.newVersion(2, 2),
                MajorMinorVersion.newVersion(3, 11),
                MajorMinorVersion.newVersion(4),
                MajorMinorVersion.newVersion(5, 2),
                MajorMinorVersion.newVersion(6, 3),
                MajorMinorVersion.newVersion(6, 5),
                MajorMinorVersion.newVersion(7),
                MajorMinorVersion.newVersion(7, 9),
        };

        List<MajorMinorVersion> actual = Arrays.asList(VERSION_LIST).stream().sorted().collect(Collectors.toList());
        List<MajorMinorVersion> expected = Arrays.asList(orderedVersionList);

        Assertions.assertEquals(expected, actual);
    }
}