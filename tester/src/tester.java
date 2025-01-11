import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author P. Bucci
 */
public final class tester {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private tester() {
        // no code needed here
    }

    private static NaturalNumber funTimes(NaturalNumber[] a, NaturalNumber n) {
        NaturalNumber local = new NaturalNumber2(5);
        n.multiply(local);
        n = new NaturalNumber2(17);
        a[1] = n;
        a[2].add(a[0]);
        a[0] = new NaturalNumber2(n);
        return n.divide(a[0]);
    }

    /**
     * Raises an int to a power using recursion.
     *
     * @param n
     *            The base number
     * @param p
     *            The exponent (power)
     * @return n raised to the power p
     * @requires p >= 0 and [n ^ (p) is within int range]
     * @ensures power = n ^ (p)
     */
    private static int power(int n, int p) {
        int result = 1;
        if (p >= 1) {
            result = n * power(n, p - 1);
        }
        return result;
    }

    private static int largestDigit(NaturalNumber n) {
        NaturalNumber one = new NaturalNumber2(1);
        int result = 1;
        if (!n.isZero()) {
            int digit = n.divideBy10();
            result = largestDigit(n);
            if (digit > result) {
                result = digit;
            }
        }

        return result;
    }

    private static int digitSum(int n) {
        int result = n;
        int reminder = n % 10;
        if (n > 9) {
            result = digitSum(n / 10) + reminder;
        }

        return result;
    }

    /**
     * Reports the evenness of the sum of digits in NN n.
     *
     * @ensures evenSum = [whether or not the sum of the digits in n is even]
     */
    private static boolean evenSum(NaturalNumber n) {
        boolean result = true;
        int lastDigit = n.divideBy10();

        if (!n.isZero()) {
            result = evenSum(n);

        }
        n.multiplyBy10(lastDigit);

        return result == (lastDigit % 2 == 0);
    }

    private static int digtSumsad(int n) {
        int result = 0;
        NaturalNumber j = new NaturalNumber2(n);
        String wow = j.toString();
        for (int i = 0; i < wow.length(); i++) {
            int lastDigit = j.divideBy10();
            result += lastDigit;
        }
        return result;
    }

    private static String madness(int[] a, int x, String s) {
        String retValue = s + "alot";
        a[2] = x;
        x++;
        int temp = a[1];
        a[1] = a[0];
        a[0] = temp;
        s = s + "burger";
        return (x + retValue + temp);
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

        int z = 42;
        String s = "narf";
        int[] narray = { 1, 2, 3 };
        String str = madness(narray, z, s);
        out.println(str + " " + z + " " + s + " " + narray[0] + " " + narray[1] + " "
                + narray[2]);

        NaturalNumber nat = new NaturalNumber2(11);
        NaturalNumber[] array = new NaturalNumber2[3];
        array[0] = new NaturalNumber2(4);
        array[1] = new NaturalNumber2(5);
        array[2] = new NaturalNumber2(6);
        NaturalNumber result = funTimes(array, nat);

        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + ", ");
        }
        out.println(" ");
        out.println(nat + " " + " " + result);

        out.println("Power Function: " + power(5, 3));

        out.println(
                "largestDigit Function: " + largestDigit(new NaturalNumber2(5429678)));

        out.println("DigitSum Function: " + digitSum(3));

        out.println("is the sum Even Function: " + evenSum(new NaturalNumber2(344)));
        out.println("is the sum Even Function: " + digtSumsad((343879)));
        NaturalNumber a = new NaturalNumber2(5);
        NaturalNumber b = new NaturalNumber2(3);
        a.subtract(b);
        b.transferFrom(a);
        NaturalNumber c = b;
        NaturalNumber d = new NaturalNumber2(b);
        c.increment();
        d.multiply(b);
        c = a;
        a.copyFrom(d);
//        c = d.divide(b);
        a.increment();

        out.print("a: " + a + ", b: " + b + ", c: " + c + ", d: " + d);

    }

}
