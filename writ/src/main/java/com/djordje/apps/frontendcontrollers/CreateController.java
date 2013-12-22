package com.djordje.apps.frontendcontrollers;

import com.djordje.apps.errorhandling.TermAlreadyExistsException;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.termmanagement.TermManager;
import com.djordje.apps.utils.termmanagement.TermManagerImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CreateController {

    private String author;
    private String termName;
    private String termDescription;

    public CreateController() {
        this.author = "Get the author name from the session!!!!";
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermDescription() {
        return termDescription;
    }

    public void setTermDescription(String termDescription) {
        this.termDescription = termDescription;
    }

    public void saveTerm() {
        TermManager termManager = new TermManagerImpl();
        try {
            termManager.add(new Term(termName,termDescription,author));
        }
        catch (TermAlreadyExistsException e) {
            FacesMessage facesMessage =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Term already exist", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

}
