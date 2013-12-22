package com.djordje.apps.utils.knowledgeprovidermanagement;

import com.djordje.apps.model.KnowledgeProvider;

public class KnowledgeProviderManagerImpl implements KnowledgeProviderManager {

    @Override
    public boolean add(KnowledgeProvider knowledgeProvider) {
        return false;
    }

    @Override
    public KnowledgeProvider getKnowledgeProvider(String nickname) {
        return new KnowledgeProvider();
    }

    @Override
    public void deleteKnowledgeProvider(String nickname) {
    }

    @Override
    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName) {
        //This should go in the persistency management class
        KnowledgeProvider provider = getKnowledgeProvider(knowledgeProvider.getNickname());
        provider.getVotedTerms().add(termName);
        deleteKnowledgeProvider(knowledgeProvider.getNickname());
        add(knowledgeProvider);
    }
}
