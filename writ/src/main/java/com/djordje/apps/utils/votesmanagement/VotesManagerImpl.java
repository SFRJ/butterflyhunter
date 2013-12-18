package com.djordje.apps.utils.votesmanagement;

import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.termmanagement.TermManager;

import static com.djordje.apps.utils.votesmanagement.VoteValidator.validateKnoledgeProviderAlreadyVoted;

public class VotesManagerImpl implements VotesManager {

    private TermManager termManager;

    public VotesManagerImpl(TermManager termManager) {
        this.termManager = termManager;
    }

    @Override
    public void plusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider) {
        Term term = termManager.getByName(sampleTerm.getName());
        validateKnoledgeProviderAlreadyVoted(sampleTerm,knowledgeProvider);
        term.setPlusVotes(term.getPlusVotes() + 1);
        term.setTotalVotes(term.getTotalVotes() + 1);
    }

    @Override
    public void minusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider) {
        Term term = termManager.getByName(sampleTerm.getName());
        validateKnoledgeProviderAlreadyVoted(sampleTerm,knowledgeProvider);
        term.setMinusVotes(term.getMinusVotes() - 1);
        term.setTotalVotes(term.getTotalVotes() - 1);
        termManager.update(sampleTerm);
    }

}
