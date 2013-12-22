package com.djordje.apps.dataaccess.inmemory;

import com.djordje.apps.model.KnowledgeProvider;

public class UsersInMemoryStorage implements UsersPersistenceManager{


    @Override
    public boolean add(KnowledgeProvider knowledgeProvider) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public KnowledgeProvider getKnowledgeProvider(String nickname) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteKnowledgeProvider(String nickname) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName) {
        KnowledgeProvider provider = getKnowledgeProvider(knowledgeProvider.getNickname());
        provider.getVotedTerms().add(termName);
        deleteKnowledgeProvider(knowledgeProvider.getNickname());
        add(knowledgeProvider);
    }
}
