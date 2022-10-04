package com.solvd.cafe.services;

import com.solvd.cafe.dao.IBookingsDAO;

import com.solvd.cafe.dao.ITablesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.BookingsDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.GuestsDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.TablesDAO;
import com.solvd.cafe.models.Bookings;
import com.solvd.cafe.models.Guests;
import com.solvd.cafe.models.Tables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Scanner;

public class ClientPage {
    private static final Logger logger = LogManager.getLogger(ClientPage.class);

    public static void managingClient() {
        final Scanner scanner = new Scanner(System.in);
        logger.info("You are on Guest servicing page! Choose a needed option:" + '\n' +
                "1) Choose a table and enter the table id  to create a booking for a guest:" + '\n' +
                "2) To operate with Guest's information for further bill creating, please fill the required information" + '\n' +
                "3) Guest want to pay for the order." + '\n' +
                "0) Exit to the main Cafe page" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                logger.info("Choose table from the list  of existing tables and enter it's id:");
                ClientPage.createBookings();
                ClientPage.managingClient();
                break;
            case 2:
                ClientPage.fullGuestInfo();
                ClientPage.managingClient();
                break;
            case 3:
                logger.info("Guide the guest to to the settlement counter\n");
                ClientPage.managingClient();
                break;
            case 0:
                MainCafe.begin();
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }

    public static void createBookings() {
        BookingsDAO bookingsDAO = new BookingsDAO();
        Bookings bookings = new Bookings();
        ClientPage.choosingTable();
        bookings.setTablesId(ClientPage.pickTable());
        ClientPage.time();
        bookingsDAO.create(bookings);
    }

    public static void choosingTable() {
        ITablesDAO iTableDAO = new TablesDAO();
        for (Tables table : iTableDAO.getAllRecords()) {
            logger.info(table);

        }
    }

    public static int pickTable() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        logger.info("Table selected");
        return id;
    }

    public static void time() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Please enter the date and time of the booking in format: YYYY-MM-DD  hh:tt:ss.0 ");
        String time = scanner.nextLine();
        Timestamp timestamp = Timestamp.valueOf(time);
        logger.info(timestamp);
    }

    public static void fullGuestInfo() {
        GuestsDAO guestsDAO = new GuestsDAO();
        Guests guest = new Guests();
        Scanner scanner = new Scanner(System.in);
        //guest.setBookingsId(ClientPage.createBookings());
        boolean incorrecName = true;
        while (incorrecName) {
            logger.info("Enter first name of guest:");
            String name = scanner.nextLine();
            if (name.matches("[A-Z][a-z]+")) {
                guest.setName(name);
                incorrecName = false;
            } else {
                logger.error("Guest's name should begin with capital letter and doesn't include numbers");
            }
        }
        boolean incorrecLastName = true;
        while (incorrecLastName) {
            logger.info("Enter last name of eguest:");
            String lastName = scanner.nextLine();
            if (lastName.matches("[A-Z][a-z]+")) {
                guest.setLastName(lastName);
                incorrecLastName = false;
            } else {
                logger.error("Employee's last name should begin with capital letter and doesn't include numbers");
            }
            ClientPage.showBookingsList();
            guest.setBookingsId(ClientPage.pickBooking());
            logger.info("Guest info successfuly filled");
            guest.setId(guest.getId());
            guestsDAO.create(guest);
            guestsDAO.update(guest);
        }
    }

    public static void showBookingsList() {
        logger.info("Find guest's booking in a list and enter required id:");
        IBookingsDAO iBookingsDAO = new BookingsDAO();
        for (Bookings booking : iBookingsDAO.getAllRecords()) {
            logger.info(booking);
        }
    }

    public static int pickBooking() {
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }
}

