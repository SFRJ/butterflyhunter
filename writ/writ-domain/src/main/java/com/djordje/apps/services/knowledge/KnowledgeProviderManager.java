package main.java.com.djordje.apps.services.knowledge;


import main.java.com.djordje.apps.core.KnowledgeProvider;

public interface KnowledgeProviderManager {

    public boolean add(KnowledgeProvider knowledgeProvider);

    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName);

    public KnowledgeProvider getKnowledgeProvider(String nickname);

    public void deleteKnowledgeProvider(String nickname);
}
