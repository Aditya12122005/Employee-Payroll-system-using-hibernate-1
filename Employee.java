package com.example.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String department;

    // One employee can have many payroll records (monthly)
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Payroll> payrolls = new ArrayList<>();

    public Employee() {}

    public Employee(String fullName, String email, String department) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
    }

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public List<Payroll> getPayrolls() { return payrolls; }
    public void setPayrolls(List<Payroll> payrolls) { this.payrolls = payrolls; }

    public void addPayroll(Payroll p) {
        payrolls.add(p);
        p.setEmployee(this);
    }

    public void removePayroll(Payroll p) {
        payrolls.remove(p);
        p.setEmployee(null);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' + ", department='" + department + '\'' + '}';
    }
}
