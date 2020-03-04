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
public class CustomerTest {

    private final static Logger log = LoggerFactory.getLogger(EmployeeTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer = new Customer("test", "test");  // "reference" customer
    private final PaymentMethod paymentMethod = new PaymentMethod(customer.getCId(), false, 123.33, "test");


    @BeforeEach
    private void setup() {
        customerRepository.saveAndFlush(customer);
        paymentMethod.setCId(customer.getCId());
        customer.addPaymentMethod(paymentMethod);
        customerRepository.saveAndFlush(customer);
    }

    @Test
    @Order(1)
    public void testSaveCustomer() {
        Customer customer2 = customerRepository.findByFirstName("test");

        log.info(customer2.toString());

        assertNotNull(customer);
        assertEquals(customer2.getFirstName(), customer.getFirstName());
        assertEquals(customer2.getLastName(), customer.getLastName());
        assertEquals(customer2.getPaymentMethods().size(), customer.getPaymentMethods().size());
        assertEquals(customer2.getPaymentMethods().size(), 1);
    }

    @Test
    @Order(4)
    public void testRemovePaymentMethodAndFlush() {
        Customer c = customerRepository.findByFirstName("test");
        PaymentMethod p = new ArrayList<PaymentMethod>(c.getPaymentMethods()).get(0);  // get on address
        c.removePaymentMethod(p);
        customerRepository.saveAndFlush(c);
        log.info(c.toString());

        Customer customer2 = customerRepository.findByFirstName("test");
        assertEquals(customer2.getPaymentMethods().size(), 0);
    }
}

