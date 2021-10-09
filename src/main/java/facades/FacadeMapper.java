package facades;

import dtos.PersonDTO;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

  public class FacadeMapper {
    private static FacadeMapper instance;
    private static EntityManagerFactory emf;
    private FacadeHoppy facadeHoppy;
    private FacadePerson facadePerson;
    public FacadeMapper() {
    }
    public static FacadeMapper getFacadeMapper(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeMapper();
        }
        return instance;
    }
    public List<PersonDTO> getAllPersonesDTO() throws PersonNotFoundException, MissingInputException {
        List<PersonDTO> personList = facadePerson.getAll();

        return personList;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public long getPersoneCount()throws PersonNotFoundException,MissingInputException {
        long getcounts = facadePerson.getPersonCount();
        return getcounts;
    }
}
