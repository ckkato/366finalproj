package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Demo0: Add, list, and remove Person instances

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.main.banner-mode=off",
        "spring.jpa.hibernate.ddl-auto=update",
        "logging.level.root=ERROR",
        "logging.level.csc366=DEBUG",

        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
        "spring.jpa.properties.hibernate.format_sql=true",
        "spring.jpa.show-sql=false",   // prevent duplicate logging
        "spring.jpa.properties.hibernate.show_sql=false",

        "logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeTest {

    private final static Logger log = LoggerFactory.getLogger(EmployeeTest.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    private final Employee employee = new Employee("test", "test", new Date());  // "reference" employee
    private final Payroll payroll = new Payroll(employee.getEmplId(), 2.0, "test");


    @BeforeEach
    private void setup() {
        employeeRepository.saveAndFlush(employee);
        payroll.setEmplId(employee.getEmplId());
        employee.addPayroll(payroll);
        employeeRepository.saveAndFlush(employee);
    }

    @Test
    @Order(1)
    public void testSaveEmployee() {
        Employee employee2 = employeeRepository.findByFirstName("test");

        log.info(employee2.toString());

        assertNotNull(employee);
        assertEquals(employee2.getFirstName(), employee.getFirstName());
        assertEquals(employee2.getLastName(), employee.getLastName());
        assertEquals(employee2.getPayrolls().size(), employee.getPayrolls().size());
        assertEquals(employee2.getPayrolls().size(), 1);
    }

    @Test
    @Order(4)
    public void testRemovePayrollAndFlush() {
        Employee e = employeeRepository.findByFirstName("test");
        Payroll p = new ArrayList<Payroll>(e.getPayrolls()).get(0);  // get on address
        e.removePayroll(p);
        employeeRepository.saveAndFlush(e);
        log.info(e.toString());

        Employee employee2 = employeeRepository.findByFirstName("test");
        assertEquals(employee2.getPayrolls().size(), 0);
    }
}

