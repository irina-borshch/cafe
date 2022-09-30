package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IGuestsDAO;
import com.solvd.cafe.models.Guests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuestsDAO implements IGuestsDAO {
    private static final Logger logger = LogManager.getLogger(GuestsDAO.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INSERT = "INSERT INTO guests" +
            "(guests.name, " +
            "guests.last_name, " +
            "guests.bookings_id)\n  " +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE guests SET " +
            "guests.name, " +
            "guests.last_name, " +
            "guests.bookings_id WHERE " +
            "guests.guest_id=?";
    private static final String DELETE = "DELETE FROM guests WHERE guest_id=?";
    private static final String GET_BY_ID = "SELECT * FROM guests WHERE id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM guests";

    @Override
    public void create(Guests object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.setString(2, object.getLastName());
            ps.setInt(3, object.getBookingsId());
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
    public void update(Guests guests) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            logger.info("New guest's name: ");
            ps.setString(1, scanner.nextLine());
            logger.info("New guest's last name: ");
            ps.setString(2, scanner.nextLine());
            logger.info("Guest's booking id: ");
            ps.setInt(3, scanner.nextInt());
            logger.info("New guest's id: ");
            ps.setInt(4, scanner.nextInt());
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
    public Guests getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Guests guest = new Guests();
                guest.setId(rs.getInt("guest_id"));
                guest.setName(rs.getString("name"));
                guest.setLastName(rs.getString("last_name"));
                guest.setBookingsId(rs.getInt("bookings_id"));
                return guest;
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
    public List<Guests> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Guests> guests = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Guests guest = new Guests();
                guest.setId(rs.getInt("guest_id"));
                guest.setName(rs.getString("name"));
                guest.setLastName(rs.getString("last_name"));
                guest.setBookingsId(rs.getInt("bookings_id"));
                guests.add(guest);

                return guests;
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
