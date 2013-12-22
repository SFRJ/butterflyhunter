package specs;

import com.djordje.apps.errorhandling.AllreadyVotedOnThatTermException;
import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.termmanagement.TermManager;
import com.djordje.apps.utils.termmanagement.TermManagerImpl;
import com.djordje.apps.utils.votesmanagement.VotesManager;
import com.djordje.apps.utils.votesmanagement.VotesManagerImpl;
import org.junit.Before;
import org.junit.Test;
import specs.support.Cleanup;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aTermWithExactly400Characters;

public class VotingSpecification extends Cleanup {

    private final KnowledgeProvider knowledgeProvider = new KnowledgeProvider("sfrj");
    private final TermManager termManager = new TermManagerImpl();
    private final VotesManager votesManager = new VotesManagerImpl(termManager);
    private final Term term = aTermWithExactly400Characters();

    @Test
    public void a_term_can_get_possitive_votes() {
        termManager.add(term);
        votesManager.plusVote(term, knowledgeProvider);
        assertThat(termManager.getByName("SampleTerm").getPlusVotes(), is(1));
        assertThat(termManager.getByName(term.getName()).getTotalVotes(), is(1));
    }

    @Test
    public void a_term_can_get_negative_votes() {
        termManager.add(term);
        votesManager.minusVote(term,knowledgeProvider);
        assertThat(termManager.getByName("SampleTerm").getMinusVotes(), is(1));
        assertThat(termManager.getByName(term.getName()).getTotalVotes(), is(-1));
    }

    @Test
    public void a_term_can_calculate_the_total_given_votes() {
        termManager.add(term);
        votesManager.minusVote(term, knowledgeProvider);
        assertThat(termManager.getByName(term.getName()).getTotalVotes(), is(-1));
    }

    @Test(expected = AllreadyVotedOnThatTermException.class)
    public void a_knowledge_provider_can_give_only_one_vote_to_one_term() {
        termManager.add(term);
        votesManager.plusVote(term,knowledgeProvider);
        votesManager.minusVote(term, knowledgeProvider);
    }
}