package com.djordje.apps.frontendcontrollers;

import com.djordje.apps.dataaccess.PersistencyManager;
import com.djordje.apps.dataaccess.inmemory.TermsInMemoryStorage;
import com.djordje.apps.model.Term;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

@ManagedBean
@RequestScoped
public class RetrieveController {

    private PersistencyManager persistencyManager;
    private List<Term> termsToDisplay;
    private String searchTerm;

    @PostConstruct
    public void init() {
        //Todo: Display by default the latest added(by time)
        persistencyManager = new TermsInMemoryStorage();
        termsToDisplay = persistencyManager.getAll();
        searchTerm = "";
    }

    public List<Term> getTerms() {
        return termsToDisplay;
    }

    public void setTermsToDisplay(List<Term> termsToDisplay) {
        this.termsToDisplay = termsToDisplay;
    }

    //TODO: Add search funcionality that with AJAX returns a list of all those terms that contain the given string.
    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void searchTermThatContainsString(AjaxBehaviorEvent ajaxBehaviorEvent) {
        termsToDisplay = persistencyManager.getAllContaining(searchTerm);
    }
}
