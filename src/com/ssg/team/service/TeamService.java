package com.ssg.team.service;

import com.ssg.team.domain.Architect;
import com.ssg.team.domain.Designer;
import com.ssg.team.domain.Employee;
import com.ssg.team.domain.Programmer;

public class TeamService {
    private static int counter = 1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total;

    public TeamService(){
        super();
    }
    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i=0; i< team.length; i++){
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException {
        if(total >= MAX_MEMBER){
            throw new TeamException("Members full!");
        }
        if (!(e instanceof Programmer)){
            throw new TeamException("Not a programmer!");
        }
        if (isExist(e)){
            throw new TeamException("Already exist in the team!");
        }
        Programmer p = (Programmer)e;
        if ("BUSY".equals(p.getStatus().getNAME())){
            throw new TeamException("Already exist in another team!");
        }
        if ("VOCATION".equals(p.getStatus().getNAME())){
            throw new TeamException("This member is currently in vocation!");
        }

        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for (int i=0; i<total; i++) {
            if (team[i] instanceof Architect)
                numOfArch++;
            else if (team[i] instanceof Designer)
                numOfDes++;
            else if (team[i] instanceof Programmer)
                numOfPro++;
        }

        if (p instanceof Architect){
            if (numOfArch == 1)
                throw new TeamException("No more than one Architect!");
        }
        else if (p instanceof Designer){
            if (numOfDes == 2)
                throw new TeamException("No more than two Designer!");
        }
        else if (p instanceof Programmer){
            if (numOfPro == 3)
                throw new TeamException("No more than three programmer!");
        }

        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;

    }
    public void removeMember(int memberId) throws TeamException {
        int i=0;
        for(; i<total; i++){
            if(team[i].getMemberId() == memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if (i==total){
            throw new TeamException("No records for the id to delete!");
        }
        for (int j=i+1; j<total; j++){
            team[j-1] = team[j];
        }
        team[--total] = null;
    }

    public boolean isExist(Employee e) {
        for (int i=0; i<total; i++){
            return team[i].getId() == e.getId();

        }
        return false;
    }


}
