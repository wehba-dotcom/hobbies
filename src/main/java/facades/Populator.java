/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;
  public class Populator {
    public static void populate() throws Exception {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadePerson fe = FacadePerson.getFacadePerson(emf);
        /*fe.createPerson(new PersonDTO(new Person("email1","First 1", "Last 1")));
        fe.createPerson(new PersonDTO(new Person("email2","First 2", "Last 2")));
        fe.createPerson(new PersonDTO(new Person("email3","First 3", "Last 3")));
*/
    }
    public static void main(String[] args) throws Exception {
        populate();
    }
  }
