package com.solvd.cafe.jaxb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainJAXB {
    private static final Logger logger = LogManager.getLogger(MainJAXB.class);
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Jason", "Statham", 35, "+380998443865", "security"));
        employees.add(new Employee("Svitlana", "Bowie", 29, "+380568449865", "chef"));
        employees.add(new Employee("Maksym", "Siryak", 23, "+380578462598", "waiter"));
        employees.add(new Employee("Nadiya", "Bilyk", 37, "+380999447826", "head"));
        /*Employee employee1 = new Employee("Jason", "Statham", 35, "+380998443865", "security");
        Employee employee2 = new Employee("Svitlana", "Bowie", 29, "+380568449865", "chef");
        Employee employee3 = new Employee("Maksym", "Siryak", 23, "+380578462598", "waiter");
        Employee employee4 = new Employee("Nadiya", "Bilyk", 37, "+380999447826", "head");

        ArrayList<Employee> employees = new ArrayList<Employee>(Arrays.asList(employee1, employee2, employee3, employee4));*/
        //write to xml

        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Employees(employees), new File("src/main/java/com/solvd/cafe/jaxb/employee.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        // read from xml
        try {
            Employees employees1 = new Employees();
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            employees1 = (Employees) unmarshaller.unmarshal(new File("src/main/java/com/solvd/cafe/jaxb/employee.xml"));
            logger.info(employees1);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
