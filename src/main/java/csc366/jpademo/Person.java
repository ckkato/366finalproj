package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
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
@Table(name = "person",     // may be omitted for default naming
       // requires @Column(name=...) 
       uniqueConstraints = @UniqueConstraint(columnNames={"last_name", "first_name"})
)
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;  // note: no annotation, still included in underlying table
    
    @NotNull
    @Column(unique=true, name="last_name")
    private String lastName;

    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "person",       // the join column should be in *Address*
               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address in the List
               orphanRemoval = true,      //  address records that are no longer attached to a person are removed
               fetch = FetchType.LAZY)
    //@OrderColumn(name = "list_idx")
    private List<Address> addresses = new ArrayList<>();
    
    public Person() { }
    
    public Person(String firstName, String lastName, String email) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
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

    public String getEmail() {
	return email;
    }
    public void setEmail(String email) {
	this.email = email;
    }

    public void addAddress(Address a) {
	addresses.add(a);
	a.setPerson(this);
    }
    public void removeAddress(Address a) {
	addresses.remove(a);
	a.setPerson(null);
    }
    public List<Address> getAddresses() {
	return this.addresses;
    }
    
    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(firstName).add(lastName).add("addresses="+addresses.toString());
	return sj.toString();
    }

}
