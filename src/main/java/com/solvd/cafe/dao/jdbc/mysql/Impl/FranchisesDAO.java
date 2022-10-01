package com.solvd.cafe.dao.jdbc.mysql.Impl;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IFranchisesDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.cafe.models.Franchises;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FranchisesDAO implements IFranchisesDAO {
    private static final Logger logger = LogManager.getLogger(FranchisesDAO.class);
    private static final Scanner scanner = new Scanner(System.in);

    private static final String INSERT = "INSERT INTO franchises" +
            "(franchises.name)\n " +
            "VALUES (?)";
    private static final String UPDATE = "UPDATE franchises SET " +
            "franchises.name=? " +
            "franchises.franchise_id=?";
    private static final String DELETE = "DELETE FROM franchises WHERE franchise_id=?";
    private static final String GET_BY_ID = "SELECT * FROM franchises WHERE franchise_id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM franchises";

    @Override
    public void create(Franchises franchises) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, franchises.getName());
            ps.executeUpdate();

            int id = 0;
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            logger.info("id: " + id + " object: " + franchises);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);
        }

    }
    @Override
    public void update(Franchises update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            logger.info("New franchise name: ");
            ps.setString(1, scanner.nextLine());
            logger.info("New franchise id: ");
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
    public Franchises getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Franchises franchises = new Franchises();
                franchises.setId(rs.getInt("franchise_id"));
                franchises.setName(rs.getString("name"));
                return franchises;
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
    public List<Franchises> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Franchises> franchises = new ArrayList<>();

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Franchises franchise  = new Franchises();
                franchise.setId(rs.getInt("franchise_id"));
                franchise.setName(rs.getString("name"));
                franchises.add(franchise);
            }
            return franchises;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionUtil.close(ps);
            ConnectionUtil.close(connection);

        }
    }
}
