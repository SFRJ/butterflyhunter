package specs;

import com.djordje.apps.utils.termmanagement.TermManager;
import com.djordje.apps.utils.termmanagement.TermManagerImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aCustomTermWith;
import static specs.support.TermStubs.aTermWithExactly400Characters;

public class TermRetrievalSpecification {

    private TermManager manager = new TermManagerImpl();

    @Before
    public void eraseAllCreatedFiles() {
        File folder = new File("/home/pro/Desktop/glassfish4/temporatyxmlstorage/");
        for(File current : folder.listFiles())
            if(current.isFile())
                current.delete();
    }

    @Test
    public void returns_all_elements_in_storage() {
        manager.add(aTermWithExactly400Characters());
        assertThat(manager.getTerms().size(),is(1));
    }

    @Test
    public void return_all_terms_that_start_with() {
        manager.add(aCustomTermWith("DN", "Directory Number..."));
        manager.add(aCustomTermWith("MPF", "Metallic Path Facility..."));
        manager.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility..."));
        manager.add(aCustomTermWith("BT", "British Telecom..."));
        assertThat(manager.getTermsWithNameStartingWith("S").size(),is(1));
    }

    @Test
    public void return_all_terms_that_contain() {
        manager.add(aCustomTermWith("DN", "Directory Number..."));
        manager.add(aCustomTermWith("MPF", "Metallic Path Facility..."));
        manager.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility..."));
        manager.add(aCustomTermWith("BT", "British Telecom..."));
        assertThat(manager.getTermsWithNameContaining("MPF").size(),is(2));
    }

    @Test
    public void return_term_is_case_insensitive() {
        manager.add(aCustomTermWith("DN", "Directory Number..."));
        manager.add(aCustomTermWith("MPF", "Metallic Path Facility..."));
        manager.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility..."));
        manager.add(aCustomTermWith("BT", "British Telecom..."));

        int upperCaseResults = manager.getTermsWithNameContaining("MPF").size();
        int lowerCaseResults = manager.getTermsWithNameContaining("mpf").size();
        assertThat(upperCaseResults,is(lowerCaseResults));
    }
}
