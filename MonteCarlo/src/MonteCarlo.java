import components.random.Random;
import components.random.Random1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public final class MonteCarlo {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private MonteCarlo() {
    }

    /**
     * Checks whether the given point (xCoord, yCoord) is inside the circle of
     * radius 1.0 centered at the point (1.0, 1.0).
     *
     * @param xCoord
     *            the x coordinate of the point
     * @param yCoord
     *            the y coordinate of the point
     * @return true if the point is inside the circle, false otherwise
     */
    private static boolean pointIsInCircle(double xCoord, double yCoord) {
        double distance = Math
                .sqrt(Math.pow((xCoord - 1), 2) + Math.pow((yCoord - 1), 2));
        return distance <= 1.0;
    }

    /**
     * Generates n pseudo-random points in the [0.0,2.0) x [0.0,2.0) square and
     * returns the number that fall in the circle of radius 1.0 centered at the
     * point (1.0, 1.0).
     *
     * @param n
     *            the number of points to generate
     * @return the number of points that fall in the circle
     */
    private static int numberOfPointsInCircle(int numOfPoints) {
        Random rnd = new Random1L();
        int j = 0;

        for (int i = 0; i < numOfPoints; i++) {
            double x = rnd.nextDouble() * 2.0, y = rnd.nextDouble() * 2.0;

            if (pointIsInCircle(x, y) == true) {
                j++;
            }
        }

        return j;
    }

    /**
     * Main method to estimate the value of Pi using the Monte Carlo method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        SimpleReader input = new SimpleReader1L();
        SimpleWriter output = new SimpleWriter1L();

        output.print("Number of points: ");
        int numOfPoints = input.nextInteger();

        int pointsInCircle = numberOfPointsInCircle(numOfPoints);

        double areaEstimate = 4.0 * pointsInCircle / numOfPoints;

        /*
         * Output the estimated value of Pi
         */
        output.println("Estimated area of circle: " + areaEstimate);

        input.close();
        output.close();
    }
}
