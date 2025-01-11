import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program.
 *
 * @author P. Bucci
 */
public final class HelloWorld {

    /**
     * No-argument constructor--private to prevent instantiation.
     */
    private HelloWorld() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        out.println("Hello World!");
        int x = 8;
        int count = 0;
        while ((x / 3) % 2 != 0) {
            x = x - 1;
            count++;
        }
        out.print(x + ", " + count);
        out.close();
    }

}
