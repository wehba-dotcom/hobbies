package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.CityInfoDTO;
import dtos.PersonDTO;
import errorhandling.CityInfoNotFoundException;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import facades.FacadeAddress;
import facades.FacadeCityInfo;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cityinfo")
public class CityInfoResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadeCityInfo FACADE = FacadeCityInfo.getFacadeCityinfo(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCityInfo()throws CityInfoNotFoundException
    {
        List<CityInfoDTO> cityInfoDTOList = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(cityInfoDTOList)).build();
    }
    @Path("add")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addCityInfo(String b)throws CityInfoNotFoundException
    {
        CityInfoDTO cityInfoDTO = GSON.fromJson(b,CityInfoDTO.class);
        CityInfoDTO result= FACADE.createCityInfo(cityInfoDTO.getZipcode(),cityInfoDTO.getCity());
        return Response.ok().entity(GSON.toJson(result)).build();
    }
    @GET
    @Path("{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressByCityName(@PathParam("city")String  city, String a) throws CityInfoNotFoundException, MissingInputException
    {
        AddressDTO addressDTO = GSON.fromJson(a, AddressDTO.class);
        if(addressDTO!=null) {
            System.out.println("AddressDTO:" + addressDTO.toString());
        }
        List<AddressDTO> result = FACADE.getAllAddressByCityName(city);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
}