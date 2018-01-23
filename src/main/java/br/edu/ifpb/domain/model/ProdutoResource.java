package br.edu.ifpb.domain.model;

import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 23/01/2018, 13:33:17
 */
@Stateless
@Path("produto")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProdutoResource {

    @EJB
    private Produtos service;

    @GET
    public Response listarProdutos() {
        GenericEntity<List<Produto>> retorno = new GenericEntity<List<Produto>>(service.todos()) {
        };
        return Response.ok()
                .entity(retorno)
                .build();
    }

    @GET
    @Path("{id}")
    public Response listarProduto(@PathParam("id") int id) {
        Produto retorno = service.produtoCom(id);
        
        return Response.ok()
                .entity(retorno)
                .build();
    }
 
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response salvar(@Context UriInfo info, Produto produto) {
        Produto retorno = service.salvar(produto);
        String id = String.valueOf(retorno.getId());
        URI uri = info.getAbsolutePathBuilder().path(id).build();
        return Response.created(uri)
                .entity(retorno)
                .build();
    }

}
