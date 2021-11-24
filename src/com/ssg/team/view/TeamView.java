package com.ssg.team.view;

import com.ssg.team.domain.Employee;
import com.ssg.team.domain.Programmer;
import com.ssg.team.service.NameListService;
import com.ssg.team.service.TeamException;
import com.ssg.team.service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu(){
        boolean loopFlag = true;
        char menu = 0;

        while (loopFlag){
            System.out.println("1-Team List, 2-Add Member, 3-Delete Member, 4-Exit");
            menu = TSUtility.readMenuSelection();
            if (menu != '1') {
                listAllEmployees();
            }
            switch(menu){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    loopFlag = exit();
                    break;
            }
        }



    }
    private void listAllEmployees(){
        Employee[] employees = listSvc.getAllEmployees();
        for (int i=0; i< employees.length; i++){
            System.out.println(employees[i]);
        }
        System.out.println("---------------------------------");

    }
    private void getTeam(){
        System.out.println("--------------Team Members--------------");
        Programmer[] team = teamSvc.getTeam();
        if(team == null || team.length == 0){
            System.out.println("No team member yet");
        }else {
            for(int i=0; i<team.length; i++){
                System.out.println(team[i]);
            }
        }

        System.out.println("---------------------------------");

    }
    private void addMember(){
        System.out.println("Add team members ");
        System.out.print("Enter the Id: ");
        int id = TSUtility.readInt();

        try {
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("Have added");
        } catch (TeamException e) {
            System.out.println("Failed: " + e.getMessage());
        }
        TSUtility.readReturn();
    }
    private void deleteMember(){
        System.out.println("Delete team members ");
        System.out.println("Enter the Id: ");
        int id = TSUtility.readInt();
        System.out.println("Are you sure to delete? Y/N");
        char isDelete = TSUtility.readConfirmSelection();
        if (isDelete == 'N'){
            return;
        }
        try {
            teamSvc.removeMember(id);
            System.out.println("Have deleted");
            TSUtility.readReturn();
        } catch (TeamException e) {
            System.out.println("Failed: " + e.getMessage());
        }


    }

    private Boolean exit(){
        boolean loopFlag = true;
        System.out.println("Exit or Not? Y/N");
        char isExit = TSUtility.readConfirmSelection();
        if (isExit == 'Y') {
            loopFlag = false;
        }
        return loopFlag;
    }

    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }
}
