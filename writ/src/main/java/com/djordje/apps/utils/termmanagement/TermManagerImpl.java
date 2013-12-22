package com.djordje.apps.utils.termmanagement;

import com.djordje.apps.dataaccess.inmemory.TermsPersistencyManager;
import com.djordje.apps.dataaccess.inmemory.TermsInMemoryStorage;
import com.djordje.apps.model.Term;

import java.util.List;

import static com.djordje.apps.utils.termmanagement.FunctionalSupport.*;
import static com.djordje.apps.utils.termmanagement.TermValidator.validateTerm;
import static com.googlecode.totallylazy.Sequences.sequence;


public class TermManagerImpl implements TermManager {

    private TermsPersistencyManager termsInMemoryStorage = new TermsInMemoryStorage();

    @Override
    public boolean add(Term term) {
        validateTerm(term, termsInMemoryStorage);
        return termsInMemoryStorage.add(term);
    }

    @Override
    public Term getByName(String name) {
        return termsInMemoryStorage.get(name);
    }

    @Override
    public List<Term> getTerms() {
        return termsInMemoryStorage.getAll();
    }

    @Override
    public List<Term> getTermsWithNameStartingWith(String prefix) {
        return sequence(getTerms()).map(termsStartingWith(prefix)).filter(isNotNull()).toList();
    }

    @Override
    public List<Term> getTermsWithNameContaining(String partialString) {
        return sequence(getTerms()).map(termsContaining(partialString)).filter(isNotNull()).toList();
    }

    @Override
    public void update(Term term) {
        termsInMemoryStorage.update(term);
    }
}
