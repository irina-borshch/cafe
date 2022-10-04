package com.solvd.cafe.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class BookingCreationService {
    private static final Logger logger = LogManager.getLogger(BookingCreationService.class);
    public static void managingClient() {
        final Scanner scanner = new Scanner(System.in);
        logger.info("1) Choose cafe to create a booking:" + '\n' +
                "2) Choose table." + '\n' +
                "2) Guest already have booking. Guide to a seating place" + '\n' +
                "3) Guest want to pay for the order." + '\n' +
                "0) Exit to the main Cafe page" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 0:
                MainCafe.begin();
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }
}
