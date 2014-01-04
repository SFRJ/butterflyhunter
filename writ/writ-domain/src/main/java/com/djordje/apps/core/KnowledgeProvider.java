package main.java.com.djordje.apps.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="KNOWLEDGEPROVIDER",namespace = "com.djordje.apps.model.KnowledgeProvider")
@XmlAccessorType(XmlAccessType.FIELD)
public class KnowledgeProvider {

    @XmlElement(name = "NICKNAME")
    private String nickname;
    @XmlElement(name = "PASSWORD")
    private String password;
    @XmlElement(name = "VOTEDTERMS")
    private List<String> votedTerms = new ArrayList<String>();
    @XmlElement(name = "CREATIONTIME")
    private long creationTime;

    public KnowledgeProvider() {
    }

    public KnowledgeProvider(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getVotedTerms() {
        return votedTerms;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getCreationTime() {
        return creationTime;
    }
}
