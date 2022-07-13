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
public class Feature {

    private int feature_id;
    private int team_id;
    private String feature_name;
    private int status;
    private String description;
    private String team_name;
    private String class_code;
    private int class_id;

    public Feature() {
    }

    public Feature(int feature_id, int team_id, String feature_name, int status, String description, String team_name, String class_code, int class_id) {
        this.feature_id = feature_id;
        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
        this.team_name = team_name;
        this.class_code = class_code;
        this.description = description;
        this.class_id = class_id;
    }

    public Feature(int team_id, String feature_name, int status, String description) {

        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
        this.description = description;
    }

    public Feature(int feature_id, int team_id, String feature_name, int status, String description) {
        this.feature_id = feature_id;
        this.team_id = team_id;
        this.feature_name = feature_name;
        this.status = status;
        this.description = description;

    }

    public Feature(int team_id, String team_name) {
        this.team_id = team_id;
        this.team_name = team_name;

    }

    public Feature(int team_id, String team_name, String class_code) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.class_code = class_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
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

    public int getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(int feature_id) {
        this.feature_id = feature_id;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Feature{" + "feature_id=" + feature_id + ", team_id=" + team_id + ", feature_name=" + feature_name + ", status=" + status + ", description=" + description + ", team_name=" + team_name + ", class_code=" + class_code + ", class_id=" + class_id + '}';
    }

}
