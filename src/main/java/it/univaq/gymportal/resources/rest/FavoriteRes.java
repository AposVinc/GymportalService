package it.univaq.gymportal.resources.rest;

import it.univaq.gymportal.business.FavoriteCourseService;
import it.univaq.gymportal.business.FavoriteGymService;
import it.univaq.gymportal.business.UserService;
import it.univaq.gymportal.business.impl.FavoriteCourseServiceImpl;
import it.univaq.gymportal.business.impl.FavoriteGymServiceImpl;
import it.univaq.gymportal.business.impl.UserServiceImpl;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.FavoriteCourse;
import it.univaq.gymportal.model.FavoriteGym;
import it.univaq.gymportal.model.User;
//import it.univaq.gymportal.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//@Auth
public class FavoriteRes {

    private final long user_id;

    public FavoriteRes(long user_id) {
        this.user_id = user_id;
    }

    @POST
    @Path("gyms")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idGym) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (user_id == user.getId()){
                FavoriteGym favoriteGym = new FavoriteGym();
                favoriteGym.setGymId(idGym);
                favoriteGym.setUser(user.getId());
                favoriteGymService.createFavoriteGym(favoriteGym);

                return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("courses")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFavoriteCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, long idCourse) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (user_id == user.getId()){
                FavoriteCourse favoriteCourse = new FavoriteCourse();
                favoriteCourse.setCourseId(idCourse);
                favoriteCourse.setUserId(user.getId());
                favoriteCourseService.createFavoriteCourse(favoriteCourse);

                return Response.created(uriinfo.getAbsolutePathBuilder().build()).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("gyms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFavoritesGym(@Context SecurityContext securityContext) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (user_id == user.getId()){
                return Response.ok(favoriteGymService.getAllFavoriteGym(user_id)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFavoritesCourse(@Context SecurityContext securityContext) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (user_id == user.getId()){
                return Response.ok(favoriteCourseService.getAllFavoriteCourse(user_id)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("gyms/{idGym: [0-9]+}")
    public Response deleteFavoriteGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteGymService favoriteGymService = new FavoriteGymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (user_id == user.getId()){
                favoriteGymService.deleteFavoriteGym(user.getId(), idGym);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Path("courses/{idCourse: [0-9]+}")
    public Response deleteFavoriteCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse) throws ServiceException {
        if (securityContext.isUserInRole("utente")) {
            UserService userService = new UserServiceImpl();
            FavoriteCourseService favoriteCourseService = new FavoriteCourseServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);

            if (user_id == user.getId()){
                favoriteCourseService.deleteFavoriteCourse(user_id, idCourse);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
