package com.ssg.team.service;

import com.ssg.team.domain.*;

import static com.ssg.team.service.Data.*;
import static java.lang.Double.parseDouble;

public class NameListService {
    private Employee[] employees;

    public NameListService(){
        employees = new Employee[EMPLOYEES.length];

        for(int i=0; i<employees.length; i++){
            int type = Integer.parseInt(EMPLOYEES[i][0]);

            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = parseDouble(EMPLOYEES[i][4]);

            Equipment equipment;
            double bonus;
            int stock;
            switch(type){
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    private Equipment createEquipment(int i) {
        int type = Integer.parseInt(EQUIPMENTS[i][0]);
        String model = EQUIPMENTS[i][1];

        switch (type){
            case PC:
                String display = EQUIPMENTS[i][2];
                return new PC(model, display);
            case NOTEBOOK:
                double price = Double.parseDouble(EQUIPMENTS[i][2]);
                return new NoteBook(price, model);
            case PRINTER:
                String printerType = EQUIPMENTS[i][2];
                return new Printer(model, printerType);
        }
        return null;
    }

    public Employee[] getAllEmployees(){
        return employees;
    }

    public Employee getEmployee(int id) throws TeamException {
        for (int i=0; i<employees.length; i++){
            if(employees[i].getId() == id){
                return employees[i];
            }
        }
        throw new TeamException("No records!");
    }
}
