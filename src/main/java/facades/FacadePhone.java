package facades;

import dtos.PhoneDTO;
import entities.Phone;
import errorhandling.PhoneNotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

  public class FacadePhone {
    private static FacadePhone instance;
    private static EntityManagerFactory emf;
    public FacadePhone() {
    }
    public static FacadePhone getFacadePhone(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance= new FacadePhone();
        }
        return instance;
    }
    private EntityManager getEntityManager() throws PhoneNotFoundException
    {
        return emf.createEntityManager();
    }
    public PhoneDTO addPhone(String number,String infornation) throws PhoneNotFoundException{
        Phone phone = new Phone(number,infornation);
            EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(phone);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new PhoneDTO(phone);
    }
    public PhoneDTO getPhoneByid(long id)throws PhoneNotFoundException
    {
        EntityManager em= emf.createEntityManager();
        return new PhoneDTO(em.find(Phone.class,id));
    }
    public long getphoneCount() throws PhoneNotFoundException{
        EntityManager em = emf.createEntityManager();
        try {
            long phonCount = (long) em.createQuery("select count (p) from Phone p").getSingleResult();
            return phonCount;
        } finally {
            emf.close();
        }
    }
        public List<PhoneDTO> getAllPhone() throws PhoneNotFoundException
        {
         EntityManager em = emf.createEntityManager();
            TypedQuery<Phone> query = (TypedQuery<Phone>) em.createQuery("select p from Phone p",Phone.class);
            List<Phone> phoneList = query.getResultList();
            return PhoneDTO.getPhoneDTO(phoneList);
        }
    }














