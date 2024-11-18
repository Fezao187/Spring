package kristine.card_generator.tools.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class GenerateCard {

    public BigInteger genCardNum(int numDigits) {
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

    public int genCvvNum(int numDigits) {
        if (numDigits < 1) {
            throw new IllegalArgumentException("Number of digits must be at least 1");
        }

        // Calculate the range for the random number
        int lowerBound = (int) Math.pow(10, numDigits - 1);
        int upperBound = (int) Math.pow(10, numDigits) - 1;

        // Generate a random number within this range
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public String genDate(){
        LocalDate expiryDate = LocalDate.now().plusYears(5);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/yy");

        return expiryDate.format(formatter);
    }
}
