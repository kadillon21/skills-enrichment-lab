package com.pluralsight.cli.util;

public class ConsoleUtilities {

    // Reset
    public static final String RESET = "\u001B[0m";


    // Text styles
    public static final String BOLD = "\u001B[1m";
    public static final String STRIKETHROUGH = "\u001B[9m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String REVERSED = "\u001B[7m";

    // Colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";


    // Common Phrases
    public static final String ERROR = ConsoleUtilities.BRIGHT_RED + ConsoleUtilities.BOLD + "ERROR: " + ConsoleUtilities.RESET;

    private ConsoleUtilities() {
    }

    // Color Scheme
    // Structure (borders, dividers)
    public static final String BORDER = "\u001B[38;5;75m";

    // Action / highlight (menu keys, prompts)
    public static final String ACCENT = "\u001B[38;5;208m";

    // Success / money in
    public static final String SUCCESS = "\u001B[38;5;114m";

    // Danger / money out / errors
    public static final String DANGER = "\u001B[38;5;203m";

    // Warning / (confirmations)

    public static final String WARNING = ConsoleUtilities.YELLOW;

    // Muted (helper text, timestamps)
    public static final String MUTED = "\u001B[38;5;243m";

    // displays a progress bar
    public static void showProgressBar(String label, int durationMs) {
        int totalBars = 30;
        int stepDelay = durationMs / totalBars;

        for (int i = 0; i <= totalBars; i++) {
            int filled = i;
            int empty = totalBars - i;
            int percent = (i * 100) / totalBars;

            System.out.print("\r" + label + " ["
                    + "█".repeat(filled)
                    + "░".repeat(empty)
                    + "] " + percent + "%");

            try {
                Thread.sleep(stepDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    // displays a spinning menu
    public static void spin(String label, int durationMs) {
        char[] frames = {'⠋', '⠙', '⠹', '⠸', '⠼', '⠴', '⠦', '⠧', '⠇', '⠏'};
        long endTime = System.currentTimeMillis() + durationMs;
        int i = 0;

        while (System.currentTimeMillis() < endTime) {
            System.out.print("\r" + label + " " + frames[i % frames.length]);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            i++;
        }
        System.out.print("\r" + label + " " + ConsoleUtilities.SUCCESS + "✓" + ConsoleUtilities.RESET);
        System.out.println();
    }
}
