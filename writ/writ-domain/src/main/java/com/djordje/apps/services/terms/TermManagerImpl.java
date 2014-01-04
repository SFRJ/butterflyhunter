package main.java.com.djordje.apps.services.terms;

import main.java.com.djordje.apps.api.TermManagementService;
import main.java.com.djordje.apps.core.Term;
import java.util.List;

public class TermManagerImpl implements TermManager {

    private final TermManagementService termManagementService;

    public TermManagerImpl(TermManagementService termManagementService) {
        this.termManagementService = termManagementService;
    }

    @Override
    public boolean add(Term term) {
        return termManagementService.add(term);
    }

    @Override
    public Term getByName(String name) {
        return termManagementService.getByName(name);
    }

    @Override
    public List<Term> getTerms() {
        return termManagementService.getTerms();
    }

    @Override
    public List<Term> getTermsWithNameContaining(String partialString) {
        return termManagementService.getTermsWithNameContaining(partialString);
    }

    @Override
    public void update(Term term) {
        termManagementService.update(term);
    }
}
