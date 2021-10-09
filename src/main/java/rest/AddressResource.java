package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.HoppyDTO;
import dtos.PersonDTO;
import entities.Address;
import errorhandling.AddressNotFoundException;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import facades.FacadeAddress;
import facades.FacadePerson;
import facades.FacadePhone;
import utils.EMF_Creator;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/address")
  public class AddressResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadeAddress FACADE = FacadeAddress.getFacadeAddress(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllAddress()throws AddressNotFoundException
    {
        List<AddressDTO> addressDTOList = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(addressDTOList)).build();
    }
    @Path("add")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addAddress(String b) throws AddressNotFoundException
    {
        AddressDTO addressDTO = GSON.fromJson(b,AddressDTO.class);
        AddressDTO result= FACADE.create(addressDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
    @GET
    @Path("{street}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonesByName(@PathParam("street")String  street, String a) throws AddressNotFoundException, MissingInputException
    {
        AddressDTO addressDTO = GSON.fromJson(a, AddressDTO.class);
        if(addressDTO!=null) {
        System.out.println("PersonDTO:" + addressDTO.toString());
        }
        List<PersonDTO> result = FACADE.getAllPersonesByCityName(street);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
  }