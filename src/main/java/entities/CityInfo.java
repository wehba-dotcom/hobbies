package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


    @Entity
    @NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo")
    public class CityInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int zipcode;
    private String city;
    @OneToMany(mappedBy = "cityInfo",cascade = CascadeType.PERSIST)
    private List<Address> addressList;
    public List<Address> getAddressList() {
        return addressList;
    }
    public void addAddress(Address address) {
        this.addressList.add(address);
        if(address!=null)
        {
            address.setCityInfo(this);
        }
    }

        public CityInfo(String city) {
            this.city = city;

        }

        public CityInfo() {
    }
    public CityInfo(int zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
            this.addressList=new ArrayList<>();
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "CityInfo{" +
                "id=" + id +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                '}';
    }
    }