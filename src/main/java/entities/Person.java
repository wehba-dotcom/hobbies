package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


          @Entity
          @NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
           public class Person implements Serializable {
            private static final long serialVersionUID = 1L;
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String email;
            private String firstName;
            private String lastName;

            @ManyToMany
            private List<Hoppy> hoppyList;
            @OneToMany(mappedBy = "person",cascade = CascadeType.PERSIST)
            private List<Phone> phoneList;

            @ManyToOne
            private Address address;
            public List<Phone> getPhoneList() {
                    return phoneList;
                }
            public Address getAddress() {
                return address;
            }
            public void setAddress(Address address) {
                this.address = address;
            }

              public Person() {
              }

              public void addPhone(Phone phone) {
            this.phoneList.add(phone);
            if (phone!=null)
            {
                phone.setPerson(this);
            }
            }

            public List<Hoppy> getHoppyList() {
            return hoppyList;
        }

            public void setHoppyList(List<Hoppy> hoppyList) {
                this.hoppyList = hoppyList;
            }

            public Person(long id, String email, String firstName, String lastName) {
            }

              public Person(String firstName) {
                  this.firstName = firstName;
                  this.hoppyList= new ArrayList<>();
                this.phoneList= new ArrayList<>();
              }

              public Person(String email, String firstName, String lastName) {
                this.email = email;
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public Long getId() {
                return id;
            }
            public void setId(Long id) {
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
            public void setLastName(String lastName) {
            this.lastName = lastName;
        }

            @Override
            public String toString() {
                return "Person{" +
                        "id=" + id +
                        ", email='" + email + '\'' +
                        ", firstName='" + firstName + '\'' +
                        ", lastName='" + lastName + '\'' +
                        '}';
            }
            }
