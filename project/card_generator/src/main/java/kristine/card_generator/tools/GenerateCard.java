package kristine.card_generator.tools;

import kristine.card_generator.models.VirtualCard;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateCard {
    VirtualCard card = new VirtualCard();

    public VirtualCard generateCard() {
        card.setCardNumber(genCardNum(16));
        card.setCvv(genCvvNum(3));
        card.setExpiryDate(genDate());
        return card;
    }

    private BigInteger genCardNum(int numDigits) {
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

    private int genCvvNum(int numDigits) {
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

    private String genDate(){
        LocalDate expiryDate = LocalDate.now().plusYears(5);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/yy");

        return expiryDate.format(formatter);
    }
}
