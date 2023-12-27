package github.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UiZipProcessor {

    public File processZipFile(File inputZip) throws IOException {

        String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();
        String uniqueFileName = "JavaFiles_" + sessionId + ".txt";
        File outputFile = new File(uniqueFileName);
        outputFile.createNewFile();

        try (InputStream is = new FileInputStream(inputZip);
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".java")) {
                    byte[] fileBytes = zis.readAllBytes();
                    Files.write(outputFile.toPath(), fileBytes, StandardOpenOption.APPEND);
                }
            }

            return outputFile;
        }
    }
}