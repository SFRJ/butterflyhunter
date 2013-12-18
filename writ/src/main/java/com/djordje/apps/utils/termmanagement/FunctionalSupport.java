package com.djordje.apps.utils.termmanagement;

import com.djordje.apps.model.Term;
import com.googlecode.totallylazy.Callable1;
import com.googlecode.totallylazy.Predicate;

public class FunctionalSupport {
    public FunctionalSupport() {
    }

    protected static Predicate<Term> isNotNull() {
        return new Predicate<Term>() {
            @Override
            public boolean matches(Term term) {
                return term != null;
            }
        };
    }

    protected static Callable1<Term, Term> termsStartingWith(final String prefix) {
        return new Callable1<Term, Term>() {
            @Override
            public Term call(Term term) throws Exception {
                return term.getName().toLowerCase().startsWith(prefix.toLowerCase()) ? term : null;
            }
        };
    }

    protected static Callable1<Term, Term> termsContaining(final String partialString) {
        return new Callable1<Term, Term>() {
            @Override
            public Term call(Term term) throws Exception {
                return term.getName().toLowerCase().contains(partialString.toLowerCase()) ? term:null;
            }
        };
    }
}