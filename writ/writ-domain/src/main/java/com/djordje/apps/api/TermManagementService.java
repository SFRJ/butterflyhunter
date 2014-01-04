package main.java.com.djordje.apps.api;


import main.java.com.djordje.apps.core.Term;

import java.util.List;

public interface TermManagementService {

    public boolean add(Term term);

    public Term getByName(String name);

    public List<Term> getTerms();

    public List<Term> getTermsWithNameContaining(String partialString);

    public void update(Term term);
}
