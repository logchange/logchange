package dev.logchange.maven_plugin.mojo.add;

import dev.logchange.commands.add.OutputFileNameProvider;
import org.codehaus.plexus.components.interactivity.Prompter;
import org.codehaus.plexus.components.interactivity.PrompterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MavenOutputFileNameProviderTest {

    private OutputFileNameProvider fileNameProvider;
    private MavenAddEntryPrompter prompter;

    @BeforeEach
    void setUp() {
        prompter = MavenAddEntryPrompter.of(new MockPrompter());
        fileNameProvider = new OutputFileNameProvider(false, prompter, null);
    }

    @Test
    void testGet_WithCommandLineOutputFileName() {
        // given
        String outputFileName = "test-file.yml";
        fileNameProvider = new OutputFileNameProvider(false, prompter, outputFileName);

        // when
        String result = fileNameProvider.get();

        // then
        assertEquals("test-file.yml", result);
    }

    @Test
    void testGet_WithEmptyFlag() {
        // given
        fileNameProvider = new OutputFileNameProvider(true, prompter, null);

        // then
        assertThrows(IllegalArgumentException.class, fileNameProvider::get);
    }

    @Test
    void testGet_WithPrompterException() throws PrompterException {
        // given
        prompter = MavenAddEntryPrompter.of(new MockPrompter(true));
        fileNameProvider = new OutputFileNameProvider(false, prompter, null);

        // then
        assertThrows(IllegalArgumentException.class, fileNameProvider::get);
    }

    @Test
    void testAdjust_WithSpecialCharactersAndExtensions() {
        // given
        String inputFileName = "te:st-fi.le.yml";
        String expectedOutputFileName = "te_st-fi_le.yml";

        // when
        String adjustedFileName = fileNameProvider.adjust(inputFileName);

        // then
        assertEquals(expectedOutputFileName, adjustedFileName);
    }

    @Test
    void testAdjust_WithSlashesAndExtensions() {
        // given
        String inputFileName = "path/to/my-file.yaml";
        String expectedOutputFileName = "path_to_my-file.yml";

        // when
        String adjustedFileName = fileNameProvider.adjust(inputFileName);

        // then
        assertEquals(expectedOutputFileName, adjustedFileName);
    }

    @Test
    void testGetOutputFileName_WithEmptyInput() {
        // given
        prompter = MavenAddEntryPrompter.of(new MockPrompter(false, "", "test-file"));
        fileNameProvider = new OutputFileNameProvider(false, prompter, null);

        // when
        String fileNameFromSecondInput = fileNameProvider.get();

        // then
        assertEquals("test-file.yml", fileNameFromSecondInput);
    }

    @Test
    void testGetOutputFileName_WithWhitespaceInput() {
        // given
        prompter = MavenAddEntryPrompter.of(new MockPrompter(false, " ", "test-file"));
        fileNameProvider = new OutputFileNameProvider(false, prompter, null);

        // when
        String fileNameFromSecondInput = fileNameProvider.get();

        // then
        assertEquals("test-file.yml", fileNameFromSecondInput);
    }

    private static class MockPrompter implements Prompter {

        private final boolean throwException;
        private final String firstUserInput;
        private final String secondUserInput;
        private boolean usedFirstUserInput;

        MockPrompter() {
            this.throwException = false;
            this.firstUserInput = "";
            this.usedFirstUserInput = false;
            this.secondUserInput = "";
        }

        MockPrompter(boolean throwException) {
            this.throwException = throwException;
            this.firstUserInput = "";
            this.usedFirstUserInput = false;
            this.secondUserInput = "";
        }

        MockPrompter(boolean throwException, String firstUserInput) {
            this.throwException = throwException;
            this.firstUserInput = firstUserInput;
            this.usedFirstUserInput = false;
            this.secondUserInput = "";
        }

        MockPrompter(boolean throwException, String firstUserInput, String secondUserInput) {
            this.throwException = throwException;
            this.firstUserInput = firstUserInput;
            this.usedFirstUserInput = false;
            this.secondUserInput = secondUserInput;
        }

        @Override
        public String prompt(String s) throws PrompterException {
            if (throwException) {
                throw new PrompterException("Mock Prompter Exception");
            }
            if (!usedFirstUserInput) {
                usedFirstUserInput = true;
                return firstUserInput;
            } else {
                return secondUserInput;
            }
        }

        @Override
        public String prompt(String s, String s1) throws PrompterException {
            return prompt(s);
        }

        @Override
        public String prompt(String s, List list) throws PrompterException {
            return prompt(s);
        }

        @Override
        public String prompt(String s, List list, String s1) throws PrompterException {
            return prompt(s);
        }

        @Override
        public String promptForPassword(String s) throws PrompterException {
            return prompt(s);
        }

        @Override
        public void showMessage(String s) {
            // No-op for testing
        }
    }
}
