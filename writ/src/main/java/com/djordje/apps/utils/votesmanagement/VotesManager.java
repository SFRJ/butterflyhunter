package com.djordje.apps.utils.votesmanagement;

import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;

public interface VotesManager {
    public void plusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider);

    public void minusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider);
}
