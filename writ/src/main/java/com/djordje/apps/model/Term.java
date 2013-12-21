package com.djordje.apps.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="TERM",namespace = "com.djordje.apps.model.Term")
@XmlAccessorType(XmlAccessType.FIELD)
public class Term {

    public Term() {
    }

    @XmlElement(name = "NAME")
    private String name;
    @XmlElement(name = "DESCRIPTION")
    private String description;
    @XmlElement(name = "PLUSVOTES")
    private int plusVotes;
    @XmlElement(name = "MINUSVOTES")
    private int minusVotes;
    @XmlElement(name = "TOTALVOTES")
    private int totalVotes;
    @XmlElement(name = "CREATIONTIME")
    private long creationTime;

    public Term(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPlusVotes() {
        return plusVotes;
    }

    public int getMinusVotes() {
        return minusVotes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setPlusVotes(int plusVotes) {
        this.plusVotes = plusVotes;
    }

    public void setMinusVotes(int minusVotes) {
        this.minusVotes = minusVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreationPath() {
        if(getCreationTime() > 0L){
            //TODO: A property is needed to this(it is specific to the environment where the app runs)
            return "/home/pro/Desktop/glassfish4/temporatyxmlstorage/" + getName() + getCreationTime() + ".xml";
        }
        return null;
    }
}
