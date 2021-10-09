package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddressDTO;
import dtos.PhoneDTO;
import errorhandling.MissingInputException;
import errorhandling.PhoneNotFoundException;
import facades.FacadeAddress;
import facades.FacadePhone;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/phone")
public class PhoneResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadePhone FACADE = FacadePhone.getFacadePhone(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Produces("text/plain")
    public String hello()throws PhoneNotFoundException {
        return "Hello, World!";
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPhones() throws PhoneNotFoundException, MissingInputException
    {
        List<PhoneDTO> phoneDTOList = FACADE.getAllPhone();
        return Response.ok().entity(GSON.toJson(phoneDTOList)).build();
    }
    @Path("add")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addPhone(String b) throws PhoneNotFoundException,MissingInputException
    {
        PhoneDTO phoneDTO = GSON.fromJson(b,PhoneDTO.class);
        PhoneDTO result= FACADE.addPhone(phoneDTO.getNumber(),phoneDTO.getInformation());
        return Response.ok().entity(GSON.toJson(result)).build();
    }
}