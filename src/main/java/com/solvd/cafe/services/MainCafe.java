package com.solvd.cafe.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MainCafe {
    private static final Logger logger = LogManager.getLogger(MainCafe.class);

    public static void main(String[] args) {
        MainCafe.begin();
    }
    public  static void begin() {
        final Scanner scanner = new Scanner(System.in);

        logger.info("Welcome! You on the Cafe page. Choose what you want to do:" + '\n' +
                "1) Guest servicing." + '\n' +
                "2) Cafe improvement." + '\n' +
                "0) Exit" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                ClientPage.managingClient();
                break;
            case 2:
                ManagementPage.managing();
                break;
            case 0:
                logger.info("Exit from the Cafe page.");
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }

}
