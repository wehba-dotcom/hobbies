package facades;

import dtos.AddressDTO;
import dtos.CityInfoDTO;
import dtos.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import errorhandling.CityInfoNotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
  public class FacadeCityInfo {
      private static FacadeCityInfo instance;
      private static EntityManagerFactory emf;
      public FacadeCityInfo() {
      }
      public static FacadeCityInfo getFacadeCityinfo(EntityManagerFactory _emf) {
          if (instance == null) {
              emf = _emf;
              instance = new FacadeCityInfo();
          }
          return instance;
      }
      public CityInfoDTO createCityInfo(int zipcode, String city) throws CityInfoNotFoundException{
          CityInfo cityInfo = new CityInfo(zipcode, city);
          EntityManager em = emf.createEntityManager();
          try {
              em.getTransaction().begin();
              em.persist(cityInfo);
              em.getTransaction().commit();
          } finally {
              em.close();
          }
          return new CityInfoDTO(cityInfo);
      }
      public List<CityInfoDTO> getAll() throws CityInfoNotFoundException{
          EntityManager em = emf.createEntityManager();
          TypedQuery<CityInfo> query = em.createQuery("SELECT r FROM CityInfo r", CityInfo.class);
          List<CityInfo> rms = query.getResultList();
          return CityInfoDTO.getCityInfoDtos(rms);
      }
      public List<AddressDTO> getAllAddressByCityName(String city) throws CityInfoNotFoundException {
          EntityManager em = emf.createEntityManager();
          TypedQuery<Address> query = em.createQuery("select p from Address p " +
           "inner join p.cityInfo h where h.city = :name", Address.class);
          query.setParameter("name", city);
          List<Address> addressList = query.getResultList();
          return AddressDTO.getDtos(addressList);
      }
  }