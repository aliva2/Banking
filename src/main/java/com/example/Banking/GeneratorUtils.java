package com.example.Banking;

import java.util.Random;
//card number for security
public class GeneratorUtils {

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }

        int sum = 0;
        for (int i = 0; i < 15; i++) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        int checksum = (10 - (sum % 10)) % 10;
        cardNumber.append(checksum);

        return cardNumber.toString();
    }
}
