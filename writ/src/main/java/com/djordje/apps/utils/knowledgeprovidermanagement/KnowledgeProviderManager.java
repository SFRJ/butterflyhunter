package com.djordje.apps.utils.knowledgeprovidermanagement;

import com.djordje.apps.model.KnowledgeProvider;

public interface KnowledgeProviderManager {

    public boolean add(KnowledgeProvider knowledgeProvider);

    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName);

    public KnowledgeProvider getKnowledgeProvider(String nickname);

    public void deleteKnowledgeProvider(String nickname);
}
