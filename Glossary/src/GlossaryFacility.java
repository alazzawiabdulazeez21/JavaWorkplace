import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class to retreve words and defentions from a text file.
 *
 * @author Abdulazeez Alazzawi
 */
public final class GlossaryFacility {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private GlossaryFacility() {
    }

    /**
     * outputs the tags to generate a HTML Index file.
     *
     * @param words
     *            the glossary terms
     *
     * @param out
     *            Output where Html WIll be written
     *
     * @param termLink
     *            sequence of links that correspond to each term
     *
     *
     * @updates out.content
     * @requires words.length() = termLink.length() and out.is_open and [words
     *           and termLink contain valid terms and links for the glossary]
     * @ensures <pre>
     * out.content = #content * [XHTML 1.0 Strict index page]
     * terms are ordered in a ul list
     * each term linked to page from corresponding termLink
     * </pre>
     */
    public static void htmlIndexPage(Sequence<String> words, SimpleWriter out,
            Sequence<String> termLink) {
        assert words != null : "words sequence cannot be null";
        assert termLink != null : "termLink sequence cannot be null";
        assert words.length() == termLink.length()
                : "words and termLink sequences must be same length";

        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'"
                + " 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type'"
                + " content='text/html; charset=ISO-8859-1' />");
        out.println("<title>Glossary</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr/>");
        out.println("<h3>Index</h3>");
        out.println("<ul>");

        for (int i = 0; i < words.length(); i++) {
            String term = words.entry(i);
            String link = termLink.entry(i);
            out.println("<li><a href=\"" + link + "\">" + term + "</a></li>");
        }

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Outputs the terms and definitions to its corresponding term in a html
     * file.
     *
     * @param term
     *            contains a term from the text file.
     *
     * @param definition
     *            contains the definition of the term.
     *
     * @param out
     *            it will update the corresponding HTML file for the term.
     *
     * @param glossary
     *            Map compoentns with terms as keys and definitions as values
     *
     * @updates out.content
     *
     * @requires term.length()>0 and definition.length()>0
     *
     * @ensures <pre>
     * out.content = #content * [XHTML 1.0 Strict index page]
     * term in red bold and italic format
     * definition is in blockquote
     * </pre>
     */
    public static void htmlTermPage(String term, String definition, SimpleWriter out,
            Map<String, String> glossary) {
        assert term != null : "term cannot be null";
        assert definition != null : "definition cannot be null";
        assert term.length() > 0 : "term cannot be empty";
        assert definition.length() > 0 : "definition cannot be empty";

        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'"
                + " 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>");
        out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type'"
                + " content='text/html; charset=ISO-8859-1' />");
        out.println("<title>" + term + "</title>");
        out.println("</head>");

        out.println("<body>");
        out.println("<h2><b><i><font color=\"red\">" + term + "</font></i></b></h2>");
        out.println("<blockquote>" + addDefinitionLinks(definition, glossary)
                + "</blockquote>");
        out.println("<hr/>");
        out.println("<p>Return to <a href=\"index.html\">index</a></p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    /**
     * defTerm coppies the definitions from fileName.
     *
     * @param fileName
     *            the text file name
     *
     * @param defTerm
     *            Map component that contains term as the key and definiton as
     *            the value
     *
     * @updates defTerm
     * @requires defTerm.size()=0 and fileName.length()>0
     * @ensures <pre>
     * defTerm.Size()>0 and
     * defTerm contains all terms and definition from fileName
     * </pre>
     */
    public static void linesFromInput(String fileName, Map<String, String> defTerm) {
//        Post Conditions
        assert fileName != null : "fileName cannot be null";
        assert fileName.length() > 0 : "fileName cannot be empty";
        assert defTerm != null : "defTerm cannot be null";
        assert defTerm.size() == 0 : "defTerm must be empty";

        SimpleReader input = new SimpleReader1L(fileName);
        while (!input.atEOS()) {
            String term = input.nextLine();

            String line = input.nextLine();
            String definition = "";
            while (!line.isEmpty() && !input.atEOS()) {
                if (definition.isEmpty()) {
                    definition = line;
                } else {
                    definition = definition + "\n" + line;
                }
                line = input.nextLine();
            }

            defTerm.add(term, definition);
        }
        input.close();
    }

    /**
     * Makes the terms in alphabtical order.
     *
     * @param sortIt
     *            sequence full of terms that becomer sorted alphabetically
     *
     * @updates sortIt
     * @requires sortIt.length()>0 and [sortIt cannot be null]
     * @ensures <pre>
     * sortIt = [SorIt is sorted in an alphabtical order]
     * </pre>
     */
    public static void sort(Sequence<String> sortIt) {
//        Postconditions
        assert sortIt != null : "sortIt cannot be null";
        assert sortIt.length() > 0 : "sortIt cannot be empty";
        for (int k = 0; k < sortIt.length(); k++) {
            assert sortIt.entry(k) != null : "entries cannot be null";
        }

        /*
         * Has two loops first one checks one term with all the other term by
         * utilizing the second loop, then changes based on the alograthim used
         */
        for (int i = 1; i < sortIt.length(); i++) {
            String temp = sortIt.entry(i);
            int j = i;

            while (j > 0 && sortIt.entry(j - 1).toLowerCase()
                    .compareTo(temp.toLowerCase()) > 0) {
                sortIt.replaceEntry(j, sortIt.entry(j - 1));
                j--;
            }
            sortIt.replaceEntry(j, temp);
        }
    }

    /**
     * If the term has a word from the list of terms of the text, it turns that
     * word into a clickable link.
     *
     * @param definition
     *            the meaning of the terms
     * @param glossary
     *            the map of terms and definitions
     *
     * @return linkDef
     * @requires definition.length() > 0 and glossary.size() > 0 and [definition
     *           and glossary are not null]
     * @ensures <pre> [Any word in definition matching a term in glossary is
     *          replaced with HTML link]
     * </pre>
     */
    public static String addDefinitionLinks(String definition,
            Map<String, String> glossary) {
        assert definition != null : "definition cannot be null";
        assert glossary != null : "glossary cannot be null";
        assert definition.length() > 0 : "definition cannot be empty";
        assert glossary.size() > 0 : "glossary cannot be empty";

        String linkedDef = definition;
        for (Map.Pair<String, String> pair : glossary) {
            String term = pair.key();
            // Replaces the term with a html link
            linkedDef = linkedDef.replaceAll("\\b" + term + "\\b",
                    "<a href=\"" + term + ".html\">" + term + "</a>");
        }
        return linkedDef;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        // Get input file path and folderName
        out.print("Input File (With Directory): ");
        String fileName = in.nextLine();

        out.print("Directory of Folder Name: ");
        String folderName = in.nextLine();

        Map<String, String> defTerm = new Map1L<>();
        linesFromInput(fileName, defTerm);

        Sequence<String> terms = new Sequence1L<>();
        for (Map.Pair<String, String> pair : defTerm) {
            terms.add(terms.length(), pair.key());
        }

        sort(terms);

        // Another sequence with the same order as the terms sequence
        Sequence<String> termsLink = new Sequence1L<>();
        for (int i = 0; i < terms.length(); i++) {
            termsLink.add(termsLink.length(), terms.entry(i) + ".html");
        }

        for (int i = 0; i < defTerm.size(); i++) {
            String term = terms.entry(i);
            String definition = defTerm.value(term);
            SimpleWriter termFile = new SimpleWriter1L(folderName + "/" + term + ".html");
            htmlTermPage(term, definition, termFile, defTerm);
        }

        // index created in the specified folder
        SimpleWriter indexFile = new SimpleWriter1L(folderName + "/index.html");
        htmlIndexPage(terms, indexFile, termsLink);
        indexFile.close();

        in.close();
        out.close();
    }
}
