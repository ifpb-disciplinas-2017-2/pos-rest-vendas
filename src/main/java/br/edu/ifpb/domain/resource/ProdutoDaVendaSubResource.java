package br.edu.ifpb.domain.resource;

import br.edu.ifpb.domain.model.Produto;
import br.edu.ifpb.domain.model.Venda;
import br.edu.ifpb.domain.service.Vendas;
import br.edu.ifpb.domain.service.Produtos;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 23/01/2018, 14:46:53
 */
@Stateless
@Path("/")
public class ProdutoDaVendaSubResource {

    @EJB
    private Vendas service;

    @EJB
    private Produtos produtos;

    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response produtosDaVenda(@PathParam("idVenda") int idVenda) {
        Venda venda = service.vendaCom(idVenda);

        GenericEntity<List<Produto>> retorno = new GenericEntity
                <List<Produto>>(venda.getProdutos()) {
        };

        return Response.ok()
                .entity(retorno)
                .build();
    }

    @PUT
    @Path("{idProduto}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response adicionarProdutoAVenda(@PathParam("idVenda") int idVenda,
            @PathParam("idProduto") int idProduto) {

        Venda venda = service.vendaCom(idVenda);
        Produto produto = produtos.produtoCom(idProduto);
        venda.novo(produto);

        Venda retorno = service.atualizar(venda);

        return Response.ok()
                .entity(retorno)
                .build();
    }
}
