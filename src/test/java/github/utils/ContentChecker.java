package github.utils;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Data
public class ContentChecker {

    private final File file;
    private final List<String> stringsToCheck;
    private final List<String> foundStrings;


    public ContentChecker(File file, List<String> stringsToCheck) {
        this.file = file;
        this.stringsToCheck = stringsToCheck;
        this.foundStrings = new ArrayList<>();
    }

    public void assertThatAllFound() throws IOException {
        performContentCheck();

        assertThat(foundStrings).containsExactlyInAnyOrderElementsOf(stringsToCheck);
    }

    private void performContentCheck() throws IOException {
        String fileContents = Files.readString(file.toPath());

        for (String item : stringsToCheck) {
            if (fileContents.contains(item)) {
                foundStrings.add(item);
            } else {
                System.out.println("Ошибка: Строка " + item + " не найдена в файле");
            }
        }
        System.out.println("Найдены строки: " + foundStrings);
    }
}