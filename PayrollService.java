package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.dao.PayrollDAO;
import com.example.model.Employee;
import com.example.model.Payroll;

import java.util.List;

public class PayrollService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final PayrollDAO payrollDAO = new PayrollDAO();

    /**
     * Compute net salary using a simple formula:
     * net = basic + hra + allowances - deductions
     * You can extend this (tax slabs, PF, etc.)
     */
    public Payroll generatePayroll(Employee employee, int month, int year,
                                   double basic, double hra, double allowances, double deductions) {
        Payroll p = new Payroll(month, year, basic, hra, allowances, deductions);
        double net = basic + hra + allowances - deductions;
        p.setNetSalary(net);
        p.setEmployee(employee);
        // persist
        return payrollDAO.save(p);
    }

    public Employee createEmployee(String name, String email, String department) {
        Employee e = new Employee(name, email, department);
        return employeeDAO.save(e);
    }

    public Employee findEmployee(Long id) { return employeeDAO.findById(id); }
    public List<Employee> listEmployees() { return employeeDAO.findAll(); }
    public List<Payroll> listPayrollsForEmployee(Long empId) { return payrollDAO.findForEmployee(empId); }
}
