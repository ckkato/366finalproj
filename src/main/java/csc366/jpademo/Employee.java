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
@Table(name = "employee",
       // requires @Column(name=...) 
       uniqueConstraints = @UniqueConstraint(columnNames={"emplId"})
)
public class Employee {
    @Id
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

//    @OneToMany(mappedBy = "person",       // the join column should be in *Address*
//               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address in the List
//               orphanRemoval = true,      //  address records that are no longer attached to a person are removed
//               fetch = FetchType.LAZY)
//    //@OrderColumn(name = "list_idx")
//    private List<Address> addresses = new ArrayList<>();
//
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
	    StringJoiner sj = new StringJoiner("," , Employee.class.getSimpleName() + "[" , "]");
	    sj.add(emplId.toString()).add(firstName).add(lastName).add("startDate="+startDate.toString());
	    return sj.toString();
    }

}
