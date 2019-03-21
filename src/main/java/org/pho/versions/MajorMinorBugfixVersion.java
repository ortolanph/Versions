package org.pho.versions;

import java.util.Objects;

public class MajorMinorBugfixVersion implements Comparable<MajorMinorBugfixVersion> {

    public static final String VERSION_SEPARATOR = ".";
    private static final int DEFAULT_INITIAL_MAJOR = 0;
    private static final int DEFAULT_INITIAL_MINOR = 0;
    private static final int DEFAULT_INITIAL_BUGFIX = 0;
    private static final String VERSION_TEMPLATE = "%d.%d.%d";

    private int major;
    private int minor;
    private int bugfix;

    private MajorMinorBugfixVersion(int major, int minor, int bugfix) {
        this.major = major;
        this.minor = minor;
        this.bugfix = bugfix;
    }

    public static MajorMinorBugfixVersion newVersion() {
        return new MajorMinorBugfixVersion(DEFAULT_INITIAL_MAJOR, DEFAULT_INITIAL_MINOR, DEFAULT_INITIAL_BUGFIX);
    }

    public static MajorMinorBugfixVersion newVersion(int major) {
        return new MajorMinorBugfixVersion(major, DEFAULT_INITIAL_MINOR, DEFAULT_INITIAL_BUGFIX);
    }

    public static MajorMinorBugfixVersion newVersion(int major, int minor) {
        return new MajorMinorBugfixVersion(major, minor, DEFAULT_INITIAL_BUGFIX);
    }

    public static MajorMinorBugfixVersion newVersion(int major, int minor, int bugfix) {
        return new MajorMinorBugfixVersion(major, minor, bugfix);
    }

    public static MajorMinorBugfixVersion parseFromText(String version) {
        String[] versionInfo = version.split("\\.");

        return newVersion(Integer.valueOf(versionInfo[0]),
                Integer.valueOf(versionInfo[1]),
                Integer.valueOf(versionInfo[2]));
    }

    public static MajorMinorBugfixVersion newMajorFromVersion(MajorMinorBugfixVersion version) {
        return newVersion(version.major + 1, DEFAULT_INITIAL_MINOR, DEFAULT_INITIAL_BUGFIX);
    }

    public static MajorMinorBugfixVersion newMinorFromVersion(MajorMinorBugfixVersion version) {
        return newVersion(version.major, version.minor + 1, DEFAULT_INITIAL_BUGFIX);
    }

    public static MajorMinorBugfixVersion newBugfixFromVersion(MajorMinorBugfixVersion version) {
        return newVersion(version.major, version.minor, version.bugfix + 1);
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getBugfix() {
        return bugfix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorMinorBugfixVersion that = (MajorMinorBugfixVersion) o;
        return major == that.major &&
                minor == that.minor &&
                bugfix == that.bugfix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, bugfix);
    }

    @Override
    public String toString() {
        return String.format(VERSION_TEMPLATE, major, minor, bugfix);
    }

    @Override
    public int compareTo(MajorMinorBugfixVersion other) {
        int majorCheck = major - other.major;
        int minorCheck = minor - other.minor;
        int bugfixCheck = bugfix - other.bugfix;

        return (majorCheck == 0) ?
                (minorCheck == 0) ?
                        (bugfixCheck == 0) ? 0 : bugfixCheck
                        : minorCheck
                : majorCheck;
    }
}
