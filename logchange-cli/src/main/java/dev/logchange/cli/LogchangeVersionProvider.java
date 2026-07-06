package dev.logchange.cli;

import dev.logchange.commands.Constants;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LogchangeVersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() {
        return new String[]{getVersionInfo() + Constants.BASIC_FOOTER};
    }

    public static String getVersionInfo() {
        InputStream resources = LogchangeVersionProvider.class.getResourceAsStream("/version.txt");

        if (resources == null) {
            return "Cloud not read version from version.txt!";
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resources))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Cloud not read version from version.txt!";
        }

        return sb.toString().trim();
    }
}
