package it.univaq.gymportal.resources.rest;

import it.univaq.gymportal.business.FeedbackCourseService;
import it.univaq.gymportal.business.FeedbackGymService;
import it.univaq.gymportal.business.UserService;
import it.univaq.gymportal.business.impl.FeedbackCourseServiceImpl;
import it.univaq.gymportal.business.impl.FeedbackGymServiceImpl;
import it.univaq.gymportal.business.impl.UserServiceImpl;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.User;
import it.univaq.gymportal.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("users")
public class UserRes {

    @GET
    @Path("{idUser: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUser(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException{
    public Response getUser(@PathParam("idUser") long idUser) throws ServiceException{
        UserService userService = new UserServiceImpl();

//        String username = securityContext.getUserPrincipal().getName();
//        User user = userService.getUserByUsername(username);
//
//        if (idUser == user.getId()){
//            return Response.ok(user).build();
//        }
//        return Response.status(Response.Status.FORBIDDEN).build();

        User user = userService.getUserByIdWithoutPassword(idUser);
        return Response.ok(user).build();
    }

    @DELETE
    @Auth
    @Path("{idUser: [0-9]+}")
    public Response deleteUser(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException {
        UserService userService = new UserServiceImpl();

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            userService.deleteUser(idUser);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Auth
    @Path("{idUser: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@Context SecurityContext securityContext, @PathParam("idUser") long idUser, User user) throws ServiceException {
        UserService userService = new UserServiceImpl();

        String username = securityContext.getUserPrincipal().getName();

        if (idUser == userService.getUserByUsername(username).getId()){
            user.setId(idUser);
            if (user.getPassword().isEmpty() || user.getPassword().equals("")) {
                user.setPassword(userService.getUserById(idUser).getPassword());
            }
            userService.updateUser(user);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Auth
    @Path("{idUser: [0-9]+}/feedbacks/gyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksGym(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException {
        UserService userService = new UserServiceImpl();
        FeedbackGymService feedbackGymService = new FeedbackGymServiceImpl();

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            return Response.ok(feedbackGymService.getAllFeedbackByUser(idUser)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Auth
    @Path("{idUser: [0-9]+}/feedbacks/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedbacksCourse(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) throws ServiceException {
        UserService userService = new UserServiceImpl();
        FeedbackCourseService feedbackCourseService = new FeedbackCourseServiceImpl();

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);

        if (idUser == user.getId()){
            return Response.ok(feedbackCourseService.getAllFeedbackByUser(idUser)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    @Path("{idUser: [0-9]+}/favorites")
    public FavoriteRes getFavorites(@Context SecurityContext securityContext, @PathParam("idUser") long idUser) {
        System.out.println("From UserRes to FavoriteRes");
        return new FavoriteRes(idUser);
    }

}
