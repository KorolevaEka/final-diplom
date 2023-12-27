package github.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AssertScreenShootUtil {

    public void takeAndCompareScreenshots(SelenideElement element, String expectedScreenshotPath, String actualScreenshotPath, String diffImagePath) throws IOException {
        BufferedImage expectedImage = ImageIO.read(new File(expectedScreenshotPath));
        BufferedImage actualImage = takeScreenshot(element, actualScreenshotPath);
        compareImagesAndAssert(expectedImage, actualImage, diffImagePath);
    }

    private BufferedImage takeScreenshot(SelenideElement element, String actualScreenshotPath) throws IOException {
        Screenshot actualScreenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(Selenide.webdriver().driver().getWebDriver(), element);

        ImageIO.write(actualScreenshot.getImage(), "PNG", new File(actualScreenshotPath));
        return ImageIO.read(new File(actualScreenshotPath));
    }

    private void compareImagesAndAssert(BufferedImage img1, BufferedImage img2, String diffImagePath) throws IOException {
        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff diff = imageDiffer.makeDiff(img1, img2);

        if (diff.hasDiff()) {
            ImageIO.write(diff.getMarkedImage(), "PNG", new File(diffImagePath));
            Assertions.fail("The images are not identical");
        }
    }
}