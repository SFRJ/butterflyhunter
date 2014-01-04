package main.java.com.djordje.apps.dataaccess.inmemory.terms;

import main.java.com.djordje.apps.api.TermManagementService;
import main.java.com.djordje.apps.core.Term;
import main.java.com.djordje.apps.core.errors.LongDescriptionException;
import main.java.com.djordje.apps.core.errors.TermAlreadyExistsException;

public class TermValidator {

    protected static boolean validateTerm(Term term,TermManagementService termsPersistencyManager) {
        return isTermNotInStorage(term.getName(), termsPersistencyManager) &&
                isDescriptionSmallerOrEqual400Characters(term.getDescription());
    }

    private static boolean isDescriptionSmallerOrEqual400Characters(String description) {
        if(description.length() > 400)
            throw new LongDescriptionException("The maximum description length is 400 but was " + description.length());
        return  true;
    }

    private static boolean isTermNotInStorage(String key,TermManagementService termsPersistencyManager) {
        if(termsPersistencyManager.getByName(key) != null)
            throw new TermAlreadyExistsException("The term " + key + " already exists in the storage");
        return true;
    }
}
