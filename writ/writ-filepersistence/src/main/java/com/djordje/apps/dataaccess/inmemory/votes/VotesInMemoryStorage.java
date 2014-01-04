package main.java.com.djordje.apps.dataaccess.inmemory.votes;

import main.java.com.djordje.apps.api.VotesManagementService;
import main.java.com.djordje.apps.core.KnowledgeProvider;
import main.java.com.djordje.apps.core.Term;
import main.java.com.djordje.apps.core.errors.AllreadyVotedOnThatTermException;
import main.java.com.djordje.apps.dataaccess.inmemory.knowledgeproviders.KnowledgeProvidersInMemoryStorage;
import main.java.com.djordje.apps.dataaccess.inmemory.terms.TermsInMemoryStorage;

import static main.java.com.djordje.apps.dataaccess.inmemory.votes.VoteValidator.canCastAVote;

public class VotesInMemoryStorage implements VotesManagementService {

    //Will use this to update the votes
    private KnowledgeProvidersInMemoryStorage knowledgeProvidersInMemoryStorage = new KnowledgeProvidersInMemoryStorage();
    private TermsInMemoryStorage termsInMemoryStorage =  new TermsInMemoryStorage();

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
    private void addAVoteToTerm(Term term) {
        term.setPlusVotes(term.getPlusVotes() + 1);
        term.setTotalVotes(term.getPlusVotes() - term.getMinusVotes());
        termsInMemoryStorage.update(term);
    }

    private Term getTermByName(Term sampleTerm) {
        return termsInMemoryStorage.getByName(sampleTerm.getName());
    }

    private void addTermToListOfVotedTermsByKnowledProvider(KnowledgeProvider knowledgeProvider, Term term) {
        knowledgeProvider.getVotedTerms().add(term.getName());
        knowledgeProvidersInMemoryStorage.updateVotes(knowledgeProvider, term.getName());
    }

    private void deductAVoteFromTerm(Term term) {
        term.setMinusVotes(term.getMinusVotes() + 1);
        term.setTotalVotes(term.getPlusVotes() - term.getMinusVotes());
        termsInMemoryStorage.update(term);
    }


}
