package dtos;

import entities.CityInfo;

import java.util.ArrayList;
import java.util.List;

 public class CityInfoDTO {
    private long id;
    private int zipcode;
    private String city;
    public CityInfoDTO(long id, int zipcode, String city) {
        this.id = id;
        this.zipcode = zipcode;
        this.city = city;
    }
    public CityInfoDTO(CityInfo cityInfo) {
        if (cityInfo.getId()!=null)
        {
            this.id=cityInfo.getId();
            this.zipcode= cityInfo.getZipcode();
            this.city=cityInfo.getCity();
        }
    }
    public static List<CityInfoDTO> getCityInfoDtos(List<CityInfo> cityInfoList)
    {
        List<CityInfoDTO> cityInfoDTOList= new ArrayList<>();
        cityInfoList.forEach(cityInfo -> cityInfoDTOList.add(new CityInfoDTO(cityInfo)));
        return cityInfoDTOList;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
   }
