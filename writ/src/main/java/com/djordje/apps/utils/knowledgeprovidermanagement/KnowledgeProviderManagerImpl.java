package com.djordje.apps.utils.knowledgeprovidermanagement;

import com.djordje.apps.dataaccess.inmemory.UsersInMemoryStorage;
import com.djordje.apps.dataaccess.inmemory.UsersPersistenceManager;
import com.djordje.apps.model.KnowledgeProvider;

public class KnowledgeProviderManagerImpl implements KnowledgeProviderManager {

    UsersPersistenceManager usersPersistenceManager = new UsersInMemoryStorage();

    @Override
    public boolean add(KnowledgeProvider knowledgeProvider) {
        return usersPersistenceManager.add(knowledgeProvider);
    }

    @Override
    public KnowledgeProvider getKnowledgeProvider(String nickname) {
        return usersPersistenceManager.getKnowledgeProvider(nickname);
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
