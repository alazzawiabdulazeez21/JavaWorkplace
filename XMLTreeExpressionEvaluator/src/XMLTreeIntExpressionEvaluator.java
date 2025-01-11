import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Abdulazeez Alazzawi
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {

//        Starter variables result will be returned and label ius used for a cleaner code
        int result = 0;
        String label = exp.label();

//        checks if starting label is a number returns value if yes
        if (exp.label().equals("number")) {
            result = Integer.parseInt(exp.attributeValue("value"));
        } else {
//            mathmatical value of first child
            result = evaluate(exp.child(0));

//            starts at 1 and does the operations based on label
            for (int i = 1; i < exp.numberOfChildren(); i++) {
                if (label.equals("minus")) {
                    result -= evaluate(exp.child(i));
                } else if (label.equals("times")) {
                    result *= evaluate(exp.child(i));
                } else if (label.equals("divide")) {
                    if (evaluate(exp.child(i)) != 0) {
                        result /= evaluate(exp.child(i));
                    }
                } else if (label.equals("plus")) {
                    result += evaluate(exp.child(i));
                }
            }
        }
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
