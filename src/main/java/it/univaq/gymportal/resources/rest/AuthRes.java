package it.univaq.gymportal.resources.rest;

//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.SignatureException;
//import it.univaq.gymportal.JWTHelpers;
import it.univaq.gymportal.business.UserService;
import it.univaq.gymportal.business.impl.UserServiceImpl;
import it.univaq.gymportal.exceptions.ServiceException;
import it.univaq.gymportal.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.sql.SQLException;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthRes {

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(@Context UriInfo uriInfo, User user) throws ServiceException {
        UserService userService = new UserServiceImpl();

        long idUser = userService.createUser(user);
        userService.addRoleToUser(idUser);

        return Response.created(uriInfo.getBaseUriBuilder().path("users").path(UserRes.class,"getUser").build(idUser)).build();
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@Context UriInfo uriinfo,
                                     @FormParam("username") String username, @FormParam("password") String password) throws ServiceException, SQLException {
        UserService userService = new UserServiceImpl();
        if (userService.checkUser(username, password)){
            System.out.println("LOGIN OK");
            if (userService.checkRole(username, "utente")){
                User user = userService.getUserByUsername(username);
                String authToken = issueToken(uriinfo, username);

                return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).location(uriinfo.getBaseUriBuilder().path("users").path(UserRes.class,"getUser").build(user.getId())).build();
            }
            return Response.status(UNAUTHORIZED).build();
        }
        return Response.status(UNAUTHORIZED).build();

    }


    @POST
    @Path("/admin/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateAdmin(@Context UriInfo uriinfo,
                                     @FormParam("username") String username, @FormParam("password") String password) throws ServiceException, SQLException {
        UserService userService = new UserServiceImpl();
        if (userService.checkUser(username, password)){
            System.out.println("LOGIN OK");
            if (userService.checkRole(username, "gestore")){
                User user = userService.getUserByUsername(username);
                String authToken = issueToken(uriinfo, username);

                return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).location(uriinfo.getBaseUriBuilder().path("users").path(UserRes.class,"getUser").build(user.getId())).build();
            }
            return Response.status(UNAUTHORIZED).build();
        }
        return Response.status(UNAUTHORIZED).build();

    }

    private String issueToken(UriInfo context, String username) {
//        Key key = JWTHelpers.getInstance().getJwtKey();
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuer(context.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(key)
//                .compact();
        String token = '';
        return token;
    }

    @GET
    @Path("/refresh")
    public Response refreshJWTToken(@Context HttpHeaders headers, @Context UriInfo uriinfo) {
        try {
            System.out.println("REFRESH TOKEN");
            String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
            String token = authorizationHeader.substring("Bearer".length()).trim();
            Key key = JWTHelpers.getInstance().getJwtKey();
            Claims jwsc = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            String newtoken = issueToken(uriinfo, jwsc.getSubject());

            return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + newtoken).build();

        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

}