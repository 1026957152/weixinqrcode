package com.coalvalue.domain.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by yuan zhao  on 08/10/2015.
 */

@Entity


@Table(name = "scan")

public class Scan extends BaseDomain {



    private String status;


    private LocalDateTime expireDate;
    private String state;

    private String sessionId;
    private String scenarioAnchorId;

    private String scenarioAttachId;

    private String scenario;
    private String referenceId;
    private String occupationId;
    private String referenceType;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    public void setScenarioAnchorId(String objectId) {

        this.scenarioAnchorId = objectId;
    }

    public String getScenarioAnchorId() {
        return scenarioAnchorId;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setScenarioAttachId(String scenarioAttachId) {
        this.scenarioAttachId = scenarioAttachId;
    }

    public String getScenarioAttachId() {
        return scenarioAttachId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }


    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getScenario() {
        return scenario;
    }

    public void setReferenceId(String referenceId) {

        this.referenceId = referenceId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setOccupationId(String occupationId) {

        this.occupationId = occupationId;
    }

    public String getOccupationId() {
        return occupationId;
    }

    public void setReferenceType(String referenceType) {

        this.referenceType = referenceType;
    }

    public String getReferenceType() {
        return referenceType;
    }


    ;
}
