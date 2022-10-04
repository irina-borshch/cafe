package com.solvd.cafe.services;

import com.solvd.cafe.dao.ICafesDAO;
import com.solvd.cafe.dao.IEmployeesDAO;
import com.solvd.cafe.dao.IMenuItemDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.CafesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.EmployeesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.MenuItemDAO;
import com.solvd.cafe.models.Cafes;
import com.solvd.cafe.models.Employees;
import com.solvd.cafe.models.MenuItem;
import org.apache.logging.log4j.LogManager;
import com.solvd.cafe.services.CafeCreationService;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ManagementPage {
    private static final Logger logger = LogManager.getLogger(ManagementPage.class);

    public static void managing() {

        final Scanner scanner = new Scanner(System.in);
        logger.info("You are on management page! Choose a needed option:" + '\n' +
                "1) Operate with cafe." + '\n' +
                "2) Operate with staff." + '\n' +
                "3) Operate with menu." + '\n' +
                "0) Exit to the main Cafe page" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                ManagementPage.operateWithCafe();
                break;
            case 2:
                ManagementPage.operateWithStaff();
                break;
            case 3:
                ManagementPage.operateWithMenu();
                break;
            case 0:
                MainCafe.begin();
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }

    public static void operateWithCafe() {
        logger.info("Which process do you want to make?:" + '\n' +
                "1) Open a new cafe." + '\n' +
                "2) Show all existing cafes." + '\n' +
                "3) Close existing cafe." + '\n' +
                "0) Exit to the previous page" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                CafeCreationService.createCafe();
                ManagementPage.operateWithCafe();
                break;
            case 2:
                ManagementPage.showAllCafes();
                ManagementPage.operateWithCafe();
                break;
            case 3:
                logger.info("Choose from existing cafe the one you want to close and enter it's id:");
                ManagementPage.showAllCafes();
                ManagementPage.deleteCafe();
                ManagementPage.operateWithCafe();

                break;
            case 0:
                ManagementPage.managing();
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }

    public static void operateWithStaff() {
        logger.info("Which process do you want to make?:" + '\n' +
                "1) Hire a new employee." + '\n' +
                "2) Show all employees." + '\n' +
                "3) To dismiss an employee." + '\n' +
                "0) Exit to the previous page" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                EmployeeCreationService.createEmployee();
                ManagementPage.operateWithStaff();
                break;
            case 2:
                ManagementPage.showAllEmployees();
                ManagementPage.operateWithStaff();
                break;
            case 3:
                logger.info("Choose from employees the one you want to dismiss and enter his/her id:");
                ManagementPage.showAllEmployees();
                ManagementPage.deleteEmployee();
                ManagementPage.operateWithStaff();
                break;
            case 0:
                ManagementPage.managing();
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }

    public static void operateWithMenu() {
        logger.info("Which process do you want to make?:" + '\n' +
                "1) Create a new menu item." + '\n' +
                "2) Show all Menu items." + '\n' +
                "3) Remove an item from the menu." + '\n' +
                "0) Exit to the previous page" + '\n' + '\n' +
                "Make your choice:");
        int choice;
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                MenuItemService.createMenuItem();
                ManagementPage.operateWithMenu();
                break;
            case 2:
                ManagementPage.showAllPositions();
                ManagementPage.operateWithMenu();
                break;
            case 3:
                logger.info("Check the menu and choose position which you want to remove. Enter it's id: ");
                ManagementPage.showAllPositions();
                ManagementPage.deletePosition();
                ManagementPage.operateWithMenu();
                break;
            case 0:
                ManagementPage.managing();
                return;
            default:
                logger.info("Invalid data entry. Choose correct option.");

        }
    }

    public static void showAllCafes() {
        logger.info("List of all existing cafes");
        ICafesDAO iCafesDAO = new CafesDAO();
        for (Cafes cafes : iCafesDAO.getAllRecords()) {
            logger.info(cafes);
        }
    }

    public static void showAllEmployees() {
        logger.info("List of all employees");
        IEmployeesDAO iEmployeesDAO = new EmployeesDAO();
        for (Employees employees : iEmployeesDAO.getAllRecords()) {
            logger.info(employees);
        }
    }

    public static void showAllPositions() {
        logger.info("List of all menu positions: ");
        IMenuItemDAO iMenuItemDAO = new MenuItemDAO();
        for (MenuItem menuItem : iMenuItemDAO.getAllRecords()) {
            logger.info(menuItem);
        }
    }

    public static void deleteCafe() {
        ICafesDAO iCafesDAO = new CafesDAO();
        Scanner scanner = new Scanner(System.in);
        iCafesDAO.delete(scanner.nextInt());
    }
    public static void deleteEmployee() {
        IEmployeesDAO iEmployeesDAO = new EmployeesDAO();
        Scanner scanner = new Scanner(System.in);
        iEmployeesDAO.delete(scanner.nextInt());
        logger.info("You've dismissed an employee :( ");
    }
    public static void deletePosition() {
        IMenuItemDAO iMenuItemDAO = new MenuItemDAO();
        Scanner scanner = new Scanner(System.in);
        iMenuItemDAO.delete(scanner.nextInt());
        logger.info("This item is no longer available in the menu. ");
    }
}


