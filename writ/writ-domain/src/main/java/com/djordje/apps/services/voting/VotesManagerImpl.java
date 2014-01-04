package main.java.com.djordje.apps.services.voting;

import main.java.com.djordje.apps.api.VotesManagementService;
import main.java.com.djordje.apps.core.KnowledgeProvider;
import main.java.com.djordje.apps.core.Term;

public class VotesManagerImpl implements VotesManager {

    private final VotesManagementService votesManagementService;

    public VotesManagerImpl(VotesManagementService votesManagementService) {
        this.votesManagementService = votesManagementService;
    }

    @Override
    public void plusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider) {
        votesManagementService.plusVote(sampleTerm,knowledgeProvider);
    }

    @Override
    public void minusVote(Term sampleTerm, KnowledgeProvider knowledgeProvider) {
        votesManagementService.minusVote(sampleTerm,knowledgeProvider);
    }
}
