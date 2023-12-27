package github.utils;

import io.restassured.response.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ApiZipFileManager {

    public String readFileContentFromZip(Response apiResponse, String fileName) throws IOException {

        byte[] zipContent = apiResponse.asByteArray();
        try (ByteArrayInputStream bais = new ByteArrayInputStream(zipContent);
             ZipInputStream zis = new ZipInputStream(bais)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(fileName)) {
                    byte[] fileBytes = zis.readAllBytes();
                    return new String(fileBytes, StandardCharsets.UTF_8);
                }
            }
        }

        return "File " + fileName + " not found in ZIP archive";
    }
}