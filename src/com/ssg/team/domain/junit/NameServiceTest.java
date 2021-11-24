package com.ssg.team.domain.junit;

import com.ssg.team.domain.Employee;
import org.junit.jupiter.api.Test;
import com.ssg.team.service.*;

public class NameServiceTest {

    @Test
    public void testGetAllEmployees(){
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        for (int i=0; i< employees.length; i++){
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee(){
        NameListService service = new NameListService();
        int id = 2;
        try {
            Employee employee = service.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            System.out.println(e.getMessage());
        }
    }
}
