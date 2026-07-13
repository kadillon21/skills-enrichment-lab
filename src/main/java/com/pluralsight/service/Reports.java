package com.pluralsight.service;


import com.pluralsight.model.SearchCriteria;
import com.pluralsight.model.Transaction;
import com.pluralsight.cli.util.ConsoleUtilities;
import com.pluralsight.cli.util.UserInput;
import com.pluralsight.cli.ui.Menus;
import com.pluralsight.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;



public class Reports {
    //Injecting Repo
    private TransactionRepository transactionRepository;

    //Constructor
    public Reports(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }



    // displays transaction menu that shows transactions for the current month
    public static List<Transaction> monthToDateReport(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);

        return transactions.stream()
                .filter(transaction ->
                        !transaction.getDate().isBefore(startOfMonth)
                                && !transaction.getDate().isAfter(now))
                .toList();
    }

    // displays transaction menu that shows transactions for the previous month
    public static void previousMonthReport(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate previousMonthStart = now.minusMonths(1).withDayOfMonth(1);
        LocalDate previousMonthEnd = previousMonthStart.withDayOfMonth(previousMonthStart.lengthOfMonth());
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousMonthStart)
                        && !transaction.getDate().isAfter(previousMonthEnd)).toList();
        Menus.displayTransactions(filtered);
    }

    // displays transaction menu that shows transactions for the current year
    public static void yearToDate(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate previousYearStart = now.withMonth(1).withDayOfMonth(1);
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousYearStart)
                        && !transaction.getDate().isAfter(now)).toList();
        Menus.displayTransactions(filtered);
    }

    // displays transaction menu that shows transactions for the previous year
    public static void previousYearReport(List<Transaction> transactions) {
        LocalDate now = LocalDate.now();
        LocalDate previousYearStart = now.minusYears(1).withMonth(1).withDayOfMonth(1);
        LocalDate previousYearEnd = previousYearStart.withMonth(12).withDayOfMonth(31);
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> !transaction.getDate().isBefore(previousYearStart)
                        && !transaction.getDate().isAfter(previousYearEnd)).toList();
        Menus.displayTransactions(filtered);
    }

    // displays transaction menu that shows transactions from chosen vendor
    public static void searchByVendor(List<Transaction> transactions) {
        String comparator = UserInput.promptForString("Vendor ");
        List<Transaction> filtered = transactions.stream().filter(
                transaction -> transaction.getVendor().toLowerCase().contains(comparator.toLowerCase())).toList();

        if (filtered.isEmpty()) {
            System.out.println("\nNo transactions to display");
        } else {
            Menus.displayTransactions(filtered);
        }
    }

    // Pulls up custom search menu and allows user to edit search options
    public static void customSearch(List<Transaction> transactions) {

        SearchCriteria criteria = new SearchCriteria();
        boolean editing = true;

        while (editing) {
            Menus.customSearchOptions(criteria);
            switch (UserInput.promptForChar("Your choice ", "ESX")) {
                case 'E':
                    ConsoleUtilities.spin("Opening editing menu...", 500);
                    editCriteria(criteria);
                    break;
                case 'S':
                    ConsoleUtilities.spin("Searching...", 500);
                    editing = false;
                    break;
                case 'X':
                    ConsoleUtilities.spin("Canceling search...", 500);
                    return;
            }
        }

        List<Transaction> filtered = transactions.stream().filter(criteria::matches).toList();
        Menus.displayTransactions(filtered);


    }

    // Method to allow user to edit specific search criteria
    public static void editCriteria(SearchCriteria criteria) {
        Menus.editValuesMenu();
        switch (UserInput.promptForInt("What would you like to change? ", 1, 7)) {
            case 1:
                criteria.setStartDate(UserInput.promptForDate("Start date [YYYY-MM-DD] "));
                ConsoleUtilities.spin("Saving search criteria...", 500);
                break;
            case 2:
                criteria.setEndDate(UserInput.promptForDate("End date [YYYY-MM-DD] "));
                ConsoleUtilities.spin("Saving search criteria...", 500);
                break;
            case 3:
                criteria.setDescription(UserInput.promptForString("Description "));
                ConsoleUtilities.spin("Saving search criteria...", 500);
                break;
            case 4:
                criteria.setVendor(UserInput.promptForString("Vendor name "));
                ConsoleUtilities.spin("Saving search criteria...", 500);
                break;
            case 5:
                criteria.setMinAmount(UserInput.promptForDouble("Min Value ", 1));
                ConsoleUtilities.spin("Saving search criteria...", 500);
                break;
            case 6:
                criteria.setMaxAmount(UserInput.promptForDouble("Max Value", 1));
                ConsoleUtilities.spin("Saving search criteria...", 500);
                break;
            case 7:
                Menus.transTypeMenu();
                switch (UserInput.promptForChar("Your choice ", "ADP")){
                    case 'A':
                        criteria.setTransType("(any)");
                        break;
                    case 'D':
                        criteria.setTransType("Deposits");
                        break;
                    case 'P':
                        criteria.setTransType("Payments");
                        break;
                }
                break;

        }
    }
}
