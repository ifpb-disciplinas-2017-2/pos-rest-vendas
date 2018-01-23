
package br.edu.ifpb.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    private LocalDateTime createdIn;

    public Venda(List<Produto> produtos) {
        this.produtos = produtos;
        this.createdIn = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedIn() {
        return createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn) {
        this.createdIn = createdIn;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
}
