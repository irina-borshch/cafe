package com.solvd.cafe.dao.jdbc.mysql.Impl;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.ICafeAddressesDAO;
import com.solvd.cafe.models.CafeAddresses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CafeAddressesDAO implements ICafeAddressesDAO {
    private static final Logger logger = LogManager.getLogger(CafeAddressesDAO.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INSERT = "INSERT INTO cafe_addresses" +
            "(cafe_addresses.street_name, " +
            "cafe_addresses.building_num, " +
            "cafe_addresses.city, " +
            "cafe_addresses.franchises_id, " +
            "cafe_addresses.country)\n " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cafe_addresses SET " +
            "(cafe_addresses.street_name=?, " +
            "cafe_addresses.building_num=?, " +
            "cafe_addresses.city=?, " +
            "cafe_addresses.franchises_id=?, " +
            "cafe_addresses.country=? WHERE " +
            "cafe_addresses.address_id=?";
    private static final String DELETE = "DELETE FROM cafe_addresses WHERE address_id=?";
    private static final String GET_BY_ID = "SELECT * FROM cafe_addresses WHERE address_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM cafe_addresses";

    @Override
    public void create(CafeAddresses object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getStreetName());
            ps.setInt(2, object.getBuildingNum());
            ps.setString(3, object.getCity());
            ps.setInt(4, object.getFranchisesId());
            ps.setString(5, object.getCountry());

            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            object.setId(id);
            logger.info("id: " + id + " object: " + object);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public void update(CafeAddresses update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            logger.info("New street name and building number: ");
            ps.setString(1, scanner.nextLine());
            ps.setInt(2, scanner.nextInt());
            logger.info("Added new country, city and franchise id: ");
            ps.setString(3, scanner.nextLine());
            ps.setInt(4, scanner.nextInt());
            ps.setString(5, scanner.nextLine());
            logger.info("Created new address id: ");
            ps.setInt(6, scanner.nextInt());
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
    public CafeAddresses getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CafeAddresses addresses = new CafeAddresses();
                addresses.setId(rs.getInt("address_id"));
                addresses.setStreetName(rs.getString("street_name"));
                addresses.setBuildingNum(rs.getInt("building_num"));
                addresses.setCity(rs.getString("city"));
                addresses.setFranchisesId(rs.getInt("franchises_id"));
                addresses.setCountry(rs.getString("country"));
                return addresses;
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
    public List<CafeAddresses> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<CafeAddresses> cafeAddresses = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CafeAddresses addresses = new CafeAddresses();
                addresses.setId(rs.getInt("address_id"));
                addresses.setStreetName(rs.getString("street_name"));
                addresses.setBuildingNum(rs.getInt("building_num"));
                addresses.setCity(rs.getString("city"));
                addresses.setFranchisesId(rs.getInt("franchises_id"));
                cafeAddresses.add(addresses);
            }
            return cafeAddresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}

