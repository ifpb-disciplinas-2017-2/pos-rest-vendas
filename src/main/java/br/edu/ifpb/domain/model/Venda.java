package br.edu.ifpb.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 23/01/2018
 */
@Entity
public class Venda implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Produto> produtos;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdIn;
    
    @OneToOne
    private Cliente cliente;

    public Venda() {
        this(new ArrayList<Produto>());
    }

    public Venda(List<Produto> produtos) {
        this.produtos = produtos;
        this.createdIn = Date.from(Instant.now());
    }

    public void novo(Produto produto) {
        this.produtos.add(produto);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(Date createdIn) {
        this.createdIn = createdIn;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
