package specs;

import com.djordje.apps.errorhandling.NicknameTakenException;
import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.knowledgeprovidermanagement.KnowledgeProviderManager;
import com.djordje.apps.utils.knowledgeprovidermanagement.KnowledgeProviderManagerImpl;
import com.djordje.apps.utils.termmanagement.TermManager;
import com.djordje.apps.utils.termmanagement.TermManagerImpl;
import com.djordje.apps.utils.votesmanagement.VotesManager;
import com.djordje.apps.utils.votesmanagement.VotesManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specs.support.Cleanup;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aTermWithExactly400Characters;

public class KnowledgeProviderSpecification /*extends Cleanup*/ {

    private final KnowledgeProvider knowledgeProvider = new KnowledgeProvider("sfrj");
    private final TermManager termManager = new TermManagerImpl();
    private final VotesManager votesManager = new VotesManagerImpl(termManager);
    private final Term term = aTermWithExactly400Characters();
    private final KnowledgeProviderManager knowledgeProviderManager = new KnowledgeProviderManagerImpl();

    @Test
    public void a_knowledge_provider_can_register() {
        knowledgeProvider.setPassword("12345");
        knowledgeProviderManager.add(knowledgeProvider);
        KnowledgeProvider savedKnowledgeProvider = knowledgeProviderManager.getKnowledgeProvider("sfrj");
        assertThat(savedKnowledgeProvider.getNickname(),is("sfrj"));
    }

    @Test(expected = NicknameTakenException.class)
    public void a_knowledge_provider_cannot_register_if_the_nickname_is_taken() {
        knowledgeProvider.setPassword("12345");
        knowledgeProviderManager.add(knowledgeProvider);
        knowledgeProviderManager.add(knowledgeProvider);
    }


    @Test
    public void when_a_knowledge_provider_votes_on_a_term_the_term_name_is_added_to_the_list_of_voted_terms() {
        termManager.add(term);
        votesManager.plusVote(term,knowledgeProvider);
        boolean containsVotedTerm = false;
        for(String currentTerm : knowledgeProviderManager.getKnowledgeProvider("sfrj").getVotedTerms()) {
            if(currentTerm.equalsIgnoreCase(term.getName())) {
                containsVotedTerm = true;
            break;
            }
        }
        assertThat(containsVotedTerm,is(true));
    }

}
