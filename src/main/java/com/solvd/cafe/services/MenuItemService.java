package com.solvd.cafe.services;

import com.solvd.cafe.dao.ICafesDAO;
import com.solvd.cafe.dao.IMenuDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.CafesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.MenuDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.MenuItemDAO;
import com.solvd.cafe.models.Cafes;
import com.solvd.cafe.models.Menu;
import com.solvd.cafe.models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class MenuItemService {
    private static final Logger logger = LogManager.getLogger(MenuItemService.class);


    public static void createMenuItem() {
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        MenuItem menuItem = new MenuItem();
        Scanner scanner = new Scanner(System.in);
        logger.info("You need to choose menu type from the existing ones and enter this menu type id:");
        MenuItemService.showAllMenuTypes();
        menuItem.setMenuId(MenuItemService.pickMenuType());

        logger.info("Enter the name of position you want to add:");
        String positionName = scanner.nextLine();
        menuItem.setPositionName(positionName);

        logger.info("Enter the size of the serving portion. Only integer numbers:");
        int servingPortion = Integer.parseInt(scanner.nextLine());
        menuItem.setServingPortion(servingPortion);

        logger.info("Enter the measurement unit. ML or G:");
        String measurementUnit = scanner.nextLine();
        menuItem.setMeasurementUnit(measurementUnit);

        logger.info("Define the whole price to created item $ :");
        double price = Integer.parseInt(scanner.nextLine());
        menuItem.setPrice(price);
        menuItem.setMenuId(menuItem.getMenuId());
        menuItemDAO.create(menuItem);
        logger.info("Menu item successfuly created.");
       // menuItemDAO.update(menuItem);
    }
    public static void showAllMenuTypes() {
        logger.info("List of all existing menu types");
        IMenuDAO iMenuDAO = new MenuDAO();
        for (Menu menu : iMenuDAO.getAllRecords()) {
            logger.info(menu);
        }
    }
    public static int pickMenuType(){
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        // logger.info(iCafesDAO.getById(scanner.nextInt()));
        logger.info("Menu type selected");
        return id;
    }
}

