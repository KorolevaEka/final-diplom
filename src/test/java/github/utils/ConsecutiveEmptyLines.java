package github.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConsecutiveEmptyLines {

    public boolean assertEmptyLines(File file, Integer threshold) throws IOException {
        int count = 0;
        boolean problems = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    count++;
                } else {
                    count = 0;

                }
                if (count > threshold) {
                    problems = false;
                    break;
                }
            }
            if (!problems) System.out.println("Number of empty lines in a row: " + count);
            return problems;
        }
    }
}