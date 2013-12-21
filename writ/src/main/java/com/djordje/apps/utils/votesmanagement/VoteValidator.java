package com.djordje.apps.utils.votesmanagement;

import com.djordje.apps.errorhandling.AllreadyVotedOnThatTermException;
import com.djordje.apps.model.KnowledgeProvider;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.knowledgeprovidermanagement.KnowledgeProviderManager;
import com.djordje.apps.utils.knowledgeprovidermanagement.KnowledgeProviderManagerImpl;

public class VoteValidator {

    public static boolean canCastAVote(KnowledgeProvider knowledgeProvider, String termName) {
        for(String currentTerm : knowledgeProvider.getVotedTerms())
            if(currentTerm.equalsIgnoreCase(termName))
                return false;
        return true;
    }

}
