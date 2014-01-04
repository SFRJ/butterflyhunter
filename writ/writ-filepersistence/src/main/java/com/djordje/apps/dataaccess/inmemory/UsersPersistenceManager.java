package main.java.com.djordje.apps.dataaccess.inmemory;

import main.java.com.djordje.apps.core.KnowledgeProvider;

import java.io.File;

public interface UsersPersistenceManager {

    public final File PATH_TO_FILE_REPOSITORY = new File("/home/pro/Desktop/glassfish4/temporaryxmlstorage/users/");

    public boolean add(KnowledgeProvider knowledgeProvider);

    public KnowledgeProvider getKnowledgeProvider(String nickname);

    public void deleteKnowledgeProvider(String nickname);
    //TODO Implement this(Required when voting on a term)
    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName);
}
