package ch.bzz.gamerList.service;


import ch.bzz.gamerList.data.DataHandler;
import ch.bzz.gamerList.model.Gamer;
import ch.bzz.gamerList.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;
import ch.bzz.gamerList.data.DataHandler;
import ch.bzz.gamerList.model.Gamer;
import ch.bzz.gamerList.model.Spiel;
import ch.bzz.gamerList.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

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
     * @param uuid the spielUUID in the URL
     * @return Response
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSpiel(@QueryParam("uuid") String uuid) {
        int httpStatus = 200;
        Spiel spiel = null;
        try {
            UUID.fromString(uuid);
            spiel = new User().getSpiel(uuid);
            if (spiel == null) {
                httpStatus = 404;
            }
        }catch (IllegalArgumentException ie) {
            httpStatus = 400;
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
     * @param gamerUUID the unique key of the gamer

     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createGamer(

            @Valid @BeanParam Spiel spiel,
         //   @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @FormParam("gamerUUID") String gamerUUID
    ) {
        int httpStatus = 200;

        spiel.setSpielUUID(UUID.randomUUID().toString());


        DataHandler.insertSpiel(spiel,gamerUUID);


        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


    /**
     * updates an existing Gamer
     * @param spielUUID valid uuid identifying the spiel
     * @param spiel a valid spiel-Object
     * @param gamerUUID the unique key of the gamer
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSpiel(
          //  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
          @FormParam("spielUUID") String spielUUID,
         //   @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @Valid @BeanParam Spiel spiel,
          @FormParam("gamerUUID") String gamerUUID
    ) {
        int httpStatus = 200;

        try {
            UUID.fromString(spielUUID);
            spiel = DataHandler.readSpiel(spielUUID);
            if (spiel.getSpiel() != null) {
                httpStatus = 200;

                DataHandler.updateSpiel();
            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
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
         //   @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @QueryParam("uuid") String spielUUID

    ) {
        int httpStatus;
        try {
            UUID.fromString(spielUUID);

            if (DataHandler.deleteSpiel(spielUUID)) {
                httpStatus = 200;

            } else {
                httpStatus = 404;
            }
        } catch (IllegalArgumentException argEx) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


}