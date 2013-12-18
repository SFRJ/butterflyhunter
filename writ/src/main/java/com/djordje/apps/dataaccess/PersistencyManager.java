package com.djordje.apps.dataaccess;

import com.djordje.apps.model.Term;

import java.util.ArrayList;
import java.util.List;

public interface PersistencyManager {

    public Term get(String key);
    public boolean add(Term term);
    public List<Term> getAll();
    public List<Term> getAllContaining(String partialWord);
    public boolean update(Term term);
}
