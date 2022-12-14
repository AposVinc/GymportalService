package it.univaq.gymportal.resources.rest;

import it.univaq.gymportal.business.GymService;
import it.univaq.gymportal.business.UserService;
import it.univaq.gymportal.business.impl.GymServiceImpl;
import it.univaq.gymportal.business.impl.UserServiceImpl;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.Gym;
import it.univaq.gymportal.model.User;
import it.univaq.gymportal.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("gyms")
public class GymRes {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGyms(@QueryParam("name") String name, @QueryParam("region") String region) throws ServiceException {
        GymService gymService = new GymServiceImpl();
        if ( region==null && name==null ) {
            return Response.ok(gymService.getAllGyms()).build();

        } if ( region!=null && name==null ){
            return Response.ok(gymService.getGymsByRegion(region)).build();

        } if ( region==null && name!=null ){
            return Response.ok(gymService.getGymsByName(name)).build();

        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("{idGym: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGym(@PathParam("idGym") long idGym) throws ServiceException {
        GymService gymService = new GymServiceImpl();
        return Response.ok(gymService.getGym(idGym)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGym(@Context SecurityContext securityContext, @Context UriInfo uriinfo, Gym gym) throws ServiceException {
        if (securityContext.isUserInRole("gestore")){
            UserService userService = new UserServiceImpl();
            GymService gymService = new GymServiceImpl();

            String username = securityContext.getUserPrincipal().getName();
            User user = userService.getUserByUsername(username);
            gym.setUserId(user.getId());

            long idGym = gymService.createGym(gym);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getGym").build(idGym)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Path("{idGym: [0-9]+}")
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym, Gym gym) throws ServiceException {
        if (securityContext.isUserInRole("gestore")) {
            GymService gymService = new GymServiceImpl();

            if (isUserManagerOfGym(securityContext, idGym)){
                gym.setId(idGym);
                gymService.updateGym(gym);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Auth
    @Path("{idGym: [0-9]+}")
    public Response deleteGym(@Context SecurityContext securityContext, @PathParam("idGym") long idGym) throws ServiceException {
        if (securityContext.isUserInRole("gestore")) {
            GymService gymService = new GymServiceImpl();

            if (isUserManagerOfGym(securityContext, idGym)){
                gymService.deleteGym(idGym);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    @Path("{idGym: [0-9]+}/courses")
    public CourseRes getCourses(@PathParam("idGym") long idGym) {
        System.out.println("From GymRes to CourseRes");
        return new CourseRes(idGym);
    }

    @Path("{idGym: [0-9]+}/feedbacks")
    public FeedbackGymRes getFeedbacksGym(@PathParam("idGym") long idGym) {
        System.out.println("From GymRes to FeedbackGymRes");
        return new FeedbackGymRes(idGym);
    }


    public boolean isUserManagerOfGym(SecurityContext securityContext, long idGym) throws ServiceException {
        UserService userService = new UserServiceImpl();
        GymService gymService = new GymServiceImpl();

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);
        Gym gym = gymService.getGymWithManager(idGym);

        return gym.getUserId() == user.getId();
    }

}

























