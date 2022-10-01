package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IDiscountsDAO;
import com.solvd.cafe.models.Discounts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountsDAO implements IDiscountsDAO {
    private static final Logger logger = LogManager.getLogger(DiscountsDAO.class);
    private static final String INSERT = "INSERT INTO discounts" +
            "(discounts.discount_type, " +
            "cafes.discount_size)\n  " +
            "VALUES (?, ?)";
    private static final String UPDATE = "UPDATE discounts SET " +
            "discounts.discount_type=?, " +
            "discounts.discount_size=? WHERE " +
            "discounts.discount_id=?";
    private static final String DELETE = "DELETE FROM discounts WHERE discount_id=?";
    private static final String GET_BY_ID = "SELECT * FROM discounts WHERE discount_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM discounts";

    @Override
    public void create(Discounts object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getDiscountType());
            ps.setDouble(2, object.getDiscountSize());

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
    public void update(Discounts update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, update.getDiscountType());
            ps.setDouble(2, update.getDiscountSize());
            ps.setInt(3, update.getId());
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
    public Discounts getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Discounts discount = new Discounts();
                discount.setId(rs.getInt("discount_id"));
                discount.setDiscountType(rs.getString("discount_type"));
                discount.setDiscountSize(rs.getDouble("discount_size"));
                return discount;
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
    public List<Discounts> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Discounts> discounts = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Discounts discount = new Discounts();
                discount.setId(rs.getInt("discount_id"));
                discount.setDiscountType(rs.getString("discount_type"));
                discount.setDiscountSize(rs.getDouble("discount_size"));
                discounts.add(discount);
            }

            return discounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}