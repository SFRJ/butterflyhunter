package test;

import main.java.com.djordje.apps.core.KnowledgeProvider;
import main.java.com.djordje.apps.core.Term;
import main.java.com.djordje.apps.services.errorhandling.AllreadyVotedOnThatTermException;
import main.java.com.djordje.apps.services.knowledge.KnowledgeProviderManager;
import main.java.com.djordje.apps.services.knowledge.KnowledgeProviderManagerImpl;
import main.java.com.djordje.apps.services.terms.TermManager;
import main.java.com.djordje.apps.services.terms.TermManagerImpl;
import main.java.com.djordje.apps.services.voting.VotesManager;
import main.java.com.djordje.apps.services.voting.VotesManagerImpl;
import org.junit.Test;
import test.support.Cleanup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static test.support.TermStubs.aTermWithExactly400Characters;

public class VotingSpecification extends Cleanup {

    private final KnowledgeProvider knowledgeProvider = new KnowledgeProvider("sfrj");
    private final TermManager termManager = new TermManagerImpl();
    private final VotesManager votesManager = new VotesManagerImpl(termManager);
    private final KnowledgeProviderManager knowledgeProviderManager = new KnowledgeProviderManagerImpl();
    private final Term term = aTermWithExactly400Characters();




    @Test
    public void a_term_can_get_possitive_votes() {
        knowledgeProviderManager.add(knowledgeProvider);
        termManager.add(term);
        votesManager.plusVote(term, knowledgeProvider);
        assertThat(termManager.getByName("SampleTerm").getPlusVotes(), is(1));
        assertThat(termManager.getByName(term.getName()).getTotalVotes(), is(1));
    }

    @Test
    public void a_term_can_get_negative_votes() {
        knowledgeProviderManager.add(knowledgeProvider);
        termManager.add(term);
        votesManager.minusVote(term,knowledgeProvider);
        assertThat(termManager.getByName("SampleTerm").getMinusVotes(), is(1));
        assertThat(termManager.getByName(term.getName()).getTotalVotes(), is(-1));
    }

    @Test
    public void a_term_can_calculate_the_total_given_votes() {
        knowledgeProviderManager.add(knowledgeProvider);
        termManager.add(term);
        votesManager.minusVote(term, knowledgeProvider);
        assertThat(termManager.getByName(term.getName()).getTotalVotes(), is(-1));
    }

    @Test(expected = AllreadyVotedOnThatTermException.class)
    public void a_knowledge_provider_can_give_only_one_vote_to_one_term() {
        knowledgeProviderManager.add(knowledgeProvider);
        termManager.add(term);
        votesManager.plusVote(term,knowledgeProvider);
        votesManager.minusVote(term, knowledgeProvider);
    }
}