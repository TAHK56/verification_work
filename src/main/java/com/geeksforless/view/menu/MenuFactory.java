package com.geeksforless.view.menu;

public class MenuFactory {

    private MenuFactory() {}
    public static Menu createMenu(String position) {
        return switch (position) {
            case "1" -> new CompanyMenu();
            case "2" -> new SouvenirMenu();
            default -> throw new IllegalArgumentException();
        };
    }
}
