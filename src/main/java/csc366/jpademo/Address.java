package csc366.jpademo;

import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // this class maps to a database table
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = true)
    private Person person;
    
    public Address() { }
    
    public Address(String street, String city, String state, String zipCode) {
	this.street = street;
	this.city = city;
	this.state = state;
	this.zipCode = zipCode;
    }
    
    public Long getId() {
	return id;
    }
    public void setId(Long id) {
	this.id = id;
    }
    
    public String getStreet() {
	return street;
    }
    public void setStreet(String street) {
	this.street = street;
    }

    public String getCity() {
	return city;
    }
    public void setCity(String city) {
	this.city = city;
    }

    public String getState() {
	return state;
    }
    public void setState(String state) {
	this.state = state;
    }

    public String getZipCode() {
	return zipCode;
    }
    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    public Person getPerson() {
	return person;
    }
    public void setPerson(Person person) {
	this.person = person;
    }
        
    @Override
    public String toString()
    {
	StringJoiner sj = new StringJoiner("," , Address.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(street).add(city).add(state).add(zipCode);
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof Address)) return false;
	return id != null && id.equals(((Address) o).getId());
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
