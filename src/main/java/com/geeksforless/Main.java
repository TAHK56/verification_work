package com.geeksforless;

import com.geeksforless.util.UserInputs;
import com.geeksforless.view.menu.MenuFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        LOGGER.info("Hello! This is the data base(file system) of company-souvenir!");
        LOGGER.info("For using this database you should choose:");
        LOGGER.info("1 - company.db(mainDb without company cannot be souvenir)");
        LOGGER.info("2 - souvenir.db");
        LOGGER.info("Something else - exit");
        LOGGER.info("Please, enter your choice");
        String answer = UserInputs.getUserInput();
        if (answer.equals("1") || answer.equals("2")) {
            MenuFactory.createMenu(answer).run();
        }
    }
}