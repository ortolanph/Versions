package org.pho.versions;

public class MajorMinorBugfixVersion {

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

}
