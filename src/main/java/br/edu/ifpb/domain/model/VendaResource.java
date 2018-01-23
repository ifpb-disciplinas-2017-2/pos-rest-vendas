
package br.edu.ifpb.domain.model;

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
 * @since 23/01/2018
 */

@Path("venda")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class VendaResource {

    @EJB
    private Vendas service;
    
    @GET
    public Response vendas() {
        GenericEntity<List<Venda>> retorno = new GenericEntity<
                List<Venda>>(service.todos()) {};
        return Response.ok().entity(retorno).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response salvarVenda(@Context UriInfo info, Venda venda) {
        service.salvar(venda);
        String id =  String.valueOf(venda.getId());
        URI uri = info.getAbsolutePathBuilder().path(id).build();
        return Response.created(uri).entity(venda).build();
    }
    
    @GET
    @Path("{id}")
    public Response listarProduto(@PathParam("id") int id) {
        Venda retorno = service.vendaCom(id);
        return Response.ok()
                .entity(retorno)
                .build();
    }
}
