package ch.bzz.gamerList.service;

import ch.bzz.gamerList.data.UserData;
import ch.bzz.gamerList.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * provides services for the user
 * <p>
 * M133: Bookshelf
 *
 * @author Kaushall Vimalarajah
 */
@Path("user")
public class UserService {

    /**
     * login a user with username/password
     *
     * @param username the username
     * @param password the password
     * @return Response-object with the userrole
     */
    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus;

        User user = UserData.findUser(username, password);
        if (user.getRole().equals("guest")) {
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "", //z.B. ghwalin.ch
                "Login-Cookie",
                600,
                false
        );


        Response response = Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
                .build();
        return response;
    }

    /**
     * logout current user
     *
     * @return Response object with guest-Cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout() {

        Response response = Response
                .status(200)
                .entity("")
                .build();
        return response;
    }
}