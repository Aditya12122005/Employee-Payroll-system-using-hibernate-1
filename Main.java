package com.example;

import com.example.model.Employee;
import com.example.model.Payroll;
import com.example.service.PayrollService;
import com.example.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PayrollService service = new PayrollService();
        Scanner sc = new Scanner(System.in);

        System.out.println("Hibernate Payroll Demo");

        // simple demo flow
        Employee emp = service.createEmployee("Aditya Parmar", "aditya@example.com", "IT");
        System.out.println("Created: " + emp);

        Payroll p = service.generatePayroll(emp, 11, 2025, 50000, 10000, 2000, 3000);
        System.out.println("Generated payroll: " + p);

        System.out.println("All employees:");
        List<Employee> els = service.listEmployees();
        for (Employee e : els) {
            System.out.println(e);
            // lazy payrolls: fetch explicitly
            List<Payroll> ps = service.listPayrollsForEmployee(e.getId());
            ps.forEach(pay -> System.out.println("  " + pay));
        }

        // cleanup
        HibernateUtil.shutdown();
        sc.close();
    }
}
