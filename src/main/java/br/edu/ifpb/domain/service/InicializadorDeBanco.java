package br.edu.ifpb.domain.service;

import br.edu.ifpb.domain.model.Cliente;
import br.edu.ifpb.domain.model.Produto;
import br.edu.ifpb.domain.model.Venda;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 24/01/2018, 14:27:02
 */
@Singleton
@Startup
public class InicializadorDeBanco {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void iniciarBancoDeDados() {
        Cliente cliente = new Cliente("123", "kiko");
        Venda venda = new Venda();
        Produto arroz = new Produto("Arroz", 4.5);
        Produto feijao = new Produto("Feijão", 3.95);
        em.persist(cliente);
        em.persist(arroz);
        em.persist(feijao);
        venda.setCliente(cliente);
        venda.novo(arroz);
        venda.novo(feijao);
        em.persist(venda);

    }

}
