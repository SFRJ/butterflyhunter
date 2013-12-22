package com.djordje.apps.dataaccess.inmemory;

import com.djordje.apps.model.Term;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface TermsPersistencyManager {

    public static final File PATH_TO_FILE_REPOSITORY = new File("/home/pro/Desktop/glassfish4/temporaryxmlstorage/terms/");

    public Term get(String key);
    public boolean add(Term term);
    public List<Term> getAll();
    public List<Term> getAllContaining(String partialWord);
    public void update(Term term);
    public void delete(String key);
}
