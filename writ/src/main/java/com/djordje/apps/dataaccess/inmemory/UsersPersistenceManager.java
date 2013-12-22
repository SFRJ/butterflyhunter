package com.djordje.apps.dataaccess.inmemory;

import com.djordje.apps.model.KnowledgeProvider;

public interface UsersPersistenceManager {

    public boolean add(KnowledgeProvider knowledgeProvider);

    public KnowledgeProvider getKnowledgeProvider(String nickname);

    public void deleteKnowledgeProvider(String nickname);

    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName);
}
