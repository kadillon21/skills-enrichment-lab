package com.pluralsight.cli.ui;

import com.pluralsight.model.SearchCriteria;
import com.pluralsight.model.Transaction;
import com.pluralsight.service.Ledger;
import com.pluralsight.cli.util.ConsoleUtilities;
import com.pluralsight.cli.util.UserInput;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menus {

    // Displays splash screen for startup
    public static void displaySplashScreen() {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String R = ConsoleUtilities.RESET;

        System.out.println(B +
                "  ██████╗ ██████╗ ███████╗███████╗████████╗██╗ ██████╗ ███████╗\n" +
                "  ██╔══██╗██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║██╔════╝ ██╔════╝\n" +
                "  ██████╔╝██████╔╝█████╗  ███████╗   ██║   ██║██║  ███╗█████╗\n"  +
                "  ██╔═══╝ ██╔══██╗██╔══╝  ╚════██║   ██║   ██║██║   ██║██╔══╝\n"  +
                "  ██║     ██║  ██║███████╗███████║   ██║   ██║╚██████╔╝███████╗\n" +
                "  ╚═╝     ╚═╝  ╚═╝╚══════╝╚══════╝   ╚═╝   ╚═╝ ╚═════╝ ╚══════╝" + R);

        System.out.println(A +
                "            ═══════════════════════════════════════\n" +
                "                    M O T O R S   G R O U P\n"        +
                "            ═══════════════════════════════════════" + R);

    }

    // Displays dashboard
    public static void displayDashboard(Ledger ledger) {
        String B = ConsoleUtilities.BORDER;
        String R = ConsoleUtilities.RESET;

        double balance = ledger.getBalance();
        String balanceColor = balance >= 0 ? ConsoleUtilities.SUCCESS : ConsoleUtilities.DANGER;
        // formats money properly
        NumberFormat money = NumberFormat.getCurrencyInstance();

        System.out.println();
        System.out.println(B + "╔═ QUICK STATS " + "═══════════════════════════════════════" + "╗");
        System.out.printf(B + "║  Balance:        " + balanceColor + "%-35s" + B + "║%n", money.format(balance));
        System.out.printf(B + "║  MTD Income:     " + ConsoleUtilities.GREEN + "%-35s" + B + "║%n", money.format(ledger.getMonthToDateIncome()));
        System.out.printf(B + "║  MTD Expenses:   " + ConsoleUtilities.RED + "%-35s" + B + "║%n", money.format(ledger.getMonthToDateExpenses()));
        System.out.printf(B + "║  Transactions:   %-35d" + B + "║%n", ledger.getTransactionCount());
        System.out.println(B + "╚" + "═════════════════════════════════════════════════════" + "╝" + R);
    }

    // Displays main menu
    public static void mainMenu() {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String Bo = ConsoleUtilities.BOLD;
        String U = ConsoleUtilities.UNDERLINE;
        String R = ConsoleUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║  " + Bo + U + R + "    ACCOUNTING LEDGER       " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "D" + R + ")  Add Deposit             " + B + "║");
        System.out.println(B + "║  " + A + "P" + R + ")  Make Payment (Debit)    " + B + "║");
        System.out.println(B + "║  " + A + "L" + R + ")  Ledger                  " + B + "║");
        System.out.println(B + "║  " + A + "X" + R + ")  Exit                    " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    // Display ledger menu
    public static void ledgerMenu() {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String Bo = ConsoleUtilities.BOLD;
        String U = ConsoleUtilities.UNDERLINE;
        String R = ConsoleUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "       LEDGER            " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "A" + R + ")  All Entries             " + B + "║");
        System.out.println(B + "║  " + A + "D" + R + ")  Deposits                " + B + "║");
        System.out.println(B + "║  " + A + "P" + R + ")  Payments                " + B + "║");
        System.out.println(B + "║  " + A + "R" + R + ")  Reports                 " + B + "║");
        System.out.println(B + "║  " + A + "H" + R + ")  Home                    " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    // Displays reports menu
    public static void reportsMenu() {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String Bo = ConsoleUtilities.BOLD;
        String U = ConsoleUtilities.UNDERLINE;
        String R = ConsoleUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "      REPORTS            " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "1" + R + ")  Month To Date           " + B + "║");
        System.out.println(B + "║  " + A + "2" + R + ")  Previous Month          " + B + "║");
        System.out.println(B + "║  " + A + "3" + R + ")  Year To Date            " + B + "║");
        System.out.println(B + "║  " + A + "4" + R + ")  Previous Year           " + B + "║");
        System.out.println(B + "║  " + A + "5" + R + ")  Search by Vendor        " + B + "║");
        System.out.println(B + "║  " + A + "6" + R + ")  Custom Search           " + B + "║");
        System.out.println(B + "║  " + A + "0" + R + ")  Back                    " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    // displays transaction type menu
    public static void transTypeMenu() {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String Bo = ConsoleUtilities.BOLD;
        String U = ConsoleUtilities.UNDERLINE;
        String R = ConsoleUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "  Custom Search Menu     " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "A" + R + ")  All Transactions        " + B + "║");
        System.out.println(B + "║  " + A + "D" + R + ")  Deposits                " + B + "║");
        System.out.println(B + "║  " + A + "P" + R + ")  Payments                " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    // displays edit values menu
    public static void editValuesMenu() {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String Bo = ConsoleUtilities.BOLD;
        String U = ConsoleUtilities.UNDERLINE;
        String R = ConsoleUtilities.RESET;

        System.out.println();
        System.out.println(B + "╔══════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "  Custom Search Menu     " + R + B + "║");
        System.out.println(B + "╠══════════════════════════════╣");
        System.out.println(B + "║                              ║");
        System.out.println(B + "║  " + A + "1" + R + ")  Start Date              " + B + "║");
        System.out.println(B + "║  " + A + "2" + R + ")  End Date                " + B + "║");
        System.out.println(B + "║  " + A + "3" + R + ")  Description             " + B + "║");
        System.out.println(B + "║  " + A + "4" + R + ")  Vendor                  " + B + "║");
        System.out.println(B + "║  " + A + "5" + R + ")  Min Value               " + B + "║");
        System.out.println(B + "║  " + A + "6" + R + ")  Max Value               " + B + "║");
        System.out.println(B + "║  " + A + "7" + R + ")  Transaction type        " + B + "║");
        System.out.println(B + "║                              ║");
        System.out.println(B + "╚══════════════════════════════╝" + R);
    }

    // Displays custom search options
    public static void customSearchOptions(SearchCriteria criteria) {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String Bo = ConsoleUtilities.BOLD;
        String U = ConsoleUtilities.UNDERLINE;
        String R = ConsoleUtilities.RESET;

        // formats doubles into a proper money format
        NumberFormat money = NumberFormat.getCurrencyInstance();

        System.out.println();
        System.out.println(B + "╔════════════════════════════════════════════════════════════╗");
        System.out.println(B + "║     " + Bo + U + R + "                  Custom Search Menu                   " + R + B + "║");
        System.out.println(B + "╠════════════════════════════════════════════════════════════╣");
        System.out.println(B + "║                                                            ║");
        System.out.printf(B + "║  Start Date: " + A + "%-46s" + B + "║%n", displayValue(criteria.getStartDate()));
        System.out.printf(B + "║  End Date: " + A + "%-48s" + B + "║%n", displayValue(criteria.getEndDate()));
        System.out.printf(B + "║  Description: " + A + "%-45s" + B + "║%n", displayValue(criteria.getDescription()));
        System.out.printf(B + "║  Vendor: " + A + "%-50s" + B + "║%n", displayValue(criteria.getVendor()));
        System.out.printf(B + "║  Min Amount: " + A + "%-46s" + B + "║%n", money.format(criteria.getMinAmount()));
        System.out.printf(B + "║  Max Amount: " + A + "%-46s" + B + "║%n", money.format(criteria.getMaxAmount()));
        System.out.printf(B + "║  Transaction Type: " + A + "%-40s" + B + "║%n", displayValue(criteria.getTransType()));
        System.out.println(B + "║                                                            ║");
        System.out.println(B + "║  " + A + "E" + R + ")  Edit Values                                           " + B + "║");
        System.out.println(B + "║  " + A + "S" + R + ")  Search                                                " + B + "║");
        System.out.println(B + "║  " + A + "X" + R + ")  Cancel                                                " + B + "║");
        System.out.println(B + "║                                                            ║");
        System.out.println(B + "╚════════════════════════════════════════════════════════════╝" + R);
    }

    // displays transactions based of criteria
    public static void displayTransactions(List<Transaction> transactions) {
        String B = ConsoleUtilities.BORDER;
        String A = ConsoleUtilities.ACCENT;
        String M = ConsoleUtilities.MUTED;
        String R = ConsoleUtilities.RESET;
        NumberFormat money = NumberFormat.getCurrencyInstance();

        List<Transaction> sorted = new ArrayList<>(transactions);
        Collections.reverse(sorted);

        if (transactions.isEmpty()) {
            System.out.println(ConsoleUtilities.WARNING + "No transactions to display.");
            return;
        }

        int pageSize = 20;
        int currentPage = 0;
        int totalPages = (int) Math.ceil((double) transactions.size() / pageSize);

        boolean viewing = true;
        while (viewing) { // Loop to keep viewing so user can select pages
            System.out.println();
            ConsoleUtilities.spin("Loading page number: " + (currentPage + 1 ) + " ", 1000);

            // Header for transactions
            System.out.println(B + "\n╔══════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println(B + "║                                         ACCOUNT LEDGER                                               ║");
            System.out.println(B + "╠════════════╦══════════╦════════════════════════════════╦═══════════════════════════╦═════════════════╣");
            System.out.println(B + "║ " + A + "   DATE    " + B + "║ " + A + "  TIME   " + B + "║ " + A + "         DESCRIPTION           " + B + "║ " + A + "          VENDOR          " + B + "║ " + A + "     AMOUNT     " + B + "║");
            System.out.println(B + "╠════════════╬══════════╬════════════════════════════════╬═══════════════════════════╬═════════════════╣");

            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, transactions.size());
            for (int i = start; i < end; i++) {
                Transaction transaction = sorted.get(i);

                String amountColor = transaction.isDeposit() ? ConsoleUtilities.SUCCESS : ConsoleUtilities.DANGER;

                // Format for each line to print transaction
                System.out.printf(B + "║" + R + " %-11s" + B + "║" + M + " %-9s" + B + "║" + R + " %-30s " + B + "║" + R + " %-25s " + B + "║" + amountColor + "%16s" + B + " ║%n",
                        transaction.getDate(),
                        transaction.getTime(),
                        truncate(transaction.getDescription(), 29),
                        truncate(transaction.getVendor(), 24),
                        money.format(transaction.getAmount())
                );
            }

            // used to ensure border formatting is never off if page numbers have multiple digits
            int digits = String.valueOf(transactions.size()).length();
            String spacer = switch (digits) {
                case 1 -> "%79s║%n";
                case 2 -> "%78s║%n";
                case 3 -> "%77s║%n";
                default -> "%76s║%n";
            };


            // used to ensure border formatting is never off if page numbers have multiple digits
            int digits2 = String.valueOf(totalPages).length();
            digits2 += String.valueOf(currentPage + 1).length();
            String spacer2 = switch (digits2) {
                case 2 -> "%89s║%n";
                case 3 -> "%88s║%n";
                case 4 -> "%87s║%n";
                case 5 -> "%86s║%n";
                default -> "%85s║%n";
            };

            // Footer that displays page number, total transactions, and number of pages
            System.out.println(B + "╠════════════╩══════════╩════════════════════════════════╩═══════════════════════════╩═════════════════╣");
            System.out.printf(B + "║  " + A + "%d total transactions " + B + spacer, transactions.size(), "");
            System.out.printf(B + "║  " + A + "Page %d of %d" + B + spacer2, currentPage + 1, totalPages, "");
            System.out.println(B + "╚══════════════════════════════════════════════════════════════════════════════════════════════════════╝" + R);

            // Menu to prompt user if they want to go to next page or continue.
            switch (UserInput.promptForChar("[N]ext  [P]rev  [F]irst  [L]ast  [J]ump  [Q]uit ", "NPFLJQ")) {
                case 'N':
                    if (currentPage < totalPages - 1) currentPage++;
                    break;
                case 'P':
                    if (currentPage > 0) currentPage--;
                    break;
                case 'F':
                    currentPage = 0;
                    break;
                case 'L':
                    currentPage = totalPages - 1;
                    break;
                case 'J':
                    int targetPage = UserInput.promptForInt("Jump to page (1-" + totalPages + ") ", 1, totalPages);
                    currentPage = targetPage - 1;
                    break;
                case 'Q':
                    ConsoleUtilities.spin("Exiting...", 500);
                    viewing = false;
                    break;
            }

        }

    }

    // Method to shorten string by given amount (maxLen) and adds a ... at the end to indicate its shortened
    public static String truncate(String s, int maxLen) {
        if (s.length() <= maxLen) return s;
        return s.substring(0, maxLen - 3) + "... ";
    }

    // used in custom search to turn empty and null values to display the string "(any)"
    private static String displayValue(Object value) {
        if (value == null) return "(any)";
        if (value.toString().isEmpty()) return "(any)";
        return value.toString();
    }

}
