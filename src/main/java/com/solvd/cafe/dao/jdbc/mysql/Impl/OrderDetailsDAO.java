package com.solvd.cafe.dao.jdbc.mysql.Impl;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IOrderDetailsDAO;
import com.solvd.cafe.models.OrderDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO implements IOrderDetailsDAO {
    private static final Logger logger = LogManager.getLogger(OrderDetailsDAO.class);

    private static final String INSERT = "INSERT INTO order_details" +
            "(order_details.menu_items_qty, " +
            "order_details.menu_item_id, " +
            "order_details.orders_id)\n  " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE order_details SET " +
            "order_details.menu_items_qty=?, " +
            "order_details.menu_item_id=?, " +
            "order_details.orders_id=? WHERE " +
            "order_details.details_id=?";
    private static final String DELETE = "DELETE FROM order_details WHERE details_id=?";
    private static final String GET_BY_ID = "SELECT * FROM order_details WHERE details_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM order_details";

    @Override
    public void create(OrderDetails object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getMenuItemsQty());
            ps.setInt(2, object.getMenuItemId());
            ps.setInt(3, object.getOrdersId());

            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            object.setId(id);
            logger.info("id: " + id + " object: " + object);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }

    @Override
    public void update(OrderDetails update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, update.getMenuItemsQty());
            ps.setInt(2, update.getMenuItemId());
            ps.setInt(3, update.getOrdersId());
            ps.setInt(4, update.getId());
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
    public OrderDetails getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setId(rs.getInt("details_id"));
                orderDetail.setMenuItemsQty(rs.getInt("menu_items_qty"));
                orderDetail.setMenuItemId(rs.getInt("menu_item_id"));
                orderDetail.setOrdersId(rs.getInt("orders_id"));
                return orderDetail;
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
    public List<OrderDetails> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<OrderDetails> orderDetails = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setId(rs.getInt("details_id"));
                orderDetail.setMenuItemsQty(rs.getInt("menu_items_qty"));
                orderDetail.setMenuItemId(rs.getInt("menu_item_id"));
                orderDetail.setOrdersId(rs.getInt("orders_id"));
                orderDetails.add(orderDetail);
            }
                return orderDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}
