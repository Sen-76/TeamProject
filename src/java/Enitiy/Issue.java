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
public class Issue {
    private int issue_id;
    private int assignee_id;
    private String issue_title;
    private String description;
    private int gitlab_id;
    private String gitlab_url;
    private String created_at;
    private String due_date;
    private int team_id;
    private int milestone_id;
    private String function_ids;
    private String label;
    private int status;
    private String milestone_name;
    private String function_name;

    public Issue() {
    }


    public Issue(int milestone_id, String milestone_name) {
        this.milestone_id = milestone_id;
        this.milestone_name = milestone_name;
    }

    
    public String getMilestone_name() {
        return milestone_name;
    }

    public void setMilestone_name(String milestone_name) {
        this.milestone_name = milestone_name;
    }

    public Issue(int assignee_id, String issue_title, String description, String gitlab_url, String created_at, String due_date, int team_id, int milestone_id, String function_ids, String label, int status) {
        this.assignee_id = assignee_id;
        this.issue_title = issue_title;
        this.description = description;
        this.gitlab_url = gitlab_url;
        this.created_at = created_at;
        this.due_date = due_date;
        this.team_id = team_id;
        this.milestone_id = milestone_id;
        this.function_ids = function_ids;
        this.label = label;
        this.status = status;
    }

    
    
    
    
    public Issue(int issue_id, String issue_title, String description, String gitlab_url, String created_at, String due_date, int milestone_id, String function_ids, String label, int status) {
        this.issue_id = issue_id;
        this.issue_title = issue_title;
        this.description = description;
        this.gitlab_url = gitlab_url;
        this.created_at = created_at;
        this.due_date = due_date;
        this.milestone_id = milestone_id;
        this.function_ids = function_ids;
        this.label = label;
        this.status = status;
    }

    public Issue(int issue_id, int assignee_id, String issue_title, String description, String gitlab_url, String created_at, String due_date, int milestone_id, String function_ids, String label, int status) {
        this.issue_id = issue_id;
        this.assignee_id = assignee_id;
        this.issue_title = issue_title;
        this.description = description;
        this.gitlab_url = gitlab_url;
        this.created_at = created_at;
        this.due_date = due_date;
        this.milestone_id = milestone_id;
        this.function_ids = function_ids;
        this.label = label;
        this.status = status;
    }
    

    public Issue(String issue_title, String description, String gitlab_url, String created_at, String due_date, int milestone_id, String function_ids, String label, int status) {
        this.issue_title = issue_title;
        this.description = description;
        this.gitlab_url = gitlab_url;
        this.created_at = created_at;
        this.due_date = due_date;
        this.milestone_id = milestone_id;
        this.function_ids = function_ids;
        this.label = label;
        this.status = status;
    }

    
    
    
    public Issue(int issue_id, int assignee_id, String issue_title, String description, int gitlab_id, String gitlab_url, String created_at, String due_date, int team_id, int milestone_id, String function_ids, String label, int status) {
        this.issue_id = issue_id;
        this.assignee_id = assignee_id;
        this.issue_title = issue_title;
        this.description = description;
        this.gitlab_id = gitlab_id;
        this.gitlab_url = gitlab_url;
        this.created_at = created_at;
        this.due_date = due_date;
        this.team_id = team_id;
        this.milestone_id = milestone_id;
        this.function_ids = function_ids;
        this.label = label;
        this.status = status;
    }

    public int getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(int issue_id) {
        this.issue_id = issue_id;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public String getIssue_title() {
        return issue_title;
    }

    public void setIssue_title(String issue_title) {
        this.issue_title = issue_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGitlab_id() {
        return gitlab_id;
    }

    public void setGitlab_id(int gitlab_id) {
        this.gitlab_id = gitlab_id;
    }

    public String getGitlab_url() {
        return gitlab_url;
    }

    public void setGitlab_url(String gitlab_url) {
        this.gitlab_url = gitlab_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }

    public String getFunction_ids() {
        return function_ids;
    }

    public void setFunction_ids(String function_ids) {
        this.function_ids = function_ids;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issue{" + "issue_id=" + issue_id + ", assignee_id=" + assignee_id + ", issue_title=" + issue_title + ", description=" + description + ", gitlab_id=" + gitlab_id + ", gitlab_url=" + gitlab_url + ", created_at=" + created_at + ", due_date=" + due_date + ", team_id=" + team_id + ", milestone_id=" + milestone_id + ", function_ids=" + function_ids + ", label=" + label + ", status=" + status + '}';
    }
    
}
