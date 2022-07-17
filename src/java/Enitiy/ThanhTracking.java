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
public class ThanhTracking {

    private String tracking_id;
    private String team_id_id;
    private String milestone_id;
    private String function_id;
    private String assigner_id;
    private String assignee_id;
    private String tracking_note;
    private String update;
    private String status;

    public ThanhTracking() {
    }

    public ThanhTracking(String tracking_id, String team_id_id, String milestone_id, String function_id, String assigner_id, String assignee_id, String tracking_note, String update, String status) {
        this.tracking_id = tracking_id;
        this.team_id_id = team_id_id;
        this.milestone_id = milestone_id;
        this.function_id = function_id;
        this.assigner_id = assigner_id;
        this.assignee_id = assignee_id;
        this.tracking_note = tracking_note;
        this.update = update;
        this.status = status;
    }

    public String getTracking_id() {
        return tracking_id;
    }

    public void setTracking_id(String tracking_id) {
        this.tracking_id = tracking_id;
    }

    public String getTeam_id_id() {
        return team_id_id;
    }

    public void setTeam_id_id(String team_id_id) {
        this.team_id_id = team_id_id;
    }

    public String getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(String milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getFunction_id() {
        return function_id;
    }

    public void setFunction_id(String function_id) {
        this.function_id = function_id;
    }

    public String getAssigner_id() {
        return assigner_id;
    }

    public void setAssigner_id(String assigner_id) {
        this.assigner_id = assigner_id;
    }

    public String getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(String assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getTracking_note() {
        return tracking_note;
    }

    public void setTracking_note(String tracking_note) {
        this.tracking_note = tracking_note;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ThanhTracking{" + "tracking_id=" + tracking_id + ", team_id_id=" + team_id_id + ", milestone_id=" + milestone_id + ", function_id=" + function_id + ", assigner_id=" + assigner_id + ", assignee_id=" + assignee_id + ", tracking_note=" + tracking_note + ", update=" + update + ", status=" + status + '}';
    }
    
    

}
