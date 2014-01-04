package main.java.com.djordje.apps.services.terms;

import com.djordje.apps.dataaccess.inmemory.TermsPersistencyManager;
import com.djordje.apps.errorhandling.LongDescriptionException;
import com.djordje.apps.errorhandling.TermAlreadyExistsException;
import com.djordje.apps.model.Term;

public class TermValidator {

    protected static boolean validateTerm(Term term,TermsPersistencyManager termsPersistencyManager) {
        return isTermNotInStorage(term.getName(), termsPersistencyManager) &&
                isDescriptionSmallerOrEqual400Characters(term.getDescription());
    }

    private static boolean isDescriptionSmallerOrEqual400Characters(String description) {
        if(description.length() > 400)
            throw new LongDescriptionException("The maximum description length is 400 but was " + description.length());
        return  true;
    }

    private static boolean isTermNotInStorage(String key,TermsPersistencyManager termsPersistencyManager) {
        if(termsPersistencyManager.get(key) != null)
            throw new TermAlreadyExistsException("The term " + key + " already exists in the storage");
        return true;
    }
}
