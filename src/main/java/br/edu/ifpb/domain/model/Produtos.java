package br.edu.ifpb.domain.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 23/01/2018, 13:34:01
 */
@Stateless
public class Produtos {

    @PersistenceContext
    private EntityManager em;

    public Produto salvar(Produto produto) {
        em.persist(produto);
        return produto;
    }

    public List<Produto> todos() {
        return em.createQuery("FROM Produto p", Produto.class).getResultList();
    }

    public Produto produtoCom(int id) {
        Produto p = em.find(Produto.class, id);
        return p;
    }

}
