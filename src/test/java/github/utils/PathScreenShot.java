package github.utils;

import com.codeborne.selenide.Configuration;

public class PathScreenShot {

    private static final String SCREENSHOT_PATH = "src/test/resources/screenshottest/";

    public String getExpectedScreenshotPath(String screenshotName) {
        if (Configuration.remote != null && !Configuration.remote.isEmpty()) {
            return SCREENSHOT_PATH + "remote_" + screenshotName + ".png";
        } else {
            return SCREENSHOT_PATH + "local_" + screenshotName + ".png";
        }
    }

    public String getActualScreenshotPath(String screenshotName) {
        return SCREENSHOT_PATH + "actual_" + screenshotName + ".png";
    }

    public String getDiffImagePath(String screenshotName) {
        return SCREENSHOT_PATH + "diff_" + screenshotName + ".png";
    }
}