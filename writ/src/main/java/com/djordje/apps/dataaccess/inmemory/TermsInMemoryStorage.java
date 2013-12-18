package com.djordje.apps.dataaccess.inmemory;

import com.djordje.apps.dataaccess.PersistencyManager;
import com.djordje.apps.model.Term;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class TermsInMemoryStorage implements PersistencyManager {

    public static final File PATH_TO_FILE_REPOSITORY = new File("/home/pro/Desktop/glassfish4/temporatyxmlstorage/");

    public Term get(String key) {
        Term output = null;
        File folder = PATH_TO_FILE_REPOSITORY;
        for(File current : folder.listFiles()) {
            if(current.isFile()){
                output = (Term) unmarshall(current);
               if(output.getName().equalsIgnoreCase(key))
                    return output;
            }
            continue;
        }
        return null;
    }

    public boolean add(Term term) {
        try {
            marshall(term);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Term> getAll() {
        List<Term> allTerms = new ArrayList<Term>();
        for(File current : PATH_TO_FILE_REPOSITORY.listFiles()) {
            if(current.isFile()){
               allTerms.add ((Term) unmarshall(current));
            }
        }
        return  allTerms;
    }

    @Override
    public List<Term> getAllContaining(String partialWord) {
        List<Term> allTerms = new ArrayList<Term>();
        for(File current : PATH_TO_FILE_REPOSITORY.listFiles()) {
            if(current.isFile()){
                Term term = (Term) unmarshall(current);
                if(term.getName().toLowerCase().contains(partialWord)
                        ||term.getName().toUpperCase().contains(partialWord))
                allTerms.add (term);
            }
        }
        return  allTerms;
    }

    @Override
    public boolean update(Term term) {
    //TODO: To update you need to retrieve modify and store again
        return false;  //To change body of implemented methods use File | Settings | File Templates.

    }

    private Object unmarshall(File xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Term.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void marshall(Term term) throws JAXBException, IOException {
        term.setCreationTime(System.currentTimeMillis());
        File file = new File(term.getCreationPath());
        JAXBContext context = JAXBContext.newInstance(Term.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(term,new FileOutputStream(file));
    }


}
