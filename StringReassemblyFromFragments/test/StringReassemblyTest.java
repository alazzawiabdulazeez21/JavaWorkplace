import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    // Tests for combination method
    @Test
    public void testCombinationWithSimpleOverlap() {
        String str1 = "Hello";
        String str2 = "low";
        int overlap = 2;
        String expected = "Hellow";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    @Test
    public void testCombinationWithNoOverlap() {
        String str1 = "Hello";
        String str2 = "World";
        int overlap = 0;
        String expected = "HelloWorld";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    @Test
    public void testCombinationWithFullOverlap() {
        String str1 = "Hello";
        String str2 = "Hello";
        int overlap = 5;
        String expected = "Hello";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(expected, result);
    }

    // Tests for addToSetAvoidingSubstrings method
    @Test
    public void testAddToSetAvoidingSubstringsWithNewString() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hello");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "World");
        assertEquals(true, strSet.contains("Hello"));
        assertEquals(true, strSet.contains("World"));
        assertEquals(2, strSet.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsWithSubstring() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Hello");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "HelloWorld");
        assertEquals(false, strSet.contains("Hello"));
        assertEquals(true, strSet.contains("HelloWorld"));
        assertEquals(1, strSet.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsWithSuperstring() {
        Set<String> strSet = new Set1L<>();
        strSet.add("HelloWorld");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "Hello");
        assertEquals(false, strSet.contains("HelloWorld"));
        assertEquals(false, strSet.contains("Hello"));
        assertEquals(0, strSet.size());
    }

    @Test
    public void testAddToSetAvoidingSubstringsWithMultipleItemsinLIst() {
        Set<String> strSet = new Set1L<>();
        strSet.add("HelloWorld");
        strSet.add("WorldHello");
        strSet.add("hi");
        strSet.add("simple");
        StringReassembly.addToSetAvoidingSubstrings(strSet, "Hello");
        assertEquals(false, strSet.contains("HelloWorld"));
        assertEquals(false, strSet.contains("Hello"));
        assertEquals(2, strSet.size());
    }

    // Tests for linesFromInput method
    @Test
    public void testLinesFromInputWithSimpleInput() {
        SimpleReader input = new SimpleReader1L("data/test-input.txt");
        Set<String> result = StringReassembly.linesFromInput(input);
        assertEquals(true, result.contains("HelloWorld"));
        assertEquals(false, result.contains("Hello"));
        assertEquals(false, result.contains("World"));
        assertEquals(1, result.size());
        input.close();
    }

    @Test
    public void testLinesFromInputWithNoSubstrings() {
        SimpleReader input = new SimpleReader1L("data/test-input2.txt");
        Set<String> result = StringReassembly.linesFromInput(input);
        assertEquals(true, result.contains("Hello"));
        assertEquals(true, result.contains("World"));
        assertEquals(true, result.contains("Goodbye"));
        assertEquals(3, result.size());
        input.close();
    }

    @Test
    public void testLinesFromInputWithLetters() {
        SimpleReader input = new SimpleReader1L("data/test-input3.txt");
        Set<String> result = StringReassembly.linesFromInput(input);
        assertEquals(true, result.contains("H"));
        assertEquals(true, result.contains("G"));
        assertEquals(true, result.contains("K"));
        assertEquals(3, result.size());
        input.close();
    }

    // Tests for printWithLineSeparators method
    @Test
    public void testPrintWithLineSeparatorsNoSeparators() {
        SimpleWriter out = new SimpleWriter1L("test-output.txt");
        StringReassembly.printWithLineSeparators("Hello World", out);
        out.close();
        SimpleReader in = new SimpleReader1L("test-output.txt");
        String result = in.nextLine();
        assertEquals("Hello World", result);
        in.close();
    }

    @Test
    public void testPrintWithLineSeparatorsWithSeparators() {
        SimpleWriter out = new SimpleWriter1L("test-output.txt");
        StringReassembly.printWithLineSeparators("Hello~World", out);
        out.close();
        SimpleReader in = new SimpleReader1L("test-output.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        assertEquals("Hello", line1);
        assertEquals("World", line2);
        in.close();
    }

    @Test
    public void testPrintWithLineSeparatorsMultipleSeparators() {
        SimpleWriter out = new SimpleWriter1L("test-output.txt");
        StringReassembly.printWithLineSeparators("Hello~Dear~World", out);
        out.close();
        SimpleReader in = new SimpleReader1L("test-output.txt");
        String line1 = in.nextLine();
        String line2 = in.nextLine();
        String line3 = in.nextLine();
        assertEquals("Hello", line1);
        assertEquals("Dear", line2);
        assertEquals("World", line3);
        in.close();
    }
}
