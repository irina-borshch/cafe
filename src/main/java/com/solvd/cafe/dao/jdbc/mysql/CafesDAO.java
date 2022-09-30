package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.ICafesDAO;
import com.solvd.cafe.models.Cafes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafesDAO implements ICafesDAO {
    private static final Logger logger = LogManager.getLogger(CafesDAO.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INSERT = "INSERT INTO cafes" +
            "(cafes.cafe_addresses_id, " +
            "cafes.menu_id, " +
            "cafes.cafe_name)\n  " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE cafes SET " +
            "cafes.cafe_addresses_id, " +
            "cafes.menu_id, " +
            "cafes.cafe_name WHERE " +
            "cafes.cafe_id=?";
    private static final String DELETE = "DELETE FROM cafes WHERE address_id=?";
    private static final String GET_BY_ID = "SELECT * FROM cafes WHERE id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM cafes";

    @Override
    public void create(Cafes object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getCafeAddressesId());
            ps.setInt(2, object.getMenuId());
            ps.setString(3, object.getCafeName());

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
    public void update(Cafes cafes) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            logger.info("Added new cafe address id and menu id: ");
            ps.setInt(1, scanner.nextInt());
            ps.setInt(2, scanner.nextInt());
            logger.info("Created new cafe name: ");
            ps.setString(3, scanner.nextLine());
            logger.info("Generated new cafe id: ");
            ps.setInt(4, scanner.nextInt());
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
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Cafes getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cafes cafe = new Cafes();
                cafe.setId(rs.getInt("cafe_id"));
                cafe.setCafeAddressesId(rs.getInt("cafe_addresses_id"));
                cafe.setMenuId(rs.getInt("menu_id"));
                cafe.setCafeName(rs.getString("cafe_name"));
                return cafe;
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
    public List<Cafes> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Cafes> cafes = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cafes cafe = new Cafes();
                cafe.setId(rs.getInt("cafe_id"));
                cafe.setCafeAddressesId(rs.getInt("cafe_addresses_id"));
                cafe.setMenuId(rs.getInt("menu_id"));
                cafe.setCafeName(rs.getString("cafe_name"));
                cafes.add(cafe);
            }
            return cafes;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
        return null;
    }
}
