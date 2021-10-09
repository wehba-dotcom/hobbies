/*
package facades;

import dtos.PersonDTO;
import entities.Address;
import entities.Hoppy;
import utils.EMF_Creator;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;


public class FacadePersonTest {

    private static EntityManagerFactory emf;
    private static FacadePerson facade;
    private static Person p1;
    private static Person p2;
    private static Person p3;
    private static Hoppy h1;
    private static Hoppy h2;
    private static Hoppy h3;
    public FacadePersonTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadePerson.getFacadePerson(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1= new Person("we@rt","wehba","korouni");
        p1= new Person("we@km","reham","korouni");
        p1= new Person("we@yu","mare","korouni");


        h1=new Hoppy("swimm","trainnig");
        h2=new Hoppy("boxing","maseles");
        h3=new Hoppy("karatai","body");


        h1.addPerson(p1);
        h1.addPerson(p2);
        h3.addPerson(p3);

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
             em.persist(h1);
             em.persist(h2);
             em.persist(h3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(2, facade.getPersonCount(), "Expects two rows in the database");
    }

    @Test
    public void addPerson() throws Exception {
        PersonDTO expected = new PersonDTO(p1);
        PersonDTO actual = facade.createPerson("wewe","wehba","korouni");
        //assertEquals(expected, actual);
        assertTrue(Objects.equals(expected.getFirstName(), actual.getFirstName()) &&
                Objects.equals(expected.getLastName(), actual.getLastName()));
    }
}
*/
