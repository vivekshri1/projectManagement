package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping
    public String displayEmployee(Model model){
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "/employee/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        Employee aEmployee = new Employee();
        model.addAttribute("employee",aEmployee);
        return "employee/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, Employee employee){
        employeeRepository.save(employee);
        return "redirect:/employees/new";
    }
}
