package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payrolls",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "month", "year"}))
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // month number 1..12
    private int month;
    private int year;

    private double basic;
    private double hra;
    private double allowances;
    private double deductions;

    private double netSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate generatedOn;

    public Payroll() {}

    public Payroll(int month, int year, double basic, double hra, double allowances, double deductions) {
        this.month = month;
        this.year = year;
        this.basic = basic;
        this.hra = hra;
        this.allowances = allowances;
        this.deductions = deductions;
        this.generatedOn = LocalDate.now();
    }

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getBasic() { return basic; }
    public void setBasic(double basic) { this.basic = basic; }
    public double getHra() { return hra; }
    public void setHra(double hra) { this.hra = hra; }
    public double getAllowances() { return allowances; }
    public void setAllowances(double allowances) { this.allowances = allowances; }
    public double getDeductions() { return deductions; }
    public void setDeductions(double deductions) { this.deductions = deductions; }
    public double getNetSalary() { return netSalary; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public LocalDate getGeneratedOn() { return generatedOn; }
    public void setGeneratedOn(LocalDate generatedOn) { this.generatedOn = generatedOn; }

    @Override
    public String toString() {
        return "Payroll{" + "id=" + id + ", month=" + month + ", year=" + year +
                ", basic=" + basic + ", hra=" + hra + ", allowances=" + allowances +
                ", deductions=" + deductions + ", netSalary=" + netSalary + '}';
    }
}
