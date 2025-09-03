package com.bajajfinservhealth.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SubmitQueryRequest {
    
    @JsonProperty("finalQuery")
    private String finalQuery;
    

    public SubmitQueryRequest() {}
    

    public SubmitQueryRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }
    

    public String getFinalQuery() {
        return finalQuery;
    }
    
    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }
    
    @Override
    public String toString() {
        return "SubmitQueryRequest{" +
                "finalQuery='" + finalQuery + '\'' +
                '}';
    }
}
