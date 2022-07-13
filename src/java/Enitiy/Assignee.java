/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitiy;

/**
 *
 * @author tiena
 */
public class Assignee {
    private String user_id;
    private String fullname;
    private String team_id;
    private String class_id;

    public Assignee() {
    }

    public Assignee(String user_id, String fullname, String team_id, String class_id) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.team_id = team_id;
        this.class_id = class_id;
    }

   

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "Assignee{" + "user_id=" + user_id + ", team_id=" + team_id + ", class_id=" + class_id + ", fullname=" + fullname + '}';
    }
    
}
