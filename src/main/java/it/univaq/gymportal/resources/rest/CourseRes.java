package it.univaq.gymportal.resources.rest;

import it.univaq.gymportal.business.CourseService;
import it.univaq.gymportal.business.GymService;
import it.univaq.gymportal.business.UserService;
import it.univaq.gymportal.business.impl.CourseServiceImpl;
import it.univaq.gymportal.business.impl.GymServiceImpl;
import it.univaq.gymportal.business.impl.UserServiceImpl;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.Course;
import it.univaq.gymportal.model.Gym;
import it.univaq.gymportal.model.User;
import it.univaq.gymportal.security.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

public class CourseRes {

    private final long idGym;

    CourseRes(long idGym) {
        this.idGym = idGym;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses(@QueryParam("name") String name) throws ServiceException {
        CourseService courseService = new CourseServiceImpl();
        if ( name != null ){
            return Response.ok(courseService.getCoursesByName(idGym,name)).build();
        }

        return Response.ok(courseService.getCoursesByGym(idGym)).build();
    }

    @GET
    @Path("{idCourse: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("idCourse") long idCourse) throws ServiceException {
        CourseService courseService = new CourseServiceImpl();
        return Response.ok(courseService.getCourse(idCourse)).build();
    }

    @POST
    @Auth
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(@Context SecurityContext securityContext, @Context UriInfo uriinfo, Course course) throws ServiceException {
        if (securityContext.isUserInRole("gestore")) {
            CourseService courseService = new CourseServiceImpl();

            course.setGym(idGym);

            long idCourse = courseService.createCourse(course);

            return Response.created(uriinfo.getAbsolutePathBuilder().path(this.getClass(), "getCourse").build(idCourse)).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @PUT
    @Auth
    @Path("{idCourse: [0-9]+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse, Course course) throws ServiceException {
        if (securityContext.isUserInRole("gestore")) {
            CourseService courseService = new CourseServiceImpl();

            if (isUserManagerOfGym(securityContext)){
                course.setId(idCourse);
                courseService.updateCourse(course);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @DELETE
    @Auth
    @Path("{idCourse: [0-9]+}")
    public Response deleteCourse(@Context SecurityContext securityContext,@PathParam("idCourse") long idCourse) throws ServiceException {
        if (securityContext.isUserInRole("gestore")) {
            CourseService courseService = new CourseServiceImpl();

            if (isUserManagerOfGym(securityContext)){
                courseService.deleteCourse(idCourse);
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }


    @Path("{idCourse: [0-9]+}/feedbacks")
    public FeedbackCourseRes getFeedbacksCourse(@PathParam("idCourse") long idCourse) {
        System.out.println("From CourseRes to FeedbackCourseRes");
        return new FeedbackCourseRes(idCourse);
    }


    public boolean isUserManagerOfGym(SecurityContext securityContext) throws ServiceException {
        UserService userService = new UserServiceImpl();
        GymService gymService = new GymServiceImpl();

        String username = securityContext.getUserPrincipal().getName();
        User user = userService.getUserByUsername(username);
        Gym gym = gymService.getGymWithManager(idGym);

        return gym.getUser() == user.getId();
    }

}
