package com.pluralsight.CommandLineRunner;

import com.pluralsight.model.Transaction;
import com.pluralsight.service.Ledger;
import com.pluralsight.service.Reports;
import com.pluralsight.cli.ui.Menus;
import com.pluralsight.cli.util.ConsoleUtilities;
import com.pluralsight.cli.util.UserInput;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class  CliRouter{

    // creates a new ledger Object to be used throughout the program
    private final Ledger ledger = new Ledger();

    // method called in AccountLedgerApp.java to run the program and control app flow
    public void run() {

        // Add an option to select a specific transaction.
        Menus.displaySplashScreen();
        ledger.loadTransactions();
        ConsoleUtilities.showProgressBar("Loading Account Ledger...", 3000);
        Menus.displayDashboard(ledger);
        handleHomeScreen();

    }

    // Handles home screen (calls menu and gives menu options)
    private void handleHomeScreen() {
        boolean onHomeScreen = true;
        while (onHomeScreen) {
            Menus.mainMenu();
            switch (UserInput.promptForChar("Your choice ", "DPLX")) {
                case 'D':
                    ConsoleUtilities.spin("Loading deposit menu...", 500);
                    addDeposit(ledger);
                    break;
                case 'P':
                    ConsoleUtilities.spin("Loading payment menu...", 500);
                    makePayment();
                    break;
                case 'L':
                    ConsoleUtilities.spin("Loading ledger...", 500);
                    handleLedgerScreen();
                    break;
                case 'X':
                    ConsoleUtilities.spin("Exiting...", 1000);
                    onHomeScreen = false;
                    break;
            }
        }
    }

    // Handles ledger screen (calls menu and gives menu options)
    private void handleLedgerScreen() {
        boolean onLedgerScreen = true;
        while (onLedgerScreen) {
            Menus.ledgerMenu();
            switch (UserInput.promptForChar("Your choice ", "ADPRH")) {
                case 'A':
                    ConsoleUtilities.spin("Loading all transactions....", 500);
                    Menus.displayTransactions(ledger.getLedger());
                    break;
                case 'D':
                    ConsoleUtilities.spin("Loading Deposits...", 500);
                    Menus.displayTransactions(ledger.getDeposits());
                    break;
                case 'P':
                    ConsoleUtilities.spin("Loading Payments...", 500);
                    Menus.displayTransactions(ledger.getPayments());
                    break;
                case 'R':
                    ConsoleUtilities.spin("Opening report screen...", 500);
                    handleReportsScreen();
                    break;
                case 'H':
                    onLedgerScreen = false;
                    ConsoleUtilities.spin("Returning to home...", 500);
            }
        }

    }

    // Handles report screen (calls menu and gives menu options)
    private void handleReportsScreen() {
        boolean onReportScreen = true;
        while (onReportScreen) {
            Menus.reportsMenu();
            switch (UserInput.promptForInt("Your choice ", 0, 6)) {
                case 1:
                    ConsoleUtilities.spin("Loading MTD report...", 500);
                    Reports.monthToDateReport(ledger.getLedger());
                    break;
                case 2:
                    ConsoleUtilities.spin("Loading previous month report...", 500);
                    Reports.previousMonthReport(ledger.getLedger());
                    break;
                case 3:
                    ConsoleUtilities.spin("Loading YTD report...", 500);
                    Reports.yearToDate(ledger.getLedger());
                    break;
                case 4:
                    ConsoleUtilities.spin("Loading previous year report...", 500);
                    Reports.previousYearReport(ledger.getLedger());
                    break;
                case 5:
                    ConsoleUtilities.spin("Loading...", 500);
                    Reports.searchByVendor(ledger.getLedger());
                    break;
                case 6:
                    ConsoleUtilities.spin("Loading custom search menu...", 500);
                    Reports.customSearch(ledger.getLedger());
                    break;
                case 0:
                    onReportScreen = false;
            }
        }
    }

    // Method to add a new deposit
    private void addDeposit(Ledger ledger) {

        System.out.println("\n── Add Deposit ──");
        String description = UserInput.promptForString("Description ");
        String vendor = UserInput.promptForString("Vendor ");
        double amount = UserInput.promptForDouble("Amount ", 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        boolean switchRunning = true;

        System.out.println(ConsoleUtilities.BOLD + "\nConfirm:");
        System.out.println(ConsoleUtilities.ACCENT + "  Date        " + ConsoleUtilities.RESET + date);
        System.out.println(ConsoleUtilities.ACCENT + "  Time        " + ConsoleUtilities.RESET + time);
        System.out.println(ConsoleUtilities.ACCENT + "  Description " + ConsoleUtilities.RESET + description);
        System.out.println(ConsoleUtilities.ACCENT + "  Vendor      " + ConsoleUtilities.RESET + vendor);
        System.out.println(ConsoleUtilities.ACCENT + "  Amount      " + ConsoleUtilities.BRIGHT_GREEN + money.format(amount) + ConsoleUtilities.RESET);

        // Switch to ensure user wants to confirm the adding of a deposit
        while (switchRunning) {
            switch (UserInput.promptForChar("\nSave this transaction? [Y/N] ", "YN")) {
                case 'Y':
                    ConsoleUtilities.showProgressBar("Adding deposit...", 3000);
                    ledger.saveTransaction(new Transaction(date, time, description, vendor, amount));
                    ConsoleUtilities.spin("Deposit saved returning home...", 1000);
                    switchRunning = false;
                    break;
                case 'N':
                    ConsoleUtilities.spin("Canceling...", 3000);
                    System.out.println("Transaction Canceled. Returning to home...");
                    switchRunning = false;
                    break;

            }
        }
    }

    // Method to add a new payment
    private void makePayment() {

        System.out.println("\n── Make Payment ──");
        String description = UserInput.promptForString("Description: ");
        String vendor = UserInput.promptForString("Vendor: ");
        double amount = -UserInput.promptForDouble("Amount: ", 1);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        NumberFormat money = NumberFormat.getCurrencyInstance();
        boolean switchRunning = true;

        System.out.println("Confirm:");
        System.out.println(ConsoleUtilities.ACCENT + "  Date:        " + ConsoleUtilities.RESET + date);
        System.out.println(ConsoleUtilities.ACCENT + "  Time:        " + ConsoleUtilities.RESET + time);
        System.out.println(ConsoleUtilities.ACCENT + "  Description: " + ConsoleUtilities.RESET + description);
        System.out.println(ConsoleUtilities.ACCENT + "  Vendor:      " + ConsoleUtilities.RESET + vendor);
        System.out.println(ConsoleUtilities.ACCENT + "  Amount:      " + ConsoleUtilities.BRIGHT_RED + money.format(amount) + ConsoleUtilities.RESET);

        // Switch to ensure user wants to confirm the adding of a deposit
        while (switchRunning) {
            switch (UserInput.promptForChar("\nSave this transactions? [Y/N] ", "YN")) {
                case 'Y':
                    ConsoleUtilities.showProgressBar("Adding Payment...", 3000);
                    ledger.saveTransaction(new Transaction(date, time, description, vendor, amount));
                    ConsoleUtilities.spin("Payment saved returning home...", 1000);
                    switchRunning = false;
                    break;
                case 'N':
                    ConsoleUtilities.spin("Canceling...", 3000);
                    System.out.println("Transaction Canceled. Returning to home...");
                    switchRunning = false;
                    break;
            }
        }

    }

}
