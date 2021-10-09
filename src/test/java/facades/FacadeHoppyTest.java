/*
package facades;

import dtos.HoppyDTO;
import entities.Hoppy;
import entities.Person;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.*;
class FacadeHoppyTest {
    public static FacadeHoppy facade;
    public static EntityManagerFactory emf;
    private static Hoppy h1;
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = FacadeHoppy.getFacadeHoppy(emf);
    }
    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        h1 = new Hoppy("boxing", "stronger body");
        h1.addPerson(new Person("we@hot", "wehba", "kor"));
        try {
            em.getTransaction().begin();
            em.persist(h1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    void createHoppy() throws Exception {
        String name = "FootBold";
        String desciption = "running so match";
        EntityManagerFactory _emf = null;
        FacadeHoppy instance = FacadeHoppy.getFacadeHoppy(emf);
        Hoppy h5 = new Hoppy(name,desciption);
        HoppyDTO expcted = instance.createHoppy(name, desciption);
        HoppyDTO acuuel = new HoppyDTO(h5);
        expcted.setId(acuuel.getId());
        assertEquals(expcted, acuuel);
    }

    @Test
    void getHoppyById() throws MissingInputException,HoppyNotFoundException {
        EntityManager em = emf.createEntityManager();
        long id = h1.getId();
        FacadeHoppy instance = FacadeHoppy.getFacadeHoppy(emf);
        HoppyDTO expcted = new HoppyDTO(h1);
        HoppyDTO acuel = instance.getHoppyById(id);
        assertEquals(expcted, acuel);
    }
    @Test
    void getHoppyCount() throws HoppyNotFoundException{
    EntityManagerFactory _emf=null;
    FacadeHoppy instance = FacadeHoppy.getFacadeHoppy(_emf);
    long expectedcount = 3L;
    long acual = instance.getHoppyCount();
    assertEquals(expectedcount,acual);
    }
    @Test
    void removeHoppy() throws HoppyNotFoundException, MissingInputException {
        EntityManagerFactory _emf= null;
        FacadeHoppy instance = FacadeHoppy.getFacadeHoppy(_emf);
        EntityManager em = emf.createEntityManager();
        long id = h1.getId();
        HoppyDTO actualhoppy= new HoppyDTO(h1);
        HoppyDTO expctedhoppy= FacadeHoppy.instance.removeHoppy(id);
        int HoppyAfterDeleted=  instance.getAll().size();
        assertEquals(5,HoppyAfterDeleted);
        assertEquals(actualhoppy,expctedhoppy);
      //  assertThrows(HoppyNotFoundException.class, () -> {instance.getHoppyById(id);});
    }
    @Test
    void updateHoppy() throws HoppyNotFoundException, MissingInputException {
        EntityManagerFactory _emf= null;
        FacadeHoppy instance = FacadeHoppy.getFacadeHoppy(_emf);
       long id = h1.getId();
        HoppyDTO hoppyDTO= new HoppyDTO(h1);
        HoppyDTO excepted= new HoppyDTO(h1);
        excepted.setName("fitness");
        hoppyDTO.setName("fitness");
        HoppyDTO result= instance.updateHoppy(hoppyDTO);
        result = instance.getHoppyById(id);
        assertEquals(result.getName(),excepted.getName());
    }
    @Test
    void getAll() throws MissingInputException, HoppyNotFoundException {
        EntityManagerFactory _emf = null;
        FacadeHoppy instance= FacadeHoppy.getFacadeHoppy(_emf);
        long actual = 1L;
        long expcted= instance.getAll().size();
        assertEquals(actual,expcted);
    }
}*/
