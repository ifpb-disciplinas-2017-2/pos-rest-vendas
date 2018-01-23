package br.edu.ifpb.domain.resource;

import br.edu.ifpb.domain.model.Venda;
import br.edu.ifpb.domain.service.Produtos;
import br.edu.ifpb.domain.service.Vendas;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
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

    @Context
    private ResourceContext resourceContext;

    @EJB
    private Vendas service;

    @EJB
    private Produtos produtos;

    @GET
    public Response vendas() {
        GenericEntity<List<Venda>> retorno = new GenericEntity<
                List<Venda>>(service.todos()) {
        };
        return Response.ok()
                .entity(retorno)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response novaVenda(@Context UriInfo info) {
        Venda retorno = service.salvar(new Venda());
        String id = String.valueOf(retorno.getId());
        URI uri = info.getAbsolutePathBuilder().path(id).build();
        return Response.created(uri)
                .entity(retorno)
                .build();
    }

    @GET
    @Path("{id}")
    public Response listarVenda(@PathParam("id") int id) {
        Venda retorno = service.vendaCom(id);

        if (retorno == null) {
            String mensagem = "{ \"msg\": \"resource not found\"}";
            return Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
        }
        return Response.ok()
                .entity(retorno)
                .build();
    }

//    @GET
    @Path("{id}/produtos")
    public ProdutoDaVendaSubResource produtosDaVenda() {
        return resourceContext.getResource(ProdutoDaVendaSubResource.class);
    }

//    @PUT
//    @Path("{id}/produtos/{idProduto}")
//    public ProdutoDaVendaSubResource adicionarProdutoAVenda() {
//        return resourceContext.getResource(ProdutoDaVendaSubResource.class);
////        Venda venda = service.vendaCom(id);
////        Produto produto = produtos.produtoCom(idProduto);
////        venda.novo(produto);
////
////        Venda retorno = service.atualizar(venda);
////
////        return Response.ok()
////                .entity(retorno)
////                .build();
//    }
}
