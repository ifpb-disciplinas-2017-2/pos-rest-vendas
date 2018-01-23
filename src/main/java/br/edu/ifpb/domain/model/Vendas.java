package br.edu.ifpb.domain.model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 23/01/2018
 */
@Stateless
public class Vendas {

    @PersistenceContext
    private EntityManager manager;

    public Venda salvar(Venda venda) {
        manager.persist(venda);
        return venda;
    }

    public List<Venda> todos() {
        return manager.createQuery("FROM Venda v", Venda.class)
                .getResultList();
    }

    public Venda vendaCom(int id) {
        Venda v = manager.find(Venda.class, id);
        return v;
    }

    public Venda atualizar(Venda venda) {
        Venda merge = manager.merge(venda);
        return merge;
    }

}
