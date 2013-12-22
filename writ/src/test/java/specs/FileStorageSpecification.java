package specs;

import com.djordje.apps.dataaccess.inmemory.TermsInMemoryStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import specs.support.Cleanup;

import java.io.File;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static specs.support.TermStubs.aCustomTermWith;

public class FileStorageSpecification extends Cleanup {

    @Test
    public void should_return_terms_from_storage_caseinsensitive() {
        TermsInMemoryStorage storage = new TermsInMemoryStorage();
        storage.add(aCustomTermWith("MPF", "Metallic Path Facility..."));
        storage.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility..."));
        assertThat(storage.getAllContaining("m").size(),is(2));
        assertThat(storage.getAllContaining("M").size(),is(2));
    }
}
