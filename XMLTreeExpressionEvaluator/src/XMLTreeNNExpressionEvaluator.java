import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Abdulazeez Alazzawi
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires * [exp is a subtree of a well-formed XML arithmetic expression]
     *           and [the label of the root of exp is not "expression"]
     *
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {

//        variables for result and label
        NaturalNumber result = new NaturalNumber2();
        String label = exp.label();

//        if the first label is number then it just returns the value of it
        if (label.equals("number")) {
            result = new NaturalNumber2(exp.attributeValue("value"));
        } else {

//            sets this equal to the first evaluation of the first child
            result = evaluate(exp.child(0));

//            starts at 1 and does the operations based on label
            for (int i = 1; i < exp.numberOfChildren(); i++) {

                if (label.equals("plus")) {

                    result.add(evaluate(exp.child(i)));

                } else if (label.equals("minus")) {

                    if (result.compareTo(evaluate(exp.child(i))) < 0) {
                        Reporter.fatalErrorToConsole(
                                "Cant result in a negative number with subtraction");
                    }

                    result.subtract(evaluate(exp.child(i)));

                } else if (label.equals("times")) {

                    result.multiply(evaluate(exp.child(i)));

                } else if (label.equals("divide")) {

                    if (evaluate(exp.child(i)).isZero()) {
                        Reporter.fatalErrorToConsole("Cant divide by 0, its not Allowed");
                    }

                    result.divide(evaluate(exp.child(i)));
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
