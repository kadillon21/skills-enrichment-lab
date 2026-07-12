package com.pluralsight;

import com.pluralsight.CommandLineRunner.CliRouter;

public class AccountLedgerApp {

    public static void main(String[] args) {
        CliRouter CliRouter = new CliRouter();
        CliRouter.run();
    }
}
