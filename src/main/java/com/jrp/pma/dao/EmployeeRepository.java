package com.jrp.pma.dao;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
    @Query(nativeQuery = true, value = "select first_name as firstName, last_name as lastName, COUNT(pe.employee_id) as projectCount "+
            "FROM employee e left join project_employee pe ON pe.employee_id=e.employee_id GROUP BY e.first_name, e.last_name ORDER BY 2 DESC")
    public List<EmployeeProject> employeeProjects();
}
