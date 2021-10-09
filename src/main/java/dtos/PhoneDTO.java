package dtos;

import entities.Phone;

import java.util.ArrayList;
import java.util.List;

  public class PhoneDTO {
    private Long id;
    private String number;
    private String information;
    public static List<PhoneDTO> getPhoneDTO(List<Phone> phoneList)
    {
        List<PhoneDTO> phoneDTOList= new ArrayList<>();
        phoneList.forEach(phone -> phoneDTOList.add(new PhoneDTO(phone)));
        return phoneDTOList;
    }
    public PhoneDTO(Long id, String number, String information) {
        this.id = id;
        this.number = number;
        this.information = information;
    }

    public PhoneDTO(String number, String information) {
        this.number = number;
        this.information = information;
    }
    public PhoneDTO(Phone phone) {
        if (phone.getId() != null) {
            this.id = phone.getId();
            this.number = phone.getNumber();
            this.information = phone.getInformation();
        }
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
 }
