package com.geeksforless.view.menu;

import com.geeksforless.util.UserInputs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public abstract class Menu {

    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);
    private final String nameEntity;

    protected Menu(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    public final void run() {
        LOGGER.info("Select your option...");
        makeChoice();

    }

    private void runCrudNavigation() {
        LOGGER.info("If you want to create a {}, please enter 1.", nameEntity);
        LOGGER.info("If you want to update the {}, please enter 2.", nameEntity);
        LOGGER.info("If you want to delete the {}, please enter 3.", nameEntity);
        LOGGER.info("If you want to find by id the {}, please enter 4.", nameEntity);
        LOGGER.info("If you want to find all {}(s), please enter 5.", nameEntity);
    }

    private void makeChoice() {
        runCrudNavigation();
        runSpecializedNavigation();
        LOGGER.info("If you want to exit, please enter 0.");
        LOGGER.info("If you want to change data base, please enter 9.");
        String position = UserInputs.getUserInput();
        while (Objects.nonNull(position)) {
            executeChoose(position);
            position = UserInputs.getUserInput();
        }
    }
    protected abstract void runSpecializedNavigation();
    protected abstract void executeChoose(String position);

}
