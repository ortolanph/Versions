package org.pho.versions;

import java.util.Objects;

public class MajorMinorVersion implements Comparable<MajorMinorVersion> {

    public static final String VERSION_SEPARATOR = ".";
    private static final int DEFAULT_INITIAL_MAJOR = 0;
    private static final int DEFAULT_INITIAL_MINOR = 0;
    private static final String VERSION_TEMPLATE = "%d.%d";

    private int major;
    private int minor;

    private MajorMinorVersion(int major, int minor) {
        this.major = major;
        this.minor = minor;
    }

    public static MajorMinorVersion newVersion() {
        return newVersion(DEFAULT_INITIAL_MAJOR, DEFAULT_INITIAL_MINOR);
    }

    public static MajorMinorVersion newVersion(int major) {
        return newVersion(major, DEFAULT_INITIAL_MINOR);
    }

    public static MajorMinorVersion newVersion(int major, int minor) {
        return new MajorMinorVersion(major, minor);
    }

    public static MajorMinorVersion parseFromText(String version) {
        String[] versionInfo = version.split("\\.");

        return newVersion(Integer.valueOf(versionInfo[0]),
                Integer.valueOf(versionInfo[1]));
    }

    public static MajorMinorVersion newMinorFromVersion(MajorMinorVersion oldVersion) {
        int major = oldVersion.getMajor();
        int minor = oldVersion.getMinor() + 1;

        return newVersion(major, minor);
    }

    public static MajorMinorVersion newMajorFromVersion(MajorMinorVersion oldVersion) {
        int major = oldVersion.getMajor() + 1;
        int minor = 0;

        return newVersion(major, minor);
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorMinorVersion that = (MajorMinorVersion) o;
        return major == that.major &&
                minor == that.minor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor);
    }

    @Override
    public String toString() {
        return String.format(VERSION_TEMPLATE, major, minor);
    }

    @Override
    public int compareTo(MajorMinorVersion other) {
        int majorCheck = major - other.major;
        int minorCheck = minor - other.minor;

        return (majorCheck == 0) ?
                (minorCheck == 0) ? 0 : minorCheck
                : majorCheck;
    }
}
