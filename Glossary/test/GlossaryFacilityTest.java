import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * JUnit test fixture for GlossaryFacility.
 */
public class GlossaryFacilityTest {

//    Sort Method

    /**
     * Test sort with basic alphabet order.
     */
    @Test
    public void testSortBasic() {
        Sequence<String> test = new Sequence1L<>();
        test.add(0, "zinc");
        test.add(1, "cat");
        test.add(2, "axe");

        Sequence<String> expected = new Sequence1L<>();
        expected.add(0, "axe");
        expected.add(1, "cat");
        expected.add(2, "zinc");

        GlossaryFacility.sort(test);
        assertEquals(expected, test);
    }

    /**
     * Test sort with case insensitive ordering.
     */
    @Test
    public void testSortCaseInsensitive() {
        Sequence<String> test = new Sequence1L<>();
        test.add(0, "Zebra");
        test.add(1, "apple");
        test.add(2, "Cat");

        Sequence<String> expected = new Sequence1L<>();
        expected.add(0, "apple");
        expected.add(1, "Cat");
        expected.add(2, "Zebra");

        GlossaryFacility.sort(test);
        assertEquals(expected, test);
    }

    /**
     * Tests a already ordered list.
     */
    @Test
    public void testSortCaseOrdered() {
        Sequence<String> test = new Sequence1L<>();
        test.add(0, "Apple");
        test.add(1, "biscuit");
        test.add(2, "girl");
        test.add(3, "xray");

        Sequence<String> expected = new Sequence1L<>();
        expected.add(0, "Apple");
        expected.add(1, "biscuit");
        expected.add(2, "girl");
        expected.add(3, "xray");

        GlossaryFacility.sort(test);
        assertEquals(expected, test);
    }

    /**
     * Tests a already ordered list.
     */
    @Test
    public void testSortCaseMinTerms() {
        Sequence<String> test = new Sequence1L<>();
        test.add(0, "biscuit");
        test.add(1, "Apple");

        Sequence<String> expected = new Sequence1L<>();
        expected.add(0, "Apple");
        expected.add(1, "biscuit");

        GlossaryFacility.sort(test);
        assertEquals(expected, test);
    }

//    LinesFromInput Method

    /**
     * Test linesFromInput with single term/definition.
     */
    @Test
    public void testLinesFromInputBasic() {
        // Create test file
        SimpleWriter testWriter = new SimpleWriter1L("test_input.txt");
        testWriter.println("word");
        testWriter.println("a unit of text");
        testWriter.println("");
        testWriter.close();

        Map<String, String> result = new Map1L<>();
        GlossaryFacility.linesFromInput("test_input.txt", result);

        assertEquals("a unit of text", result.value("word"));
        assertEquals(1, result.size());
    }

    /**
     * Test linesFromInput with multiple terms/definitions.
     */
    @Test
    public void testLinesFromInputMultiple() {
        SimpleWriter testWriter = new SimpleWriter1L("test_multi.txt");
        testWriter.println("word");
        testWriter.println("first definition");
        testWriter.println("");
        testWriter.println("text");
        testWriter.println("second definition");
        testWriter.println("");
        testWriter.close();

        Map<String, String> result = new Map1L<>();
        GlossaryFacility.linesFromInput("test_multi.txt", result);

        assertEquals("first definition", result.value("word"));
        assertEquals("second definition", result.value("text"));
        assertEquals(2, result.size());
    }

    /**
     * Test linesFromInput with long terms.
     */
    @Test
    public void testLinesFromInputLongWords() {
        SimpleWriter testWriter = new SimpleWriter1L("test_multi.txt");
        testWriter.println("pneumonoultramicroscopicsilicovolcanoconiosis");
        testWriter.println(
                "A lung disease caused by inhaling very fine silicate or quartz dust, especially from a volcano");
        testWriter.println("");
        testWriter.println("Electroencephalographically");
        testWriter.println(
                "relating to the technique of recording and interpreting the electrical activity of the brain");
        testWriter.println("");
        testWriter.println("Floccinaucinihilipilification");
        testWriter.println("the action or habit of estimating something as worthless");
        testWriter.println("");
        testWriter.close();

        Map<String, String> result = new Map1L<>();
        GlossaryFacility.linesFromInput("test_multi.txt", result);

        assertEquals(
                "A lung disease caused by inhaling very fine silicate or quartz dust, especially from a volcano",
                result.value("pneumonoultramicroscopicsilicovolcanoconiosis"));
        assertEquals(
                "relating to the technique of recording and interpreting the electrical activity of the brain",
                result.value("Electroencephalographically"));
        assertEquals("the action or habit of estimating something as worthless",
                result.value("Floccinaucinihilipilification"));
        assertEquals(3, result.size());
    }

//    addDefinitionLinks

    /**
     * Test addDefinitionLinks with single term match.
     */
    @Test
    public void testAddDefinitionLinksBasic() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word", "definition");

        String testDef = "A word is important";
        String expected = "A <a href=\"word.html\">word</a> is important";

        String result = GlossaryFacility.addDefinitionLinks(testDef, glossary);
        assertEquals(expected, result);
    }

    /**
     * definition does consist another word with anther html.
     */
    @Test
    public void testAddDefinitionLinksWithNoWord() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word", "definition1");
        glossary.add("text", "definition2");

        String testDef = "Each letter in the sentence is a term";
        String expected = "Each letter in the sentence is a term";

        String result = GlossaryFacility.addDefinitionLinks(testDef, glossary);
        assertEquals(expected, result);
    }

    /**
     * Test addDefinitionLinks with multiple term matches.
     */
    @Test
    public void testAddDefinitionLinksMultiple() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word", "definition1");
        glossary.add("text", "definition2");

        String testDef = "Each word in the text is a word";
        String expected = "Each <a href=\"word.html\">word</a> in the "
                + "<a href=\"text.html\">text</a> is a "
                + "<a href=\"word.html\">word</a>";

        String result = GlossaryFacility.addDefinitionLinks(testDef, glossary);
        assertEquals(expected, result);
    }

//    HtmlTermPage

    /**
     * Test htmlTermPage generates correct HTML structure.
     */
    @Test
    public void testHtmlTermPage() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word", "a unit of text");
        glossary.add("text", "written content");

        SimpleWriter testOut = new SimpleWriter1L("test_term.html");
        GlossaryFacility.htmlTermPage("word", "a unit of text", testOut, glossary);

        SimpleReader reader = new SimpleReader1L("test_term.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("<title>word</title>"));
        assertTrue(htmlContent.contains("<font color=\"red\">word</font>"));
        assertTrue(htmlContent.contains("href=\"text.html\">text</a>"));
        assertTrue(htmlContent.contains("<a href=\"index.html\">index</a>"));
    }

    /**
     * Tests HTML term page with definition containing other glossary terms.
     */
    @Test
    public void testHtmlTermPageWithLinks() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word", "a basic unit of text");
        glossary.add("text", "written content");

        SimpleWriter out = new SimpleWriter1L("test_term_links.html");
        GlossaryFacility.htmlTermPage("word", "a basic unit of text", out, glossary);

        SimpleReader reader = new SimpleReader1L("test_term_links.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("<title>word</title>"));
        assertTrue(htmlContent.contains("<font color=\"red\">word</font>"));
        assertTrue(htmlContent.contains("<a href=\"text.html\">text</a>"));
    }

    /**
     * Tests HTML term page with multiline definition.
     */
    @Test
    public void testHtmlTermPageMultiline() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word", "first line\nsecond line");

        SimpleWriter out = new SimpleWriter1L("test_term_multiline.html");
        GlossaryFacility.htmlTermPage("word", "first line\nsecond line", out, glossary);

        SimpleReader reader = new SimpleReader1L("test_term_multiline.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("first line"));
        assertTrue(htmlContent.contains("second line"));
    }

    /**
     * Hyperlinks if the word is also in the term from glossary.
     */
    @Test
    public void testAddDefinitionLinksHyperlink() {
        // Set up test data
        Map<String, String> glossary = new Map1L<>();
        glossary.add("word1", "The definition of word1 has word2 definition");
        glossary.add("word2", "The definition of word2");

        // Test string containing terms that should be linked
        String definition = "The definition of word1 has word2 definition";

        // Get the processed definition with links
        String result = GlossaryFacility.addDefinitionLinks(definition, glossary);

        assertTrue(result.contains("<a href=\"word1.html\">word1</a>"));
        assertTrue(result.contains("<a href=\"word2.html\">word2</a>"));
    }

    /**
     * Tests HTML term page with special characters in term or definition.
     */
    @Test
    public void testHtmlTermPageSpecialChars() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("term-one", "definition with & and <>");

        SimpleWriter out = new SimpleWriter1L("test_term_special.html");
        GlossaryFacility.htmlTermPage("term-one", "definition with & and <>", out,
                glossary);

        SimpleReader reader = new SimpleReader1L("test_term_special.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("<title>term-one</title>"));
        assertTrue(htmlContent.contains("definition with & and <>"));
    }

    /**
     * Tests HTML term page formatting (red, bold, italic).
     */
    @Test
    public void testHtmlTermPageFormatting() {
        Map<String, String> glossary = new Map1L<>();
        glossary.add("test", "definition");

        SimpleWriter out = new SimpleWriter1L("test_term_format.html");
        GlossaryFacility.htmlTermPage("test", "definition", out, glossary);

        SimpleReader reader = new SimpleReader1L("test_term_format.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent
                .contains("<h2><b><i><font color=\"red\">test</font></i></b></h2>"));
    }

//    htmlIndexPage

    /**
     * Tests the basic functionality of htmlIndexPage method.
     */
    @Test
    public void testHtmlIndexPage() {
        // Set up the test data before calling the method
        Sequence<String> words = new Sequence1L<String>();
        Sequence<String> termLink = new Sequence1L<String>();

        // Add terms and links first
        words.add(0, "term");
        words.add(1, "word");
        words.add(2, "book");

        termLink.add(0, "term.html");
        termLink.add(1, "word.html");
        termLink.add(2, "book.html");

        // Create output file and call method
        SimpleWriter out = new SimpleWriter1L("test_index.html");
        GlossaryFacility.htmlIndexPage(words, out, termLink);
        out.close();

        // Read the generated file
        SimpleReader reader = new SimpleReader1L("test_index.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        // Check for correct content
        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("<title>Glossary</title>"));
        assertTrue(htmlContent.contains("<h2>Glossary</h2>"));
        assertTrue(htmlContent.contains("<a href=\"term.html\">term</a>"));
        assertTrue(htmlContent.contains("<a href=\"word.html\">word</a>"));
        assertTrue(htmlContent.contains("<a href=\"book.html\">book</a>"));
    }

    /**
     * Tests htmlIndexPage with empty sequences.
     */
    @Test
    public void testHtmlIndexPageEmpty() {
        Sequence<String> words = new Sequence1L<String>();
        Sequence<String> termLink = new Sequence1L<String>();

        SimpleWriter out = new SimpleWriter1L("test_empty_index.html");
        GlossaryFacility.htmlIndexPage(words, out, termLink);
        out.close();

        SimpleReader reader = new SimpleReader1L("test_empty_index.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("<title>Glossary</title>"));
        assertTrue(htmlContent.contains("<ul>"));
        assertTrue(htmlContent.contains("</ul>"));
    }

    /**
     * Tests htmlIndexPage with Multiple words and links.
     */
    @Test
    public void testHtmlIndexPageMultiple() {
        Sequence<String> words = new Sequence1L<String>();
        Sequence<String> termLink = new Sequence1L<String>();
        words.add(0, "Pneumonoultramicroscopicsilicovolcanoconiosis");
        words.add(1, "Hippopotomonstrosesquipedaliophobia");
        words.add(2, "Supercalifragilisticexpialidocious");
        words.add(3, "Pseudopseudohypoparathyroidism");
        words.add(4, "Floccinaucinihilipilification");

        termLink.add(0, "Pneumonoultramicroscopicsilicovolcanoconiosis.html");
        termLink.add(1, "Hippopotomonstrosesquipedaliophobia.html");
        termLink.add(2, "Supercalifragilisticexpialidocious.html");
        termLink.add(3, "Pseudopseudohypoparathyroidism.html");
        termLink.add(4, "Floccinaucinihilipilification.html");

        SimpleWriter out = new SimpleWriter1L("test_empty_index.html");
        GlossaryFacility.htmlIndexPage(words, out, termLink);
        out.close();

        SimpleReader reader = new SimpleReader1L("test_empty_index.html");
        StringBuilder content = new StringBuilder();
        while (!reader.atEOS()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();

        String htmlContent = content.toString();
        assertTrue(htmlContent.contains("<title>Glossary</title>"));
        assertTrue(htmlContent.contains(
                "<a href=\"Pneumonoultramicroscopicsilicovolcanoconiosis.html\">Pneumonoultramicroscopicsilicovolcanoconiosis</a>"));
        assertTrue(htmlContent.contains(
                "<a href=\"Hippopotomonstrosesquipedaliophobia.html\">Hippopotomonstrosesquipedaliophobia</a>"));
        assertTrue(htmlContent.contains(
                "<a href=\"Pseudopseudohypoparathyroidism.html\">Pseudopseudohypoparathyroidism</a>"));
        assertTrue(htmlContent.contains(
                "<a href=\"Supercalifragilisticexpialidocious.html\">Supercalifragilisticexpialidocious</a>"));
        assertTrue(htmlContent.contains(
                "<a href=\"Floccinaucinihilipilification.html\">Floccinaucinihilipilification</a>"));
    }
}
