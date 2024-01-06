package github.data;

import com.github.javafaker.Faker;
import lombok.Data;

@Data
public class TestData {
    private final String
            baseUrl = System.getProperty("baseUrl","https://github.com/"),
            ownerName = System.getProperty("ownerName", "KorolevaEka"),
            repoUnderTest = System.getProperty("repoUnderTest", "KorolevaEka/HW9_faker"),
            searchRepo = System.getProperty("searchRepo", "KorolevaEka/HW9_faker"),
            searchResult = System.getProperty("searchResult", "KorolevaEka/HW9_faker"),
            advancedSearchLanguage = System.getProperty("advancedSearchLanguage", "Java"),
            advancedSearchFrom = System.getProperty("advancedSearchFrom", "KorolevaEka"),
            advancedSearchDate = System.getProperty("advancedSearchDate", "2023"),
            urlSearchAdvanced = baseUrl + "search/advanced",
            urlLoginPage = baseUrl + "login",
            targetFileName = "README.md",
            negativeTargetFileName = "invalid_file.txt",
            pageTitle = "GitHub: Let’s build from here · GitHub";

    private final Boolean autoInit = true;

    private final Faker faker = new Faker();
    private final String
            nameNewFile = faker.app().name() + ".txt",
            repositoryName = faker.pokemon().name(),
            newNameRepository = faker.pokemon().name(),
            descriptionRepository = faker.name().title(),
            message = faker.lordOfTheRings().character(),
            content = faker.chuckNorris().fact();
}