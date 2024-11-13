import java.math.BigInteger;
import java.security.SecureRandom;

public class RandNum {

    public static BigInteger generateRandomNumber(int numDigits) {
        if (numDigits < 1) {
            throw new IllegalArgumentException("Number of digits must be at least 1");
        }

        // SecureRandom provides a more secure random number generation
        SecureRandom random = new SecureRandom();

        // Generate a random number with the specified number of digits
        StringBuilder number = new StringBuilder();

        // First digit should be non-zero to ensure correct digit count
        number.append(random.nextInt(9) + 1);

        // Generate the remaining digits
        for (int i = 1; i < numDigits; i++) {
            number.append(random.nextInt(10));
        }

        // Convert the string to BigInteger
        return new BigInteger(number.toString());
    }

    public static void main(String[] args) {
        System.out.println(generateRandomNumber(16));  // Generates a 15-digit random number
    }
}
