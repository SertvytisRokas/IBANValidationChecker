package local;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    Validation validation = new Validation();
    private final String testNumber = "LT647044001231465456";
    private final String testFlag = "LT";
    private int testLength = 20;

    @Before
    public void setUp() {
        validation = new Validation();
    }

    @Test
    public void isNotTooLong() {
        assertTrue(Validation.checkLength(testNumber));
    }

    @Test
    public void doStartWith2Letters() {
        assertTrue(Validation.checkFirst2Chars(testNumber));
    }

    @Test
    public void areLettersCapital() {
        assertTrue(Validation.checkIfLetterCapital(testNumber));
    }

    @Test
    public void canGenerateFlag() {
        assertEquals("LT", validation.generateFlag(testNumber));
    }

    @Test
    public void canGenerateBBAN() {
        assertEquals("7044001231465456", validation.generateBBAN(testNumber));
    }

    @Test
    public void canGenerateInitCharacters() {
        assertEquals("212964", validation.generateInitChars(testNumber));
    }

    @Test
    public void canConvertFlag() {
        assertEquals("2129", validation.convertFlag(testNumber));
    }

    @Test
    public void canVerifyCountry() {
        assertTrue(Validation.isValidCountry(testFlag));
    }

    @Test
    public void canCompareFlagToLength() {
        assertTrue(Validation.isValidLengthToFlag(testLength, testFlag));
    }
}
