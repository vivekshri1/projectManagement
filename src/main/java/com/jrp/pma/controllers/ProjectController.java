package com.jrp.pma.controllers;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.service.EmployeeService;
import com.jrp.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping ("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;
    @GetMapping
    public String displayProjects(Model model){
        List<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        return "/projects/list-projects";
    }
    @GetMapping("/new")
    public String displayProjectForm(Model model){
        Project aProject = new Project();
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("project",aProject);
        model.addAttribute("allEmployees" ,employees);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Model model, Project project, @RequestParam List<Long> employees ) {
        projectService.save(project);
//        Iterable<Employee> choosenEmployees = employeeRepository.findAllById(employees);
//        for(Employee emp:choosenEmployees){
//            emp.setProject(project);
//            employeeRepository.save(emp);
//        }
        //use a redirect to prevent duplicate submission
        return "redirect:/projects/new";
    }
}
