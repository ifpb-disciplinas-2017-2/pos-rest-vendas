package br.edu.ifpb.domain.model;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 23/01/2018, 14:46:53
 */
@Stateless
@Path("")
public class VendaSubResource {

    @EJB
    private Vendas service;

    @EJB
    private Produtos produtos;

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
