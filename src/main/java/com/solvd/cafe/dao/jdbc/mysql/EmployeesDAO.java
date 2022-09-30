package com.solvd.cafe.dao.jdbc.mysql;

import com.solvd.cafe.connection.ConnectionUtil;
import com.solvd.cafe.dao.IEmployeesDAO;
import com.solvd.cafe.models.Employees;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO implements IEmployeesDAO {
    private static final Logger logger = LogManager.getLogger(EmployeesDAO.class);
    private static final String INSERT = "INSERT INTO employees" +
            "(employees.name, " +
            "employees.last_name, " +
            "employees.phone_num, " +
            "employees.position, " +
            "employees.cafes_id)\n  " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employees SET " +
            "employees.name, " +
            "employees.last_name, " +
            "employees.phone_num, " +
            "employees.position, " +
            "employees.cafes_id WHERE " +
            "employees.item_id=?";
    private static final String DELETE = "DELETE FROM employees WHERE employee_id=?";
    private static final String GET_BY_ID = "SELECT * FROM employees WHERE id=?";
    private static final String GET_ALL_RECORDS = "SELECT * FROM employees";

    @Override
    public void create(Employees object) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.setString(2, object.getLastName());
            ps.setString(3, object.getPhoneNum());
            ps.setString(4, object.getPosition());
            ps.setInt(5, object.getCafesId());
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
    public void update(Employees update) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(UPDATE);
            ps.setString(1, update.getName());
            ps.setString(2, update.getLastName());
            ps.setString(3, update.getPhoneNum());
            ps.setString(4, update.getPosition());
            ps.setInt(5, update.getCafesId());
            ps.setInt(6, update.getId());
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
    public Employees getById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employees employee = new Employees();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPhoneNum(rs.getString("phone_num"));
                employee.setPosition(rs.getString("position"));
                employee.setCafesId(rs.getInt("cafes_id"));
                return employee;
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
    public List<Employees> getAllRecords() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionUtil.getConnection();
            ps = connection.prepareStatement(GET_ALL_RECORDS);
            List<Employees> employees = new ArrayList<>();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employees employee = new Employees();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPhoneNum(rs.getString("phone_num"));
                employee.setPosition(rs.getString("position"));
                employee.setCafesId(rs.getInt("cafes_id"));
                employees.add(employee);

                return employees;
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
