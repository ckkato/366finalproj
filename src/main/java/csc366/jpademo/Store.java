package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;

import javax.validation.constraints.NotNull;

@Entity  // this class maps to a database table
public class Store {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "store",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    private int revenue;
    
    public Store() { }
    
    public Store(int revenue) {
        this.revenue = revenue;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getRevenue() {
        return revenue;
    }
    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
        
    @Override
    public String toString()
    {
        StringJoiner sj = new StringJoiner("," , Address.class.getSimpleName() + "[" , "]");
        sj.add(id.toString()).add(employees.toString());
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
