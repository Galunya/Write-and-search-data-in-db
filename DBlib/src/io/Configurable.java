package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configurable {

    protected Properties settings;

    public Configurable(File file) throws FileNotFoundException, IOException {
        settings = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parse(line.trim());

            }
        }

    }

    private boolean isComment(String line) {
        return line.charAt(0) == '#';
    }

    private void parse(String line) {
        if (!line.isEmpty() && !isComment(line)) {
            String[] parts = line.split("=");
            if(parts.length>1){
                settings.put(parts[0].trim(), parts[1].trim());
            }
        }
    }
}
