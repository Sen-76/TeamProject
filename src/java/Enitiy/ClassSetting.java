/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitiy;

/**
 *
 * @author tqbao
 */
public class ClassSetting {
    int id;
    int type_id;
    String title_type;
    int type_value;
    int class_id;
    String color;
    String description;
    String created_at;
    String due_date;
    String label;

    public ClassSetting() {
    }

    public ClassSetting(int id, int type_id, String title_type, int type_value, int class_id, String color, String description) {
        this.id = id;
        this.type_id = type_id;
        this.title_type = title_type;
        this.type_value = type_value;
        this.class_id = class_id;
        this.color = color;
        this.description = description;
    }

    public ClassSetting(int id, int type_id, String title_type, int type_value, int class_id, String color, String description, String created_at, String due_date) {
        this.id = id;
        this.type_id = type_id;
        this.title_type = title_type;
        this.type_value = type_value;
        this.class_id = class_id;
        this.color = color;
        this.description = description;
        this.created_at = created_at;
        this.due_date = due_date;
    }

    public ClassSetting(int id, int type_id, String title_type, int type_value, int class_id, String color, String description, String created_at, String due_date, String label) {
        this.id = id;
        this.type_id = type_id;
        this.title_type = title_type;
        this.type_value = type_value;
        this.class_id = class_id;
        this.color = color;
        this.description = description;
        this.created_at = created_at;
        this.due_date = due_date;
        this.label = label;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTitle_type() {
        return title_type;
    }

    public void setTitle_type(String title_type) {
        this.title_type = title_type;
    }

    public int getType_value() {
        return type_value;
    }

    public void setType_value(int tye_value) {
        this.type_value = tye_value;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ClassSetting{" + "id=" + id + ", type_id=" + type_id + ", title_type=" + title_type + ", type_value=" + type_value + ", class_id=" + class_id + ", color=" + color + ", description=" + description + ", created_at=" + created_at + ", due_date=" + due_date + ", label=" + label + '}';
    }

    
    
}
