package test;

import main.java.com.djordje.apps.core.Term;
import main.java.com.djordje.apps.services.errorhandling.LongDescriptionException;
import main.java.com.djordje.apps.services.errorhandling.TermAlreadyExistsException;
import main.java.com.djordje.apps.services.terms.TermManager;
import main.java.com.djordje.apps.services.terms.TermManagerImpl;
import org.junit.Test;
import test.support.Cleanup;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static test.support.TermStubs.aTermWithExactly400Characters;
import static test.support.TermStubs.aTermWithMoreThan400Characters;

public class AddingANewTermSpecification extends Cleanup {

    private TermManager manager = new TermManagerImpl();

    @Test
    public void a_new_term_is_marshalled() {
        Term term = new Term("AAA","AAA","Mr. Bombastic");
        manager.add(term);
        assertThat(PATH_TO_FILE_REPOSITORY,is(notNullValue()));
    }

    @Test
    public void the_term_description_can_hold_400_characters() {
        assertThat(aTermWithExactly400Characters().getDescription().length(),is(400));
    }

    @Test(expected = LongDescriptionException.class)
    public void the_term_description_cant_hold_more_than_400_characters() {
        manager.add(aTermWithMoreThan400Characters());
    }

    @Test(expected = TermAlreadyExistsException.class)
    public void there_will_be_an_exception_if_the_term_already_exist_in_the_storage() {
        manager.add(aTermWithExactly400Characters());
        manager.add(aTermWithExactly400Characters());
    }
}