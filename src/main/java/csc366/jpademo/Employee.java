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
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name="empl_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long emplId;

    @NotNull
    @Column(name="first_name")
    private String firstName;  // note: no annotation, still included in underlying table
    
    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column(name="start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @OneToMany(mappedBy = "employee",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Payroll> payrolls = new ArrayList<>();

    public Employee() { }
    
    public Employee(String firstName, String lastName, Date startDate) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.startDate = startDate;
    }
    
    public Long getEmplId() {
	    return emplId;
    }
    public void setEmplId(Long emplId) {
	    this.emplId = emplId;
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

    public Date getStartDate() {
	    return startDate;
    }

    public void setStartDate(Date startDate) {
	    this.startDate = startDate;
    }

    public void addPayroll(Payroll payroll) {
        payrolls.add(payroll);
        payroll.setEmployee(this);
    }
    public void removePayroll(Payroll payroll) {
        payrolls.remove(payroll);
        payroll.setEmployee(null);
    }

    public List<Payroll> getPayrolls() {
        return this.payrolls;
    }
    
    @Override
    public String toString() {
	    StringJoiner sj = new StringJoiner("," , Employee.class.getSimpleName() + "[" , "]");
	    sj.add(emplId.toString()).add(firstName).add(lastName).add("startDate="+startDate.toString())
            .add("payrolls="+payrolls.toString());
	    return sj.toString();
    }


}
