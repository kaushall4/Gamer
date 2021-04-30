package ch.bzz.gamerList.service;


import ch.bzz.gamerList.data.DataHandler;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

import ch.bzz.gamerList.model.Spiel;

/**
        * provides services for the Spiel
        * <p>

         *
         * @author Kaushall Vimalarajah
        */
@Path("spiel")
public class SpielService extends Application {

    /**
     * produces a list of all spiele
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listSpiel() {
        Map<String, Spiel> spielMap = DataHandler.getSpielMap();
        Response response = Response
                .status(200)
                .entity(spielMap)
                .build();
        return response;

    }

    /**
     * reads a single spiel identified by the spielID
     *
     * @param spielUUID the spielUUID in the URL
     * @return Response
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpiel(

            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String spielUUID
    ) {
        Spiel spiel = null;
        int httpStatus;

        spiel = DataHandler.readSpiel(spielUUID);
        if (spiel.getTitel() != null) {
            httpStatus = 200;
        } else {
            httpStatus = 404;
        }


        Response response = Response
                .status(httpStatus)
                .entity(spiel)
                .build();
        return response;
    }

    /**
     * creates a new spiel
     * @param spiel a valid Spiel-Object

     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createSpiel(

            @Valid @BeanParam Spiel spiel
    ) {
        int httpStatus = 200;
        spiel.setSpielUUID(UUID.randomUUID().toString());
        DataHandler.insertSpiel(spiel);

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


    /**
     * updates an existing Gamer
     * @param spiel a valid spiel-Object
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSpiel(
            @Valid @BeanParam Spiel spiel
    ) {
        int httpStatus = 200;

        if (DataHandler.updateSpiel(spiel)) {
            httpStatus = 200;
        } else {
            httpStatus = 404;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }

    /**
     * deletes an existing spiel identified by its uuid
     * @param spielUUID  the unique key for the spiel
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSpiel(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String spielUUID
    ) {
        int httpStatus;

        int errorcode = DataHandler.deleteSpiel(spielUUID);
        if (errorcode == 0) httpStatus = 200;
        else if (errorcode == -1) httpStatus = 409;
        else httpStatus = 404;

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


}