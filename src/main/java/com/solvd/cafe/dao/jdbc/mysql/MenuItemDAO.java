package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IMenuItemDAO;
import com.solvd.cafe.models.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuItemDAO implements IMenuItemDAO {
    private static final Logger logger = LogManager.getLogger(MenuItemDAO.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INSERT = "INSERT INTO menu_item" +
            "(menu_item.position_name, " +
            "menu_item.serving_portion, " +
            "menu_item.measurement_unit, " +
            "menu_item.price, " +
            "menu_item.menu_id)\n  " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE menu_item SET " +
            "menu_item.position_name, " +
            "menu_item.serving_portion, " +
            "menu_item.measurement_unit, " +
            "menu_item.price, " +
            "menu_item.menu_id WHERE " +
            "menu_item.item_id=?";
    private static final String DELETE = "DELETE FROM menu_item WHERE item_id=?";
    private static final String GET_BY_ID = "SELECT * FROM menu_item WHERE id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM menu_item";

    @Override
    public void create(MenuItem object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getPositionName());
            ps.setInt(2, object.getServingPortion());
            ps.setString(3, object.getMeasurementUnit());
            ps.setDouble(4, object.getPrice());
            ps.setInt(5, object.getMenuId());
            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            logger.info("id: " + id + " object: " + object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public void update(MenuItem update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            logger.info("Details to the new position in menu (including serving portion): ");
            ps.setString(1, scanner.nextLine());
            ps.setInt(2, scanner.nextInt());
            logger.info("Measurement unit: ");
            ps.setString(3, scanner.nextLine());
            logger.info("Price setting: ");
            ps.setDouble(4, scanner.nextInt());
            logger.info("Unique id setting: ");
            ps.setInt(5, scanner.nextInt());
            ps.setInt(6, update.getId());
            scanner.close();
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public MenuItem getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt("item_id"));
                menuItem.setPositionName(rs.getString("position_name"));
                menuItem.setServingPortion(rs.getInt("serving_portion"));
                menuItem.setMeasurementUnit(rs.getString("measurement_unit"));
                menuItem.setPrice(rs.getDouble("price"));
                menuItem.setMenuId(rs.getInt("menu_id"));
                return menuItem;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<MenuItem> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<MenuItem> menuItems = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt("item_id"));
                menuItem.setPositionName(rs.getString("position_name"));
                menuItem.setServingPortion(rs.getInt("serving_portion"));
                menuItem.setMeasurementUnit(rs.getString("measurement_unit"));
                menuItem.setPrice(rs.getDouble("price"));
                menuItem.setMenuId(rs.getInt("menu_id"));
                menuItems.add(menuItem);

                return menuItems;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
