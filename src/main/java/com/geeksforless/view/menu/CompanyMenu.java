package com.geeksforless.view.menu;

import com.geeksforless.view.controller.CompanyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyMenu extends Menu {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMenu.class);
    private static final CompanyController controller = new CompanyController();

    public CompanyMenu() {
        super("company");
    }

    @Override
    protected void runSpecializedNavigation() {
        LOGGER.info("If you want to find all companies lower by price, please enter 6.");
        LOGGER.info("If you want to find all companies by souvenir id, please enter 7.");
        LOGGER.info("If you want to find all companies by date  of issue, please enter 8.");
    }

    @Override
    protected void executeChoose(String position) {
        switch (position) {
            case "1" -> controller.create();
            case "2" -> controller.update();
            case "3" -> controller.delete();
            case "4" -> controller.findById();
            case "5" -> controller.findAll();
            case "6" -> controller.findAllLowerByPrice();
            case "7" -> controller.findAllBySouvenirId();
            case "8" -> controller.findAllByYearOfIssue();
            case  "9" -> new SouvenirMenu().run();
            case "0" -> System.exit(0);
            default -> LOGGER.info("You should have inputted correct answer...");
        }
        run();
    }
}
