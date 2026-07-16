package com.pluralsight.cli;

import com.pluralsight.CommandLineRunner.CliRouter;

public class CliRunner {

    public static void main(String[] args) {
        CliRouter cliRouter = new CliRouter();
        cliRouter.run();
    }
}
