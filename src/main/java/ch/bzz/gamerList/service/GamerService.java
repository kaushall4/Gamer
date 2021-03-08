package ch.bzz.gamerList.service;

import ch.bzz.gamerList.data.DataHandler;
import ch.bzz.gamerList.model.Gamer;
import ch.bzz.gamerList.model.Spiel;
import ch.bzz.gamerList.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
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
        Map<String,Gamer> gamerList = DataHandler.getGamerList();
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
    public Response readGame(@QueryParam("uuid") String uuid) {
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
}