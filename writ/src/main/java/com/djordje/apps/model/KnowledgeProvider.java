package com.djordje.apps.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="KNOWLEDGEPROVIDER",namespace = "com.djordje.apps.model.KnowledgeProvider")
@XmlAccessorType(XmlAccessType.FIELD)
public class KnowledgeProvider {

    @XmlElement(name = "NICKNAME")
    private String nickname;
    @XmlElement(name = "VOTEDTERMS")
    private List<String> votedTerms = new ArrayList<String>();

    public KnowledgeProvider() {
    }

    public KnowledgeProvider(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<String> getVotedTerms() {
        return votedTerms;
    }
}
