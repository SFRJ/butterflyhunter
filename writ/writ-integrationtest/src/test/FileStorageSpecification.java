package test;

import main.java.com.djordje.apps.dataaccess.inmemory.TermsInMemoryStorage;
import org.junit.Test;
import test.support.Cleanup;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static test.support.TermStubs.aCustomTermWith;

public class FileStorageSpecification extends Cleanup {

    @Test
    public void should_return_terms_from_storage_caseinsensitive() {
        TermsInMemoryStorage storage = new TermsInMemoryStorage();
        storage.add(aCustomTermWith("MPF", "Metallic Path Facility...","Mr. Bombastic"));
        storage.add(aCustomTermWith("SMPF", "Shared Metallic Path Facility...", "Mr. Bombastic"));
        assertThat(storage.getAllContaining("m").size(),is(2));
        assertThat(storage.getAllContaining("M").size(),is(2));
    }
}
