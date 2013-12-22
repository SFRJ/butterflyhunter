package specs;

import com.djordje.apps.dataaccess.PersistencyManager;
import com.djordje.apps.errorhandling.LongDescriptionException;
import com.djordje.apps.errorhandling.TermAlreadyExistsException;
import com.djordje.apps.model.Term;
import com.djordje.apps.utils.termmanagement.TermManager;
import com.djordje.apps.utils.termmanagement.TermManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specs.support.Cleanup;

import java.io.File;

import static com.djordje.apps.dataaccess.PersistencyManager.PATH_TO_FILE_REPOSITORY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aTermWithExactly400Characters;
import static specs.support.TermStubs.aTermWithMoreThan400Characters;

public class AddingANewTermSpecification extends Cleanup {

    private TermManager manager = new TermManagerImpl();

    @Test
    public void a_new_term_is_marshalled() {
        Term term = new Term("AAA","AAA");
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