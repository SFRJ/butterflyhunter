package main.java.com.djordje.apps.frontendcontrollers;


import main.java.com.djordje.apps.core.Term;
import main.java.com.djordje.apps.services.terms.TermManager;
import main.java.com.djordje.apps.services.terms.TermManagerImpl;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.List;

@ManagedBean
@RequestScoped
public class RetrieveController {

    private TermManager termsPersistencyManager;
    private List<Term> termsToDisplay;
    private String searchTerm;

    @PostConstruct
    public void init() {
        //Todo: Display by default the latest added(by time)
        termsPersistencyManager = new TermManagerImpl();
        termsToDisplay = termsPersistencyManager.getTerms();
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
        termsToDisplay = termsPersistencyManager.getTermsWithNameContaining(searchTerm);
    }
}

