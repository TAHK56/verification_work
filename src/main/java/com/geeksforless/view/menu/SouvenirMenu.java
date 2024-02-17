package com.geeksforless.view.menu;

import com.geeksforless.view.controller.SouvenirController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SouvenirMenu extends Menu {

    private static final Logger LOGGER = LoggerFactory.getLogger(SouvenirMenu.class);
    private static final SouvenirController controller = new SouvenirController();

    public SouvenirMenu() {
        super("souvenir");
    }

    @Override
    protected void runSpecializedNavigation() {
        LOGGER.info("If you want to find all souvenirs  by company id, please enter 6.");
        LOGGER.info("If you want to find all souvenirs by country, please enter 7.");
        LOGGER.info("If you want to find all souvenirs by year, please enter 8.");
    }

    @Override
    protected void executeChoose(String position) {
        switch (position) {
            case "1" -> controller.create();
            case "2" -> controller.update();
            case "3" -> controller.delete();
            case "4" -> controller.findById();
            case "5" -> controller.findAll();
            case "6" -> controller.findAllByCompanyId();
            case "7" -> controller.findAllByCountry();
            case "8" -> controller.findAllByYear();
            case "9" -> new CompanyMenu().run();
            case "0" -> System.exit(0);
            default -> LOGGER.info("You should have inputted correct answer...");
        }
        run();
    }
}
