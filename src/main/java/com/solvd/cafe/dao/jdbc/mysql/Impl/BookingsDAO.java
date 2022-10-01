package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IBookingsDAO;
import com.solvd.cafe.models.Bookings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingsDAO implements IBookingsDAO {
    private static final Logger logger = LogManager.getLogger(BookingsDAO.class);
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INSERT = "INSERT INTO bookings" +
            "(bookings.time, " +
            "bookings.tables_id)\n  " +
            "VALUES (?, ?)";
    private static final String UPDATE = "UPDATE bookings SET " +
            "bookings.time=?, " +
            "bookings.tables_id=? WHERE " +
            "bookings.booking_id=?";
    private static final String DELETE = "DELETE FROM bookings WHERE booking_id=?";
    private static final String GET_BY_ID = "SELECT * FROM bookings WHERE booking_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM bookings";

    @Override
    public void create(Bookings object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setTime(1, object.getTime());
            ps.setInt(2, object.getTablesId());
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
    public void update(Bookings bookings) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            try {
                logger.info("New booking added: ");
                String string = scanner.nextLine();
                java.util.Date time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(string);
                java.sql.Date sqlTime = new java.sql.Date(time.getTime());
                //ps.setTime(1, sqlTime);
                ps.setDate(1, (sqlTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            logger.info("Updated table status to occupied: ");
            ps.setInt(2, scanner.nextInt());
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
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }
    }

    @Override
    public Bookings getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Bookings booking = new Bookings();
                booking.setId(rs.getInt("booking_id"));
                booking.setTime(rs.getTime("time"));
                booking.setTablesId(rs.getInt("tables_id"));
                return booking;
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
    public List<Bookings> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Bookings> bookings = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Bookings booking = new Bookings();
                booking.setId(rs.getInt("booking_id"));
                booking.setTime(rs.getTime("time"));
                booking.setTablesId(rs.getInt("tables_id"));
                bookings.add(booking);
            }

            return bookings;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}