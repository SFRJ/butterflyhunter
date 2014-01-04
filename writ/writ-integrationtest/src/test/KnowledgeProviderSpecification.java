package test;

import main.java.com.djordje.apps.core.KnowledgeProvider;
import main.java.com.djordje.apps.core.Term;
import main.java.com.djordje.apps.services.errorhandling.NicknameTakenException;
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

public class KnowledgeProviderSpecification extends Cleanup {

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
        knowledgeProviderManager.add(knowledgeProvider);
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
