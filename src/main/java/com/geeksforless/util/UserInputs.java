package com.geeksforless.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class UserInputs {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputs.class);

    private UserInputs() {
        throw new AssertionError("You cannot create this class...");
    }

    public static String getUserInput() {
        String userInput = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            userInput = reader.readLine();
        } catch (IOException ex) {
            LOGGER.error("Something have gone wrong..{}", ex.getMessage());
        }
        return userInput;
    }
}
