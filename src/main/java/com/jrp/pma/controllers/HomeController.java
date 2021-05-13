package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.service.EmployeeService;
import com.jrp.pma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jrp.pma.util.LoggerUtil.log;

@Controller
public class HomeController {

    @Value("${version}")
    private String ver;
    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        model.addAttribute("versionNumber",ver);
        Map<String,Object> map= new HashMap<>();
        List<Project> projects = projectService.getAll();
        model.addAttribute("projectList",projects );
        List<ChartData> projectStatus = projectService.getStatus();
        //Lets convert projectstatus data into json structure for use in javascript
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectStatus);
        log(jsonString); //[{"value":1,"label":"COMPLETED"},{"value":2,"label":"INPROGRESS"},{"value":1,"label":"NOTSTARTED"}]
        model.addAttribute("projectStatusCntCount",jsonString);
        List<EmployeeProject> employeesProjects = employeeService.employeeProjects();
        model.addAttribute("employeeProjectCount",employeesProjects );
        return "main/home";

    }

}
