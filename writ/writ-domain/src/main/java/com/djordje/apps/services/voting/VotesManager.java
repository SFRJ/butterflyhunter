package main.java.com.djordje.apps.services.voting;


import main.java.com.djordje.apps.core.KnowledgeProvider;
import main.java.com.djordje.apps.core.Term;

public interface VotesManager {
    public void plusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider);

    public void minusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider);
}
