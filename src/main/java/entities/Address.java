package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


    @Entity
    @NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address ")
    public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int hoseNumber;
    @ManyToOne
    private CityInfo cityInfo;
    @OneToMany(mappedBy = "address",cascade = CascadeType.PERSIST)
    private List<Person> personList;
    public List<Person> getPersonList() {
        return personList;
    }
    public Address(String street) {
        this.street = street;
    }
    public CityInfo getCityInfo() {
        return cityInfo;
    }
    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }
    public void addperson(Person person) {
        this.personList.add(person);
        if(person!=null)
        {
            person.setAddress(this);
        }
    }

    public Address(String street, int hoseNumber) {
        this.street = street;
        this.hoseNumber = hoseNumber;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
        this.personList= new ArrayList<>();
    }
    public int getHoseNumber() {
        return hoseNumber;
    }
    public void setHoseNumber(int hoseNumber) {
        this.hoseNumber = hoseNumber;
    }
    public Address() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", hoseNumber=" + hoseNumber +
                '}';
    }
    }