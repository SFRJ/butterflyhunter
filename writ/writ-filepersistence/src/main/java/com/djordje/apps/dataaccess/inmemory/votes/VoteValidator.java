package main.java.com.djordje.apps.dataaccess.inmemory.votes;

import main.java.com.djordje.apps.core.KnowledgeProvider;

public class VoteValidator {

    public static boolean canCastAVote(KnowledgeProvider knowledgeProvider, String termName) {
        for(String currentTerm : knowledgeProvider.getVotedTerms())
            if(currentTerm.equalsIgnoreCase(termName))
                return false;
        return true;
    }

}
