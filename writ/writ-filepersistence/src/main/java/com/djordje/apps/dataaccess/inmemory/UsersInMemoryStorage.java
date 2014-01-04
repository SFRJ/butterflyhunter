package main.java.com.djordje.apps.dataaccess.inmemory;


import main.java.com.djordje.apps.core.KnowledgeProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UsersInMemoryStorage implements UsersPersistenceManager{


    @Override
    public boolean add(KnowledgeProvider knowledgeProvider) {
        try {
            marshall(knowledgeProvider);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public KnowledgeProvider getKnowledgeProvider(String nickname) {
        KnowledgeProvider output = null;
        File folder = PATH_TO_FILE_REPOSITORY;
        if(folder.exists())
            for(File current : folder.listFiles()) {
                if(current.isFile()){
                    output = (KnowledgeProvider) unmarshall(current);
                    if(output.getNickname().equalsIgnoreCase(nickname))
                        return output;
                }
                continue;
            }
        return null;

    }

    private Object unmarshall(File xmlFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(KnowledgeProvider.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteKnowledgeProvider(String nickname) {
        for(File current : PATH_TO_FILE_REPOSITORY.listFiles()) {
            if(current.isFile()){
                KnowledgeProvider knowledgeProvider = (KnowledgeProvider) unmarshall(current);
                if(knowledgeProvider.getNickname().equalsIgnoreCase(nickname))
                    current.delete();
                break;
            }
        }
    }

    @Override
    public void updateVotes(KnowledgeProvider knowledgeProvider, String termName) {
        KnowledgeProvider provider = getKnowledgeProvider(knowledgeProvider.getNickname());
        provider.getVotedTerms().add(termName);
        deleteKnowledgeProvider(knowledgeProvider.getNickname());
        add(knowledgeProvider);
    }

    private void marshall(KnowledgeProvider knowledgeProvider) throws JAXBException, IOException {
        knowledgeProvider.setCreationTime(System.currentTimeMillis());
        File file = new File(pathToMarshalledFile(knowledgeProvider));
        JAXBContext context = JAXBContext.newInstance(KnowledgeProvider.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(knowledgeProvider,new FileOutputStream(file));
    }

    private String pathToMarshalledFile(KnowledgeProvider knowledgeProvider) {
        if(knowledgeProvider.getCreationTime() > 0L) {
            return PATH_TO_FILE_REPOSITORY.getPath() + "/" + knowledgeProvider.getNickname() + knowledgeProvider.getCreationTime() + ".xml";
        }
        return null;
    }
}
