package facades;

import dtos.HoppyDTO;
import dtos.PersonDTO;
import dtos.PhoneDTO;
import entities.Hoppy;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import entities.Phone;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
  public class FacadePerson {
    private static FacadePerson instance;
    private static EntityManagerFactory emf;
    private FacadePerson() {}
    public static FacadePerson getFacadePerson(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadePerson();
        }
        return instance;
    }
    private EntityManager getEntityManager()throws PersonNotFoundException {
        return emf.createEntityManager();
    }
    public PersonDTO createPerson(String email,String firstName,String lastName)throws PersonNotFoundException{
        Person person = new Person(email,firstName,lastName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }
    public PersonDTO getPersonById(int id)throws PersonNotFoundException{
        EntityManager em = emf.createEntityManager();
        return new PersonDTO(em.find(Person.class, id));
    }
    public long getPersonCount()throws PersonNotFoundException{
        EntityManager em = emf.createEntityManager();
        try{
         long PersonCount = (long)em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return PersonCount;
        }finally{  
            em.close();
        }
    }
      public PersonDTO editPerson(long id,String email,String firstName,String lastName) throws PersonNotFoundException {
          Person person = new Person(id,email,firstName,lastName);
          EntityManager em = emf.createEntityManager();
          try {
              em.getTransaction().begin();
             person = em.merge(person);
              em.getTransaction().commit();
          } finally {
              em.close();
          }
          return new PersonDTO(person);
      }
    public List<PersonDTO> getAll()throws PersonNotFoundException,MissingInputException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT r FROM Person r", Person.class);
        List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }
    public List<PhoneDTO> getPhonesByPersonName(String name) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Phone> query =
        em.createQuery("select p from Phone p " +
        "inner join p.person h where h.firstName= :name", Phone.class);
        query.setParameter("name", name);
        List<Phone> phoneList = query.getResultList();
        return PhoneDTO.getPhoneDTO(phoneList);
    }
    }


