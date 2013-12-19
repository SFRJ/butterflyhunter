package com.djordje.apps.utils.termmanagement;

import com.djordje.apps.model.Term;

import java.util.List;

public interface TermManager {

    public boolean add(Term term);

    public Term getByName(String name);

    public List<Term> getTerms();

    public List<Term> getTermsWithNameStartingWith(String prefix);

    public List<Term> getTermsWithNameContaining(String partialString);

    public void update(Term term);
}
