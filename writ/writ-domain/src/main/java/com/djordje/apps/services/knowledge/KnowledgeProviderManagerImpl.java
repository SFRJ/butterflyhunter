package main.java.com.djordje.apps.services.knowledge;

import main.java.com.djordje.apps.api.KnowledgeProviderManagementService;
import main.java.com.djordje.apps.core.KnowledgeProvider;

public class KnowledgeProviderManagerImpl implements KnowledgeProviderManager {

    private final KnowledgeProviderManagementService knowledgeProviderManagementService;

    public KnowledgeProviderManagerImpl(KnowledgeProviderManagementService knowledgeProviderManagementService) {
        this.knowledgeProviderManagementService = knowledgeProviderManagementService;
    }

    @Override
    public boolean add(KnowledgeProvider knowledgeProvider) {
        return knowledgeProviderManagementService.addKnowledeProvider(knowledgeProvider);
    }

    @Override
    public KnowledgeProvider getKnowledgeProvider(String nickname) {
        return knowledgeProviderManagementService.getKnowledgeProvider(nickname);
    }

    @Override
    public void deleteKnowledgeProvider(String nickname) {
        knowledgeProviderManagementService.deleteKnowledgeProvider(nickname);
    }

    @Override
    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName) {

    }
}
