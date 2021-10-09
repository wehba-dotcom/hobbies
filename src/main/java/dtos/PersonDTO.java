 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

 /**
 *
 * @author tha
 */
  public class PersonDTO {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    public PersonDTO(String email, String firstName, String lasttName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public PersonDTO(long id, String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public static List<PersonDTO> getDtos(List<Person> personList){
        List<PersonDTO> personDTOList = new ArrayList();
        personList.forEach(person->personDTOList.add(new PersonDTO(person)));
        return personDTOList;
    }
    public PersonDTO(Person rm) {
        if(rm.getId() != null)
            this.id = rm.getId();
        this.email= rm.getEmail();
        this.firstName = rm.getFirstName();
        this.lastName = rm.getLastName();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lasttName) {
        this.lastName = lasttName;
    }
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof PersonDTO)) return false;
         PersonDTO personDTO = (PersonDTO) o;
         return getId() == personDTO.getId() && Objects.equals(getEmail(), personDTO.getEmail()) && Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects.equals(getLastName(), personDTO.getLastName());
     }
     @Override
     public int hashCode() {
         return Objects.hash(getId(), getEmail(), getFirstName(), getLastName());
     }
   }
