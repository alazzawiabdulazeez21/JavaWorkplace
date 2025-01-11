import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Calculate Square root using Newton Iteration.
 *
 * @author Abdulazeez Alazzawi
 *
 */
public final class Newton1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        double result = x;
        final double relativeError = .0001;

        while (Math.abs((result * result - x) / x) >= relativeError) {
            result = (result + x / result) / 2.0;
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

        String answer = "y";
        while (answer.equals("y")) {
            out.print("What Number do you want to square root: ");
            double numSqr = in.nextDouble();

            out.println("The square root of your number is:  " + sqrt(numSqr));

            out.print("Would you like to check for square root again?  ");
            answer = in.nextLine();

        }

        in.close();
        out.close();
    }

}
