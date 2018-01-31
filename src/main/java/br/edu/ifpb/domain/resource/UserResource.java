
package br.edu.ifpb.domain.resource;

import br.edu.ifpb.domain.model.User;
import br.edu.ifpb.domain.service.Users;
import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 31/01/2018
 */

@Path("user")
public class UserResource {

    @Inject
    private Users users;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response allUsers() {
        
        GenericEntity<List<User>> answer = new GenericEntity<
                List<User>>(users.all()){};
        
        if (answer != null)
            return Response.ok()
                    .entity(answer)
                    .build();
        
        return Response.status(Response.Status.NOT_FOUND)
                    .build();
    }
    
    @GET
    @Path("{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUserBy(@PathParam("email") String email) {
        
        User answer = users.with(email);
        
        if (answer != null) {
            return Response.ok()
                    .entity(answer)
                    .build();
        }
        
        return Response.status(Response.Status.NOT_FOUND)
                    .build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addUser(@Context UriInfo info, User user) {
        
        User answer = users.add(user);
        URI location = info.getAbsolutePathBuilder()
                .path(String.valueOf(answer.getEmail()))
                .build();
        
        return Response.created(location)
                    .entity(answer)
                    .build();
    }
    
}
