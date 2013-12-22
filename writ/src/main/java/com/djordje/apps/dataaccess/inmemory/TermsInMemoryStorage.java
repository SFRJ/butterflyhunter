package com.djordje.apps.dataaccess.inmemory;

import com.djordje.apps.model.Term;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class TermsInMemoryStorage implements TermsPersistencyManager {

    public Term get(String key) {
        Term output = null;
        File folder = PATH_TO_FILE_REPOSITORY;
        if(folder.exists())
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
    public void update(Term term) {
        Term termToUpdate = get(term.getName());
        delete(termToUpdate.getName());
        termToUpdate.setMinusVotes(term.getMinusVotes());
        termToUpdate.setPlusVotes(term.getPlusVotes());
        termToUpdate.setTotalVotes(term.getPlusVotes() - term.getMinusVotes());
        termToUpdate.setCreationTime(term.getCreationTime());
        add(termToUpdate);
    }

    public void delete(String key) {
        for(File current : PATH_TO_FILE_REPOSITORY.listFiles()) {
            if(current.isFile()){
                Term term = (Term) unmarshall(current);
                if(term.getName().equalsIgnoreCase(key))
                    current.delete();
                break;
            }
        }
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
        File file = new File(pathToMarshalledFile(term));
        JAXBContext context = JAXBContext.newInstance(Term.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(term,new FileOutputStream(file));
    }

    private String pathToMarshalledFile(Term term) {
        if(term.getCreationTime() > 0L) {
        return PATH_TO_FILE_REPOSITORY.getPath() + "/" + term.getName() + term.getCreationTime() + ".xml";
        }
        return null;
    }


}
