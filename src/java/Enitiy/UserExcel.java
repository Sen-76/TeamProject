/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitiy;

/**
 *
 * @author ASUS
 */
public class UserExcel {
    String groupID;
    String username;
    String rollNum;
    String fullName;
    String leader;

    public UserExcel() {
    }

    public UserExcel(String groupID, String username, String rollNum, String fullName, String leader) {
        this.groupID = groupID;
        this.username = username;
        this.rollNum = rollNum;
        this.fullName = fullName;
        this.leader = leader;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    
    
    @Override
    public String toString() {
        return "UserExcel{" + "groupID=" + groupID + ", username=" + username + ", rollNum=" + rollNum + ", fullName=" + fullName + ", leader=" + leader + '}';
    }
    
    
}
