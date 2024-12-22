package dev.logchange.cli;

import dev.logchange.commands.Constants;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogchangeVersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {

        InputStream resources = LogchangeVersionProvider.class.getResourceAsStream("/version.txt");

        if (resources == null) {
            return new String[]{"Cloud not read version from version.txt!"};
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resources))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }

        sb.append(Constants.BASIC_FOOTER);

        return new String[]{sb.toString()};

    }
}
