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
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(name = "payroll",
       // requires @Column(name=...)
       uniqueConstraints = @UniqueConstraint(columnNames={"id"})
)
public class Payroll {
    @Id
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name="pay_rate")
    private Double payRate;  // note: no annotation, still included in underlying table
    
    @NotNull
    @Column(name="pay_period")
    private String payPeriod;

//    @OneToMany(mappedBy = "person",       // the join column should be in *Address*
//               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address in the List
//               orphanRemoval = true,      //  address records that are no longer attached to a person are removed
//               fetch = FetchType.LAZY)
//    //@OrderColumn(name = "list_idx")
//    private List<Address> addresses = new ArrayList<>();
//
    public Payroll() { }
    
    public Payroll(Long id, Double payRate, String payPeriod) {
	    this.id = id;
	    this.payRate = payRate;
	    this.payPeriod = payPeriod;
    }
    
    public Long getId() {
	    return id;
    }
    public void setId(Long id) {
	    this.id = id;
    }

    public Double getPayRate() { return payRate; }
    public void setPayRate(Double payRate) { this.payRate = payRate; }
    
    public String getPayPeriod() {
	    return payPeriod;
    }
    public void setPayPeriod(String payPeriod) {
	    this.payPeriod = payPeriod;
    }

//
//    public void addAddress(Address a) {
//	addresses.add(a);
//	a.setPerson(this);
//    }
//    public void removeAddress(Address a) {
//	addresses.remove(a);
//	a.setPerson(null);
//    }
//    public List<Address> getAddresses() {
//	return this.addresses;
//    }
    
    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Payroll.class.getSimpleName() + "[" , "]");
	    sj.add(id.toString()).add(payRate.toString()).add(payPeriod);
	    return sj.toString();
    }

}
