package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table

public class PaymentMethod {
    @Id
    @Column(name="c_id")
    private Long cId;

    @NotNull
    @Column(name="is_cash")
    private Boolean isCash;  // note: no annotation, still included in underlying table

    @NotNull
    @Column(name="card_number")
    private Double cardNumber;

    @NotNull
    @Column(name="card_type")
    private String cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_c_id", nullable = false)
    private Customer customer;

    //    @OneToMany(mappedBy = "person",       // the join column should be in *Address*
//               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address in the List
//               orphanRemoval = true,      //  address records that are no longer attached to a person are removed
//               fetch = FetchType.LAZY)
//    //@OrderColumn(name = "list_idx")
//    private List<Address> addresses = new ArrayList<>();
//

    public PaymentMethod() { }

    public PaymentMethod(Long cId, Boolean isCash, Double cardNumber, String cardType) {
        this.cId = cId;
        this.isCash = isCash;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    public Long getCId() {
        return cId;
    }
    public void setCId(Long cId) {
        this.cId = cId;
    }

    public Boolean getIsCash() { return isCash; }
    public void setIsCash(Boolean isCash) { this.isCash = isCash; }

    public Double getCardNumber() {return cardNumber;}
    public void setCardNumber(Double cardNumber) { this.cardNumber = cardNumber; }

    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("," , Payroll.class.getSimpleName() + "[" , "]");
        sj.add(cId.toString()).add(isCash.toString()).add(cardNumber.toString()).add(cardType);
        return sj.toString();
    }

}

