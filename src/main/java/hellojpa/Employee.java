package hellojpa;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    @Column(name="EMPLOYEE_ID")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="COMPANY_ID")
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
