package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name="c_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cId;

    @NotNull
    @Column(name="first_name")
    private String firstName;  // note: no annotation, still included in underlying table

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)

    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public Customer() { }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getCId() {
        return cId;
    }
    public void setCId(Long cId) {
        this.cId = cId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
        paymentMethod.setCustomer(this);
    }
    public void removePaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.remove(paymentMethod);
        paymentMethod.setCustomer(null);
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.paymentMethods;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Customer.class.getSimpleName() + "[" , "]");
        sj.add(cId.toString()).add(firstName).add(lastName);
        return sj.toString();
    }


}

