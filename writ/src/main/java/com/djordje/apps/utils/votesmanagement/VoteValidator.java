package com.djordje.apps.utils.votesmanagement;

import com.djordje.apps.errorhandling.AllreadyVotedOnThatTermException;
import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;

public class VoteValidator {

    protected static void validateKnoledgeProviderAlreadyVoted(Term term, KnowledgeProvider knowledgeProvider) {
        if(term.getVoters().contains(knowledgeProvider.getNickname()))
            throw new AllreadyVotedOnThatTermException(knowledgeProvider.getNickname() + " already voted on the term " + term);
        term.getVoters().add(knowledgeProvider);
    }


}
