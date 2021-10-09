package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.HoppyDTO;
import dtos.PersonDTO;
import entities.Hoppy;
import errorhandling.HoppyNotFoundException;
import errorhandling.MissingInputException;
import facades.FacadeHoppy;

import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("hoppy")
public class HoppyResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FacadeHoppy FACADE = FacadeHoppy.getFacadeHoppy(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllHobbies() throws MissingInputException,HoppyNotFoundException{
        List<HoppyDTO> reslt = FACADE.getAll();
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHoppy(String a) throws MissingInputException,HoppyNotFoundException{
        HoppyDTO hoppyDTO = GSON.fromJson(a, HoppyDTO.class);
        HoppyDTO reslt = FACADE.createHoppy(hoppyDTO.getName(),hoppyDTO.getDescription());
        return Response.ok().entity(GSON.toJson(reslt)).build();
    }
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editHoppy(@PathParam("id")long id, String a) throws HoppyNotFoundException,MissingInputException
    {
        HoppyDTO hoppyDTO = GSON.fromJson(a,HoppyDTO.class);
        hoppyDTO.setId(id);
        HoppyDTO result = FACADE.updateHoppy(hoppyDTO);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id")long id) throws MissingInputException,HoppyNotFoundException
    {
        HoppyDTO hoppyDTO = null;
        hoppyDTO=  FACADE.removeHoppy(id);
        return Response.ok().entity(GSON.toJson(hoppyDTO)).build();
    }
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonesByName(@PathParam("name")String  name, String a) throws HoppyNotFoundException,MissingInputException
    {
        HoppyDTO hopyyDTO = GSON.fromJson(a, HoppyDTO.class);
        if(hopyyDTO!=null) {
        System.out.println("HobbyDTO:" + hopyyDTO.toString());
        }
        List<PersonDTO> result = FACADE.getPersonesByHoppyName(name);
        return Response.ok().entity(GSON.toJson(result)).build();
    }
  }