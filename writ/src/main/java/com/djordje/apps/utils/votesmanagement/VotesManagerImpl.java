package com.djordje.apps.utils.votesmanagement;

import com.djordje.apps.errorhandling.AllreadyVotedOnThatTermException;
import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.knowledgeprovidermanagement.KnowledgeProviderManager;
import com.djordje.apps.utils.knowledgeprovidermanagement.KnowledgeProviderManagerImpl;
import com.djordje.apps.utils.termmanagement.TermManager;

import static com.djordje.apps.utils.votesmanagement.VoteValidator.canCastAVote;

public class VotesManagerImpl implements VotesManager {

    private TermManager termManager;
    KnowledgeProviderManager knowledgeProviderManager = new KnowledgeProviderManagerImpl();

    public VotesManagerImpl(TermManager termManager) {
        this.termManager = termManager;
    }

    @Override
    public void plusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider) {
        if(canCastAVote(knowledgeProvider, sampleTerm.getName())) {
            addAVoteToTerm(getTermByName(sampleTerm));
            addTermToListOfVotedTermsByKnowledProvider(knowledgeProvider, sampleTerm);
        }
        else {
            throw new AllreadyVotedOnThatTermException(knowledgeProvider.getNickname() + " already voted in term " + sampleTerm.getName());
        }
    }

    @Override
    public void minusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider) {
        if(canCastAVote(knowledgeProvider, sampleTerm.getName())) {
        deductAVoteFromTerm(sampleTerm);
        addTermToListOfVotedTermsByKnowledProvider(knowledgeProvider, sampleTerm);
        }
        else {
            throw new AllreadyVotedOnThatTermException(knowledgeProvider.getNickname() + " already voted in term " + sampleTerm.getName());
        }
    }

    private void addTermToListOfVotedTermsByKnowledProvider(KnowledgeProvider knowledgeProvider, Term term) {
        knowledgeProvider.getVotedTerms().add(term.getName());
        knowledgeProviderManager.updateVotes(knowledgeProvider,term.getName());
    }

    private Term getTermByName(Term sampleTerm) {
        return termManager.getByName(sampleTerm.getName());
    }

    private void addAVoteToTerm(Term term) {
        term.setPlusVotes(term.getPlusVotes() + 1);
        term.setTotalVotes(term.getPlusVotes() - term.getMinusVotes());
        termManager.update(term);
    }

    private void deductAVoteFromTerm(Term term) {
        term.setMinusVotes(term.getMinusVotes() + 1);
        term.setTotalVotes(term.getPlusVotes() - term.getMinusVotes());
        termManager.update(term);
    }

}
