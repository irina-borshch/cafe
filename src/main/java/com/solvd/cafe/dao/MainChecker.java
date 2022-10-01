package com.solvd.cafe.dao;

import com.solvd.cafe.dao.jdbc.mysql.Impl.EmployeesDAO;
import com.solvd.cafe.models.Employees;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainChecker {
    private static final Logger logger = LogManager.getLogger(MainChecker.class);

    public static void main(String[] args) {
        logger.info("GET ALL RECORDS");
        IEmployeesDAO iEmployeesDAO = new EmployeesDAO();
        //List<Employees> employees = new ArrayList<>();
        for (Employees employees : iEmployeesDAO.getAllRecords()) {
            logger.info(employees);
        }

        logger.info("GET BY ID");
        logger.info(iEmployeesDAO.getById(3));

        logger.info("CREATE");
        Employees newEmployee = new Employees();
        //newEmployee.setId(8);
        newEmployee.setName("Jason");
        newEmployee.setLastName("Statham");
        newEmployee.setPhoneNum("+380998443865");
        newEmployee.setPosition("security");
        newEmployee.setCafesId(2);
        iEmployeesDAO.create(newEmployee);


        logger.info("UPDATE");
        Employees updateEmployee = new Employees();
        updateEmployee.setName("Jason");
        updateEmployee.setLastName("Statham");
        updateEmployee.setPhoneNum("+380998443865");
        updateEmployee.setPosition("security");
        updateEmployee.setCafesId(2);
        updateEmployee.setId(5);
        iEmployeesDAO.update(updateEmployee);

        for (Employees employees : iEmployeesDAO.getAllRecords()) {
            logger.info(employees);
        }

        logger.info("DELETE");
        iEmployeesDAO.delete(14);
        for (Employees employees : iEmployeesDAO.getAllRecords()) {
            logger.info(employees);
        }
    }
}
