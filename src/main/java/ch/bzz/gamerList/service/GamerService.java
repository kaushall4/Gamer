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

/**
        * provides services for the gamer
        * <p>

         *
         * @author Kaushall Vimalarajah
        */
@Path("gamer")
public class GamerService extends Application {

    /**
     * produces a list of all gamers
     *
     * @return Response
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listGamer() {
        Map<String,Gamer> gamerList = DataHandler.getGamerMap();
        Response response = Response
                .status(200)
                .entity(gamerList)
                .build();
        return response;

    }

    /**
     * reads a single Gamer identified by the gamerID
     *
     * @param uuid the gamerUUID in the URL
     * @return Response
     */
    @Path("read")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGamer(@QueryParam("uuid") String uuid) {
        int httpStatus = 200;
        Gamer gamer = null;
        try {
            UUID.fromString(uuid);
            gamer = new User().getGamer(uuid);
            if (gamer == null) {
                httpStatus = 404;
            }
        }catch (IllegalArgumentException ie) {
            httpStatus = 400;
        }

        Response response = Response
                .status(httpStatus)
                .entity(gamer)
                .build();
        return response;
    }

    /**
     * creates a new book
     * @param gamer a valid Gamer-Object
     * @param spielUUID the unique key of the publisher

     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createGamer(

            @Valid @BeanParam Gamer gamer,
         //   @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @FormParam("spielUUID") String spielUUID
    ) {
        int httpStatus = 200;

        gamer.setGamerUUID(UUID.randomUUID().toString());


        DataHandler.insertGamer(gamer,spielUUID);


        Response response = Response
                .status(httpStatus)
                .entity("")
                .build();
        return response;
    }


    /**
     * updates an existing Gamer
     * @param gamerUUID valid uuid identifying the gamer
     * @param gamer a valid gamer-Object
     * @param spielUUID the unique key of the spiel
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGamer(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @FormParam("gamerUUID") String gamerUUID,
          //  @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @Valid @BeanParam Gamer gamer,
            @FormParam("spielUUID") String spielUUID
    ) {
        int httpStatus = 200;

        try {
            UUID.fromString(gamerUUID);
            gamer = DataHandler.readGamer(gamerUUID);
            if (gamer.getNachname() != null) {
                httpStatus = 200;

                DataHandler.updateGamer();
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
     * deletes an existing book identified by its uuid
     * @param gamerUUID  the unique key for the book
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGamer(
         //   @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-{3}[0-9a-fA-F]{12})")
            @QueryParam("uuid") String gamerUUID

    ) {
        int httpStatus;
        try {
            UUID.fromString(gamerUUID);

            if (DataHandler.deleteGamer(gamerUUID)) {
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