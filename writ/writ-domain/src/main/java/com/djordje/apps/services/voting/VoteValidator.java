package main.java.com.djordje.apps.services.voting;

import main.java.com.djordje.apps.core.KnowledgeProvider;

public class VoteValidator {

    public static boolean canCastAVote(KnowledgeProvider knowledgeProvider, String termName) {
        for(String currentTerm : knowledgeProvider.getVotedTerms())
            if(currentTerm.equalsIgnoreCase(termName))
                return false;
        return true;
    }

}
