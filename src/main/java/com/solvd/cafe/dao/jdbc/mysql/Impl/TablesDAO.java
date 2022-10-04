package com.solvd.cafe.dao.jdbc.mysql.Impl;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.ITablesDAO;
import com.solvd.cafe.models.Tables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TablesDAO implements ITablesDAO {
    private static final Logger logger = LogManager.getLogger(TablesDAO.class);
    private static final String INSERT = "INSERT INTO tables" +
            "(tables.seating_size, " +
            "tables.cafes_id)\n  " +
            "VALUES (?, ?)";
    private static final String UPDATE = "UPDATE tables SET " +
            "tables.seating_size=?, " +
            "tables.cafes_id=? WHERE " +
            "tables.table_id=?";
    private static final String DELETE = "DELETE FROM tables WHERE table_id=?";
    private static final String GET_BY_ID = "SELECT * FROM tables WHERE table_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM tables";

    @Override
    public void create(Tables object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, object.getSeatingSize());
            ps.setInt(2, object.getCafesId());

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
    public void update(Tables update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, update.getSeatingSize());
            ps.setInt(2, update.getCafesId());
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
    public Tables getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Tables table = new Tables();
                table.setId(rs.getInt("table_id"));
                table.setSeatingSize(rs.getInt("seating_size"));
                table.setCafesId(rs.getInt("cafes_id"));
                return table;
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
    public List<Tables> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Tables> tables = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tables table = new Tables();
                table.setId(rs.getInt("table_id"));
                table.setSeatingSize(rs.getInt("seating_size"));
                table.setCafesId(rs.getInt("cafes_id"));
                tables.add(table);
            }
                return tables;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}