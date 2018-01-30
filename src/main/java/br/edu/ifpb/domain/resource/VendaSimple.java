package br.edu.ifpb.domain.resource;

import br.edu.ifpb.domain.model.Venda;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.UriInfo;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/01/2018, 14:02:51
 */
public class VendaSimple implements Serializable {

    private int id;
    private Link cliente;
    private List<Link> produtos;
    private Date createdIn;

    public VendaSimple() {
    }

    public VendaSimple(Venda venda, UriInfo info) {
        this.id = venda.getId();
        this.createdIn = venda.getCreatedIn();

        URI c = info.getBaseUriBuilder() // .../api/
                .path(ClienteResource.class) // .../api/cliente
                .path(venda.getCliente().getCpf()) // .../api/cliente/123
                .build();

//        String c = "http://localhost:8080/pos-vendas/api/cliente/"
//                + venda.getCliente().getCpf();
        this.cliente = new Link(venda.getCliente().getNome(), c.toString());

        this.produtos = new ArrayList<>();
        venda.getProdutos().forEach(produto -> {
            String idProduto = String.valueOf(produto.getId());
            URI p = info.getBaseUriBuilder() // .../api/
                    .path(ProdutoResource.class) // .../api/produto
                    .path(idProduto) // .../api/produto/1
                    .build();

//            String p = "http://localhost:8080/pos-vendas/api/produto/"
//                    + produto.getId();
            produtos.add(new Link(produto.getDescricao(), p.toString()));
        });

        //        URI uri = info.getBaseUriBuilder() // .../api/
//                .path(VendaResource.class) // .../api/venda
//                .path(id) // .../api/venda/1
//                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Link getCliente() {
        return cliente;
    }

    public void setCliente(Link cliente) {
        this.cliente = cliente;
    }

    public List<Link> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Link> produtos) {
        this.produtos = produtos;
    }

    public Date getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Date createdIn) {
        this.createdIn = createdIn;
    }

}
