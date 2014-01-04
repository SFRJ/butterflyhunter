package main.java.com.djordje.apps.api;


import main.java.com.djordje.apps.core.KnowledgeProvider;

public interface KnowledgeProviderManagementService {

    public boolean addKnowledeProvider(KnowledgeProvider knowledgeProvider);

    public KnowledgeProvider getKnowledgeProvider(String nickname);

    public void deleteKnowledgeProvider(String nickname);
}
