package specs;

import com.djordje.apps.utils.termmanagement.TermManager;
import com.djordje.apps.utils.termmanagement.TermManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specs.support.Cleanup;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aCustomTermWith;
import static specs.support.TermStubs.aTermWithExactly400Characters;

public class TermRetrievalSpecification extends Cleanup {

    private TermManager manager = new TermManagerImpl();

    @Test
    public void returns_all_elements_in_storage() {
        manager.add(aTermWithExactly400Characters());
        assertThat(manager.getTerms().size(),is(1));
    }

    @Test
    public void return_all_terms_that_start_with() {
        manager.add(aCustomTermWith("DN", "Directory Number...","Mr. Bombastic"));
        manager.add(aCustomTermWith("MPF", "Metallic Path Facility...","Mr. Bombastic"));
        manager.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility...","Mr. Bombastic"));
        manager.add(aCustomTermWith("BT", "British Telecom...","Mr. Bombastic"));
        assertThat(manager.getTermsWithNameStartingWith("S").size(),is(1));
    }

    @Test
    public void return_all_terms_that_contain() {
        manager.add(aCustomTermWith("DN", "Directory Number...","Mr. Bombastic"));
        manager.add(aCustomTermWith("MPF", "Metallic Path Facility...","Mr. Bombastic"));
        manager.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility...","Mr. Bombastic"));
        manager.add(aCustomTermWith("BT", "British Telecom...","Mr. Bombastic"));
        assertThat(manager.getTermsWithNameContaining("MPF").size(),is(2));
    }

    @Test
    public void return_term_is_case_insensitive() {
        manager.add(aCustomTermWith("DN", "Directory Number...","Mr. Bombastic"));
        manager.add(aCustomTermWith("MPF", "Metallic Path Facility...","Mr. Bombastic"));
        manager.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility...","Mr. Bombastic"));
        manager.add(aCustomTermWith("BT", "British Telecom...","Mr. Bombastic"));

        int upperCaseResults = manager.getTermsWithNameContaining("MPF").size();
        int lowerCaseResults = manager.getTermsWithNameContaining("mpf").size();
        assertThat(upperCaseResults,is(lowerCaseResults));
    }
}
