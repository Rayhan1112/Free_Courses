package com.freecourses;

public class suggestion {
    String name,suggestion;
    suggestion(){

    }
    public suggestion(String name,String suggestion){
        this.name=name;
        this.suggestion=suggestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestio(String suggestio) {
        this.suggestion = suggestion;
    }
}
