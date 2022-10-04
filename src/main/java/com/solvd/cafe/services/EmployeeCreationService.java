package com.solvd.cafe.services;

import com.solvd.cafe.dao.ICafesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.CafesDAO;
import com.solvd.cafe.dao.jdbc.mysql.Impl.EmployeesDAO;
import com.solvd.cafe.models.Employees;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class EmployeeCreationService {
    private static final Logger logger = LogManager.getLogger(EmployeeCreationService.class);

    public static void createEmployee() {
        EmployeesDAO employeesDAO = new EmployeesDAO();
        Scanner scanner = new Scanner(System.in);
        Employees employee = new Employees();
        logger.info("You need to choose a cafe where you want to hire a new employee. Enter cafe id:");
        ManagementPage.showAllCafes();
        employee.setCafesId(EmployeeCreationService.pickCafe());
        logger.info("Enter first name of employee:");

            boolean incorrecName = true;
            while (incorrecName) {
               // logger.info("Enter first name of employee:");
                String name = scanner.nextLine();
                if (name.matches("[A-Z][a-z]+")) {
                    employee.setName(name);
                    incorrecName = false;
                } else {
                    logger.error("Employee's name should begin with capital letter and doesn't include numbers");
                }
            }
            boolean incorrecLastName = true;
            while (incorrecLastName) {
                logger.info("Enter last name of employee:");
                String lastName = scanner.nextLine();
                if (lastName.matches("[A-Z][a-z]+")) {
                    employee.setLastName(lastName);
                    incorrecLastName = false;
                } else {
                    logger.error("Employee's last name should begin with capital letter and doesn't include numbers");
                }
            }
            logger.info("Enter contact number of employee:");
            String number = scanner.nextLine();
            employee.setPhoneNum(number);

            logger.info("Name the position for which you want to hire an employee.");
            //logger.info("Congrats, you've successfuly hired an employee");
            String position = scanner.nextLine();
            employee.setPosition(position);

            employeesDAO.create(employee);
            employeesDAO.update(employee);


            //employee.setId(employee.getId());


            logger.info("Congrats, you've successfuly hired an employee");
            // employeesDAO.update(employee);

        }
        public static int pickCafe(){
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            // logger.info(iCafesDAO.getById(scanner.nextInt()));
            logger.info("Cafe selected");
            return id;
        }
    }
