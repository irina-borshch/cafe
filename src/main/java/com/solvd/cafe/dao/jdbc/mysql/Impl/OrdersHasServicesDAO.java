package com.solvd.cafe.dao.jdbc.mysql.Impl;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IOrdersHasServicesDAO;
import com.solvd.cafe.models.OrdersHasServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersHasServicesDAO implements IOrdersHasServicesDAO {
    private static final Logger logger = LogManager.getLogger(OrdersHasServicesDAO.class);
    private static final String INSERT = "INSERT INTO orders_has_services" +
            "(orders_has_services.orders_id, " +
            "orders_has_services.services_id)\n  " +
            "VALUES (?, ?)";
    private static final String UPDATE = "UPDATE orders_has_services SET " +
            "orders_has_services.orders_id=?, " +
            "orders_has_services.services_id=? WHERE " +
            "orders_has_services.ordserv_id=?";
    private static final String DELETE = "DELETE FROM orders_has_services WHERE ordserv_id=?";
    private static final String GET_BY_ID = "SELECT * FROM orders_has_services WHERE ordserv_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM orders_has_services";

    @Override
    public void create(OrdersHasServices object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getOrdersId());
            ps.setInt(2, object.getServicesId());

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
    public void update(OrdersHasServices update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, update.getOrdersId());
            ps.setInt(2, update.getServicesId());
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
    public OrdersHasServices getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                OrdersHasServices ordHasSer = new OrdersHasServices();
                ordHasSer.setId(rs.getInt("ordserv_id"));
                ordHasSer.setOrdersId(rs.getInt("orders_id"));
                ordHasSer.setServicesId(rs.getInt("services_id"));
                return ordHasSer;
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
    public List<OrdersHasServices> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<OrdersHasServices> ordersHasServices = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdersHasServices ordHasSer = new OrdersHasServices();
                ordHasSer.setId(rs.getInt("table_id"));
                ordHasSer.setOrdersId(rs.getInt("orders_id"));
                ordHasSer.setServicesId(rs.getInt("services_id"));
                ordersHasServices.add(ordHasSer);
            }

            return ordersHasServices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}