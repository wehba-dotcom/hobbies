/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ExceptionDTO err;

    @Context
    ServletContext context;

    @Override
    public Response toResponse(Throwable ex) {
        Logger.getLogger(GenericExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        Response.StatusType type = getStatusType(ex);
        int statusCode = type.getStatusCode();

        if (ex instanceof PersonNotFoundException) {
            statusCode = ((PersonNotFoundException) ex).getStatusCode();
            err = new ExceptionDTO(statusCode, ex.getMessage());
        } else if (ex instanceof MissingInputException) {
            statusCode = ((MissingInputException) ex).getStatusCode();
            err = new ExceptionDTO(statusCode, ex.getMessage());
        } else if (ex instanceof RuntimeException) {
            err = new ExceptionDTO(statusCode, "Internal Server Problem. We are sorry for the inconvenience");
        } else if (ex instanceof WebApplicationException) {
            err = new ExceptionDTO(statusCode, ex.getMessage());
        } else {
            err = new ExceptionDTO(statusCode, type.getReasonPhrase());
        }

        return Response.status(statusCode)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }

    private Response.StatusType getStatusType(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return ((WebApplicationException) ex).getResponse().getStatusInfo();
        }
        return Response.Status.INTERNAL_SERVER_ERROR;
    }
}
