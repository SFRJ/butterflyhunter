package com.djordje.apps.utils.termmanagement;

import com.djordje.apps.dataaccess.PersistencyManager;
import com.djordje.apps.errorhandling.LongDescriptionException;
import com.djordje.apps.errorhandling.TermAlreadyExistsException;
import com.djordje.apps.model.Term;

public class TermValidator {

    protected static boolean validateTerm(Term term,PersistencyManager persistencyManager) {
        return isTermNotInStorage(term.getName(), persistencyManager) &&
                isDescriptionSmallerOrEqual400Characters(term.getDescription());
    }

    private static boolean isDescriptionSmallerOrEqual400Characters(String description) {
        if(description.length() > 400)
            throw new LongDescriptionException("The maximum description length is 400 but was " + description.length());
        return  true;
    }

    private static boolean isTermNotInStorage(String key,PersistencyManager persistencyManager) {
        if(persistencyManager.get(key) != null)
            throw new TermAlreadyExistsException("The term " + key + " already exists in the storage");
        return true;
    }
}
