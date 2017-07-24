package com.felipeinocencio.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private int id;
    private String name;
    private String course;
    private String email;

    public Student(){}
    
    public Student(@JsonProperty("id")int id,@JsonProperty("name")String name,
     	   @JsonProperty("course")String course,@JsonProperty("email")String email){ 
 			this.id = id;
 			this.name = name;
 	        this.course = course;
 	        this.email = email;
        
     }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
