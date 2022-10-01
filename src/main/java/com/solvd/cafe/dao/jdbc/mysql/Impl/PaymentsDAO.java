package com.solvd.cafe.dao.jdbc.mysql.Impl;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IPaymentsDAO;
import com.solvd.cafe.models.Payments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentsDAO implements IPaymentsDAO {
    private static final Logger logger = LogManager.getLogger(PaymentsDAO.class);
    private static final String INSERT = "INSERT INTO payments" +
            "(payments.total_price, " +
            "payments.orders_id, " +
            "payments.discounts_id)\n  " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE payments SET " +
            "payments.total_price=?, " +
            "payments.orders_id=?, " +
            "payments.discounts_id=? WHERE " +
            "payments.payment_id=?";
    private static final String DELETE = "DELETE FROM payments WHERE payment_id=?";
    private static final String GET_BY_ID = "SELECT * FROM payments WHERE payment_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM payments";

    @Override
    public void create(Payments object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, object.getTotalPrice());
            ps.setInt(2, object.getOrdersId());
            ps.setInt(3, object.getDiscountsId());

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
    public void update(Payments payments) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setDouble(1, payments.getTotalPrice());
            ps.setInt(2, payments.getOrdersId());
            ps.setInt(3, payments.getDiscountsId());
            ps.setInt(4, payments.getId());
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
    public Payments getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Payments payment = new Payments();
                payment.setId(rs.getInt("payment_id"));
                payment.setTotalPrice(rs.getDouble("total_price"));
                payment.setOrdersId(rs.getInt("orders_id"));
                payment.setDiscountsId(rs.getInt("discounts_id"));
                return payment;
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
    public List<Payments> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Payments> payments = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Payments payment = new Payments();
                payment.setId(rs.getInt("discount_id"));
                payment.setTotalPrice(rs.getDouble("total_price"));
                payment.setOrdersId(rs.getInt("orders_id"));
                payment.setDiscountsId(rs.getInt("discounts_id"));
                payments.add(payment);
            }
                return payments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}