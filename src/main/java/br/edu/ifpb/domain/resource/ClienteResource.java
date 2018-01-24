
package br.edu.ifpb.domain.resource;

import br.edu.ifpb.domain.model.Cliente;
import br.edu.ifpb.domain.service.Clientes;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
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
 * @since 24/01/2018
 */

@Path("cliente")
public class ClienteResource {
    
    @EJB
    private Clientes service;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response todos() {
        GenericEntity<List<Cliente>> retorno = new GenericEntity<List<Cliente>>
            (service.todos()){};
        
        return Response.ok()
                .entity(retorno)
                .build();
    }
    
    @GET
    @Path("{cpf}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response recuperarUm(@PathParam("cpf") String cpf) {
        Cliente retorno = service.comCpf(cpf);
        
        return Response.ok()
                .entity(retorno)
                .build();
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response add(@Context UriInfo info, Cliente cliente) {
        Cliente retorno = service.salvar(cliente);
        URI build = info.getAbsolutePathBuilder()
                .path(String.valueOf(retorno.getCpf()))
                .build();
        
        return Response.created(build)
                .entity(retorno)
                .build();
    }
    
}
