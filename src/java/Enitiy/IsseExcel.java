
package Enitiy;

public class IsseExcel {
    private String issue_id;
    private String assignee_id;
    private String issue_title;
    private String description;
    private String gitlab_id;
    private String gitlab_url;
    private String created_at;
    private String due_date;
    private String team_id;
    private String milestone_id;
    private String function_ids;
    private String label;
    private String status;

    public IsseExcel() {
    }

    public IsseExcel(String assignee_id, String issue_title, String description, String gitlab_url, String created_at, String due_date, String team_id, String milestone_id, String function_ids, String label, String status) {
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

    
    public IsseExcel(String issue_id, String assignee_id, String issue_title, String description, String gitlab_id, String gitlab_url, String created_at, String due_date, String team_id, String milestone_id, String function_ids, String label, String status) {
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

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(String assignee_id) {
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

    public String getGitlab_id() {
        return gitlab_id;
    }

    public void setGitlab_id(String gitlab_id) {
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

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(String milestone_id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IsseExcel{" + "issue_id=" + issue_id + ", assignee_id=" + assignee_id + ", issue_title=" + issue_title + ", description=" + description + ", gitlab_id=" + gitlab_id + ", gitlab_url=" + gitlab_url + ", created_at=" + created_at + ", due_date=" + due_date + ", team_id=" + team_id + ", milestone_id=" + milestone_id + ", function_ids=" + function_ids + ", label=" + label + ", status=" + status + '}';
    }
    
}
