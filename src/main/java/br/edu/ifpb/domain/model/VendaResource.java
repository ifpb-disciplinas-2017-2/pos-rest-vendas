package br.edu.ifpb.domain.model;

import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
        return Response.ok()
                .entity(retorno)
                .build();
    }

    @GET
    @Path("{id}/produtos")
    public Response produtosDaVenda(@PathParam("id") int id) {
        Venda venda = service.vendaCom(id);

        GenericEntity<List<Produto>> retorno = new GenericEntity<List<Produto>>(venda.getProdutos()) {
        };

        return Response.ok()
                .entity(retorno)
                .build();
    }

    @PUT
    @Path("{id}/produtos/{idProduto}")
    public Response adicionarProdutoAVenda(@PathParam("id") int id,
            @PathParam("idProduto") int idProduto) {

        Venda venda = service.vendaCom(id);
        Produto produto = produtos.produtoCom(idProduto);
        venda.novo(produto);

        Venda retorno = service.atualizar(venda);

        return Response.ok()
                .entity(retorno)
                .build();
    }
}
