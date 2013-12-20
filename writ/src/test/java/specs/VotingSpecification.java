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

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aTermWithExactly400Characters;

public class VotingSpecification {

    private final KnowledgeProvider knowledgeProvider = new KnowledgeProvider("sfrj");
    private final TermManager termManager = new TermManagerImpl();
    private final VotesManager votesManager = new VotesManagerImpl(termManager);
    private final Term term = aTermWithExactly400Characters();


    @Before
    public void eraseAllCreatedFiles() {
        File folder = new File("/home/pro/Desktop/glassfish4/temporatyxmlstorage/");
        for(File current : folder.listFiles())
            if(current.isFile())
                current.delete();
    }

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

    @Test
    public void every_term_knows_about_the_voters_that_voted_on_it() {
        termManager.add(term);
        votesManager.plusVote(term,knowledgeProvider);
        Term votedTerm = termManager.getByName(term.getName());
        votedTerm.getVoters().add(term.getVoters().get(0));
        String voterName = termManager.getByName(term.getName()).getName();
        assertThat(voterName,is(knowledgeProvider.getNickname()));
    }

}