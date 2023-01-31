package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "COMPANY_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "company")
    List<Employee> employees = new ArrayList<>(); // NPE 방지를 위해 미리 생성

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
