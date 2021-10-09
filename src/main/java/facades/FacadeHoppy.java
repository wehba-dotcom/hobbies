package facades;

import dtos.HoppyDTO;
import dtos.PersonDTO;
import entities.Hoppy;
import entities.Person;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

  public class FacadeHoppy {
    public static FacadeHoppy instance;
    public static EntityManagerFactory emf;
    private FacadeHoppy() {
    }
    public static FacadeHoppy getFacadeHoppy(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeHoppy();
        }
        return instance;
    }
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public HoppyDTO createHoppy(String name,String description) throws MissingInputException, HoppyNotFoundException {
        Hoppy hoppy = new Hoppy(name, description);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hoppy);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HoppyDTO(hoppy);
    }
    public HoppyDTO getHoppyById(Long id) throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        return new HoppyDTO(em.find(Hoppy.class, id));
    }
    public HoppyDTO getById(long id) {
        EntityManager em = emf.createEntityManager();
        return new HoppyDTO(em.find(Hoppy.class, id));
    }
    public long getHoppyCount() throws  HoppyNotFoundException{
        EntityManager em = emf.createEntityManager();
        try {
            long hoppyCount = (long) em.createQuery("SELECT COUNT(r) FROM Hoppy r").getSingleResult();
            return hoppyCount;
        } finally {
            em.close();
        }
    }
    public HoppyDTO removeHoppy(long id) throws MissingInputException, HoppyNotFoundException{
        EntityManager em = emf.createEntityManager();
        Hoppy hoppy = em.find(Hoppy.class, id);
            try {
                em.getTransaction().begin();
                em.remove(hoppy);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        return new HoppyDTO(hoppy);
    }
    public HoppyDTO updateHoppy(HoppyDTO hoppyDTO) throws HoppyNotFoundException {
        Hoppy hoppy = new Hoppy(hoppyDTO.getId(), hoppyDTO.getName(), hoppyDTO.getDescription());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            hoppy = em.merge(hoppy);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HoppyDTO(hoppy);
    }
    public List<HoppyDTO> getAll() throws HoppyNotFoundException{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Hoppy> query = em.createQuery("SELECT hoppy FROM Hoppy hoppy", Hoppy.class);
        List<Hoppy> hoppyList = query.getResultList();
        return HoppyDTO.getHoppyDtos(hoppyList);
    }
    public List<PersonDTO> getPersonesByHoppyName(String name) throws HoppyNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("select p from Person p " + "inner join p.hoppyList h where h.name= :name", Person.class);
        query.setParameter("name", name);
        List<Person> personList = query.getResultList();
        return PersonDTO.getDtos(personList);
    }
        }


