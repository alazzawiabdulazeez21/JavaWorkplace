import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Calculate Square root using Newton Iteration.
 *
 * @author Abdulazeez alazzawi
 *
 */
public final class Newton4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     * Works when x = 0. User enters relative error manually
     *
     * @param x
     * @param error
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double error) {
        double result = x;
        double relativeError = error;

        if (x > 0) {
            while (Math.abs((result * result - x) / x) >= relativeError) {
                result = (result + x / result) / 2.0;
            }

        } else if (x == 0) {
            result = 0;
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
        double numSqr = 0;

        while (answer.equals("y") && numSqr >= 0) {

//         Asks The use to enter a value of x if its negative it breaks
            out.print("Enter a New Value of X: ");
            numSqr = in.nextDouble();

//         Checks if its negative or not
            if (numSqr >= 0) {

                out.print("Whats the relative error in decimal form: ");
                double relativeError = in.nextDouble();

                out.println("The square root of your number is:  "
                        + sqrt(numSqr, relativeError));

                out.print("Would you like to check for square root again?  ");
                answer = in.nextLine();
            }

        }

        in.close();
        out.close();
    }

}
