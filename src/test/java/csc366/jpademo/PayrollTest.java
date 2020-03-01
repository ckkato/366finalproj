package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

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
public class PayrollTest {

    private final static Logger log = LoggerFactory.getLogger(PayrollTest.class);

    @Autowired
    private PayrollRepository payrollRepository;

    private final Payroll payroll = new Payroll(new Long(1), new Double(2.0), "test");  // "reference" payroll

    @BeforeEach
    private void setup() {
        payrollRepository.saveAndFlush(payroll);
    }

    @Test
    @Order(1)
    public void testSavePayroll() {
        Payroll payroll2 = payrollRepository.findById(1);

        log.info(payroll2.toString());

        assertNotNull(payroll);
        assertEquals(payroll2.getPayRate(), payroll.getPayRate());
        assertEquals(payroll2.getPayPeriod(), payroll.getPayPeriod());
    }


}
