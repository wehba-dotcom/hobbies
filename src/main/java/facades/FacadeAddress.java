package facades;

import dtos.AddressDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.Person;
import errorhandling.AddressNotFoundException;
import errorhandling.MissingInputException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

 public class FacadeAddress {
    private static FacadeAddress instance;
    private static EntityManagerFactory emf;
     public FacadeAddress() {
    }
    public static FacadeAddress getFacadeAddress(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeAddress();
        }
        return instance;
    }
    public AddressDTO getAddressById(long id)throws AddressNotFoundException {
        EntityManager em = emf.createEntityManager();
        return new AddressDTO(em.find(Address.class, id));
    }
    private EntityManager getEntityManager()throws AddressNotFoundException {
        return emf.createEntityManager();
    }
    public AddressDTO create(AddressDTO addressDTO) throws AddressNotFoundException{
        Address address = new Address(addressDTO.getStreet(), addressDTO.getHoseNumber());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AddressDTO(address);
    }
    public List<AddressDTO> getAll() throws AddressNotFoundException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Address> query = em.createQuery("SELECT r FROM Address r", Address.class);
        List<Address> rms = query.getResultList();
        return AddressDTO.getDtos(rms);
    }
    public long getAddressCount() throws AddressNotFoundException{
        EntityManager em = emf.createEntityManager();
        try {
            long AddressCount = (long) em.createQuery("SELECT COUNT(r) FROM Address r").getSingleResult();
            return AddressCount;
        } finally {
            em.close();
        }
    }
        public List<PersonDTO> getAllPersonesByCityName(String street) throws AddressNotFoundException
        {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Person> query = em.createQuery("select p from Person p " +
            "inner join p.address h where h.street= :name", Person.class);
            query.setParameter("name", street);
            List<Person> personList = query.getResultList();
            return PersonDTO.getDtos(personList);
        }
    }

