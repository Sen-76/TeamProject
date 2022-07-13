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
public class Function1 {
    private int function_id;
    private int team_id;
    private String function_name;
    private int feature_id;
    private String access_roles;
    private String description;
    private int complexity_id;
    private int owner_id;
    private String priority;
    private int status;
    private String feature_name;
    private String team_name;
    private String class_code;
    private int user_id;
    private String fullname;
    private int class_id;
    private int type_id;
    private String setting_title;
    private String setting_value;

    public Function1() {
    }

    public Function1(int function_id, int team_id, String function_name, int feature_id, String access_roles, String description, int complexity_id, int owner_id, String priority, int status) {
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
    }
    
    

    public Function1(int function_id, int team_id, String function_name, int feature_id, String access_roles, String description, int complexity_id, int owner_id, String priority, int status, String feature_name, String team_name, String class_code, int class_id, String fullname, String setting_value) {
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
      this.feature_name = feature_name;
        this.team_name = team_name;
        this.class_code = class_code;
        this.class_id = class_id;
        this.fullname = fullname;
        this.setting_value = setting_value;
      
    }

    public Function1(int team_id, String function_name, int feature_id, String access_roles, String description, int complexity_id, int owner_id, String priority, int status) {
        this.team_id = team_id;
        this.function_name = function_name;
        this.feature_id = feature_id;
        this.access_roles = access_roles;
        this.description = description;
        this.complexity_id = complexity_id;
        this.owner_id = owner_id;
        this.priority = priority;
        this.status = status;
    }

    public Function1(int function_id, int team_id, String function_name, int feature_id, String access_roles, String description, int complexity_id, int owner_id, String priority, int status, String feature_name, String team_name, String class_code, int class_id, String setting_value) {
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
        this.feature_name = feature_name;
        this.team_name = team_name;
        this.class_code = class_code;
        this.class_id = class_id;
        this.setting_value = setting_value;
    }
    
    

    public Function1(int owner_id, int user_id, String fullname) {
        this.owner_id = owner_id;
        this.user_id = user_id;
        this.fullname = fullname;
    }

    public Function1(int team_id, String team_name, String class_code) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.class_code = class_code;
    }
    

    public Function1(int complexity_id, int type_id, String setting_title, String setting_value) {
        this.complexity_id = complexity_id;
        this.type_id = type_id;
        this.setting_title = setting_title;
        this.setting_value = setting_value;
    }

    public Function1(int feature_id, String feature_name) {
        this.feature_id = feature_id;
        this.feature_name = feature_name;
    }

//    public Function1(int team_id, String team_name) {
//        this.team_id = team_id;
//        this.team_name = team_name;
//    }
    
    
    
    

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }
    
    
    
    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    

    public int getFunction_id() {
        return function_id;
    }

    public void setFunction_id(int function_id) {
        this.function_id = function_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int features_id) {
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

    public int getComplexity_id() {
        return complexity_id;
    }

    public void setComplexity_id(int complexity_id) {
        this.complexity_id = complexity_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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



//    @Override
//    public String toString() {
//        return "Function1{" + "function_id=" + function_id + ", team_id=" + team_id + ", function_name=" + function_name + ", feature_id=" + feature_id + ", access_roles=" + access_roles + ", description=" + description + ", complexity_id=" + complexity_id + ", owner_id=" + owner_id + ", priority=" + priority + ", status=" + status + ", feature_name=" + feature_name + ", team_name=" + team_name + ", class_code=" + class_code + ", user_id=" + user_id + ", fullname=" + fullname + '}';
//    }
//    

    @Override
    public String toString() {
        return "Function1{" + "function_id=" + function_id + ", team_id=" + team_id + ", function_name=" + function_name + ", feature_id=" + feature_id + ", access_roles=" + access_roles + ", description=" + description + ", complexity_id=" + complexity_id + ", owner_id=" + owner_id + ", priority=" + priority + ", status=" + status + ", feature_name=" + feature_name + ", team_name=" + team_name + ", class_code=" + class_code + ", user_id=" + user_id + ", fullname=" + fullname + ", type_id=" + type_id + ", setting_title=" + setting_title + ", setting_value=" + setting_value +  ", class_id=" + class_id + '}';
    }
    
    
    
    
    

   
}
