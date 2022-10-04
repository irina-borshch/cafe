package com.solvd.cafe.services;

import com.solvd.cafe.dao.jdbc.mysql.Impl.CafeAddressesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.CafesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.FranchisesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.MenuDAO;
import com.solvd.cafe.models.*;
import com.solvd.cafe.models.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class CafeCreationService {
    private static final Logger logger = LogManager.getLogger(CafeCreationService.class);
   /*private FranchisesDAO franchisesDAO = new FranchisesDAO();
   private CafeAddressesDAO cafeAddressesDAO = new CafeAddressesDAO();
    private CafesDAO cafesDAO = new CafesDAO();*/

    public  static  void createCafe() {
         FranchisesDAO franchisesDAO = new FranchisesDAO();
         CafeAddressesDAO cafeAddressesDAO = new CafeAddressesDAO();
         CafesDAO cafesDAO = new CafesDAO();
        MenuDAO menuDAO = new MenuDAO();
        Franchises franchises = new Franchises();
        CafeAddresses cafeAddresses = new CafeAddresses();
        Cafes cafe = new Cafes();
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        logger.info("Enter new franchise name:");
        String name = scanner.nextLine();
        franchises.setName(name);
        franchisesDAO.create(franchises);
        //franchisesDAO.getById(5);
        logger.info("Congrats, you've created a new franchise!");

        boolean incorrectCountryName = true;
        while (incorrectCountryName) {
            logger.info("Enter country where you want to open cafe:");
            String countryName = scanner.nextLine();
            if (countryName.matches("[A-Z][a-z]+")) {
                cafeAddresses.setCountry(countryName);
                incorrectCountryName = false;
            } else {
                logger.error("Country name should begin with capital letter and doesn't include numbers");
            }
        }
        boolean incorrectCityName = true;
        while (incorrectCityName) {
            logger.info("Enter city:");
            String city = scanner.nextLine();
            if (city.matches("[A-Z][a-z]+")) {
                cafeAddresses.setCity(city);
                incorrectCityName = false;
            } else {
                logger.error("City name should begin with capital letter and doesn't include numbers");
            }
        }
        boolean incorrectStreetName = true;
        while (incorrectStreetName) {
            logger.info("Write name of the preferred street:");
            String streetName = scanner.nextLine();
            if (streetName.matches("[A-Z][a-z]+")) {
                cafeAddresses.setStreetName(streetName);
                incorrectStreetName = false;
            } else {
                logger.error("Name of the street should begin with capital letter and doesn't include numbers");
            }
        }

        logger.info("Write number of building:");
        int buildingNum = Integer.parseInt(scanner.nextLine());
        cafeAddresses.setBuildingNum(buildingNum);
        cafeAddresses.setFranchisesId(franchises.getId());
        cafeAddressesDAO.create(cafeAddresses);
        //cafeAddressesDAO.getAllRecords();
        logger.info("Congrats, you've created new Cafe location!");

        logger.info("Enter a menu type which will be in your cafe: ");
        String nameMenu = scanner.nextLine();
        menu.setMenuType(nameMenu);
        menuDAO.create(menu);

        logger.info("Last step - creation of Cafe name. Write Cafe name!");
        String cafeName = scanner.nextLine();
        cafe.setCafeName(cafeName);
        cafe.setCafeAddressesId(cafeAddresses.getId());
        cafe.setMenuId(menu.getId());
        //cafe.setMenuId();
        cafesDAO.create(cafe);
        //cafesDAO.update(cafe);
    }
}
