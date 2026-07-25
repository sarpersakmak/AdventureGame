package com.adventuregame.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Centralizes all console input and output operations.
 *
 * <p>Using one scanner prevents the input-buffer problems caused by creating
 * multiple {@link Scanner} instances for {@code System.in}.</p>
 */
public final class ConsoleIO {
    private final Scanner scanner;
    private final PrintStream output;

    public ConsoleIO(InputStream input, PrintStream output) {
        this.scanner = new Scanner(Objects.requireNonNull(input, "input must not be null"));
        this.output = Objects.requireNonNull(output, "output must not be null");
    }

    public void print(String message) {
        output.print(message);
    }

    public void println(String message) {
        output.println(message);
    }

    public void println() {
        output.println();
    }

    /**
     * Reads a non-empty line and keeps asking until the user enters one.
     */
    public String readNonBlankLine(String prompt) {
        while (true) {
            print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            println("Please enter a non-empty value.");
        }
    }

    /**
     * Reads an integer inside the inclusive range.
     */
    public int readIntInRange(String prompt, int minimum, int maximum) {
        if (minimum > maximum) {
            throw new IllegalArgumentException("minimum must not be greater than maximum");
        }

        while (true) {
            print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= minimum && number <= maximum) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // The shared validation message below is enough for the player.
            }
            println("Please enter a number between " + minimum + " and " + maximum + ".");
        }
    }

    /**
     * Reads one of the supplied case-insensitive choices.
     */
    public String readChoice(String prompt, Set<String> validChoices) {
        Objects.requireNonNull(validChoices, "validChoices must not be null");
        if (validChoices.isEmpty()) {
            throw new IllegalArgumentException("validChoices must not be empty");
        }

        while (true) {
            print(prompt);
            String choice = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
            if (validChoices.contains(choice)) {
                return choice;
            }
            println("Please enter one of: " + String.join(", ", validChoices));
        }
    }
}
