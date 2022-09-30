package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IMenuDAO;
import com.solvd.cafe.models.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDAO implements IMenuDAO {
    private static final Logger logger = LogManager.getLogger(MenuDAO.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INSERT = "INSERT INTO menu" +
            "(menu.menu_type)\n " +
            "VALUES (?)";
    private static final String UPDATE = "UPDATE menu SET " +
            "menu.menu_type WHERE " +
            "menu.menu_id=?";
    private static final String DELETE = "DELETE FROM menu WHERE menu_id=?";
    private static final String GET_BY_ID = "SELECT * FROM menu WHERE id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM menu";


    @Override
    public void create(Menu object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getMenuType());

            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            logger.info("id: " + id + " object: " + object);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }

    }

    @Override
    public void update(Menu menu) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            logger.info("Added a new menu type: ");
            ps.setString(1, scanner.nextLine());
            logger.info("Generated new menu id: ");
            ps.setInt(2, scanner.nextInt());
            scanner.close();
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
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
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public Menu getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Menu menu = new Menu();
                menu.setId(rs.getInt("menu_id"));
                menu.setMenuType(rs.getString("menu_type"));
                return menu;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }

    @Override
    public List<Menu> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Menu> menu = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Menu mn = new Menu();
                mn.setId(rs.getInt("menu_id"));
                mn.setMenuType(rs.getString("menu_type"));
                menu.add(mn);
            }
            return menu;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
