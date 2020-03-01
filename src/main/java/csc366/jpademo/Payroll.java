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

public class Payroll {
    @Id
    @Column(name="empl_id")
    private Long emplId;

    @NotNull
    @Column(name="pay_rate")
    private Double payRate;  // note: no annotation, still included in underlying table
    
    @NotNull
    @Column(name="pay_period")
    private String payPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_empl_id", nullable = false)
    private Employee employee;

//    @OneToMany(mappedBy = "person",       // the join column should be in *Address*
//               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address in the List
//               orphanRemoval = true,      //  address records that are no longer attached to a person are removed
//               fetch = FetchType.LAZY)
//    //@OrderColumn(name = "list_idx")
//    private List<Address> addresses = new ArrayList<>();
//
    public Payroll() { }
    
    public Payroll(Long emplId, Double payRate, String payPeriod) {
	    this.emplId = emplId;
	    this.payRate = payRate;
	    this.payPeriod = payPeriod;
    }

    public Long getEmplId() {
	    return emplId;
    }
    public void setEmplId(Long emplId) {
	    this.emplId = emplId;
    }

    public Double getPayRate() { return payRate; }
    public void setPayRate(Double payRate) { this.payRate = payRate; }
    
    public String getPayPeriod() {
	    return payPeriod;
    }
    public void setPayPeriod(String payPeriod) {
	    this.payPeriod = payPeriod;
    }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    
    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Payroll.class.getSimpleName() + "[" , "]");
	    sj.add(emplId.toString()).add(payRate.toString()).add(payPeriod);
	    return sj.toString();
    }

}
