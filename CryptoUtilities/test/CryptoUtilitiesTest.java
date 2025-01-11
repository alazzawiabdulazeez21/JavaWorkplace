import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Abdulazeez Alazzawi
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_523_2() {
        NaturalNumber n = new NaturalNumber2(523);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_0_1() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(1);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_5345() {
        NaturalNumber n = new NaturalNumber2(5345);
        NaturalNumber nExpected = new NaturalNumber2(5345);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_10() {
        NaturalNumber n = new NaturalNumber2(10);
        NaturalNumber nExpected = new NaturalNumber2(10);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_2_3_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(6);
        NaturalNumber p = new NaturalNumber2(3);
        NaturalNumber pExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_5_1_7() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber pExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    @Test
    public void testIsWitnessToCompositeness_CompositeNumber() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(15); // 15 is composite
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsWitnessToCompositeness_PrimeNumber() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(5); // 5 is prime
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsWitnessToCompositeness_2_91() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(91);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsWitnessToCompositeness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        boolean result = CryptoUtilities.isWitnessToCompositeness(w, n);
        assertEquals(true, result);
    }

    /*
     * Tests of isPrime2
     */

    @Test
    public void testIsPrime2_SmallPrime() {
        NaturalNumber n = new NaturalNumber2(3);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_LargePrime() {
        NaturalNumber n = new NaturalNumber2(101);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(true, result);
    }

    @Test
    public void testIsPrime2_15() {
        NaturalNumber n = new NaturalNumber2(15);
        boolean result = CryptoUtilities.isPrime2(n);
        assertEquals(false, result);
    }

    /*
     * Tests of generateNextLikelyPrime
     */

    @Test
    public void testGenerateNextLikelyPrime_PrimeInput() {
        NaturalNumber n = new NaturalNumber2(7); // 7 is prime
        NaturalNumber nExpected = new NaturalNumber2(7);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_NonPrimeInput() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_LargeNonPrimeInput() {
        NaturalNumber n = new NaturalNumber2(20);
        NaturalNumber nExpected = new NaturalNumber2(23);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
