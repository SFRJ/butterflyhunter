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

    @XmlElement(name = "AUTHOR")
    private String author;
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

    public Term() {
    }

    public Term(String name, String description,String author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }

    public String getAuthor() {
        return author;
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
}
