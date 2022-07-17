/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitiy;

/**
 *
 * @author Admin
 */
public class FunctionExcel {
    private String function_id;
    private String team_id;
    private String function_name;
    private String feature_id;
    private String access_roles;
    private String description;
    private String complexity_id;
    private String owner_id;
    private String priority;
    private String status;
    private String setting_title;
    private String setting_value;
    private String fullname;
    private String feature_name;
    private String team_name;
    private String class_code;

    public FunctionExcel() {
    }

    public FunctionExcel(String function_id, String team_id, String function_name, String feature_id, String access_roles, String description, String complexity_id, String owner_id, String priority, String status, String setting_title, String setting_value, String fullname, String feature_name, String team_name, String class_code) {
        this.function_id = function_id;
        this.team_id = team_id;
        this.function_name = function_name;
        this.feature_id = feature_id;
        this.access_roles = access_roles;
        this.description = description;
        this.complexity_id = complexity_id;
        this.owner_id = owner_id;
        this.priority = priority;
        this.status = status;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
        this.fullname = fullname;
        this.feature_name = feature_name;
        this.team_name = team_name;
        this.class_code = class_code;
    }

    public String getFunction_id() {
        return function_id;
    }

    public void setFunction_id(String function_id) {
        this.function_id = function_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(String feature_id) {
        this.feature_id = feature_id;
    }

    public String getAccess_roles() {
        return access_roles;
    }

    public void setAccess_roles(String access_roles) {
        this.access_roles = access_roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComplexity_id() {
        return complexity_id;
    }

    public void setComplexity_id(String complexity_id) {
        this.complexity_id = complexity_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSetting_title() {
        return setting_title;
    }

    public void setSetting_title(String setting_title) {
        this.setting_title = setting_title;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    @Override
    public String toString() {
        return "FunctionExcel{" + "function_id=" + function_id + ", team_id=" + team_id + ", function_name=" + function_name + ", feature_id=" + feature_id + ", access_roles=" + access_roles + ", description=" + description + ", complexity_id=" + complexity_id + ", owner_id=" + owner_id + ", priority=" + priority + ", status=" + status + ", setting_title=" + setting_title + ", setting_value=" + setting_value + ", fullname=" + fullname + ", feature_name=" + feature_name + ", team_name=" + team_name + ", class_code=" + class_code + '}';
    }

    
    
    
}

