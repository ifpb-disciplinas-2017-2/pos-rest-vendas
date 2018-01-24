
package br.edu.ifpb.domain.service;

import br.edu.ifpb.domain.model.Cliente;
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
public class Clientes {

    @PersistenceContext
    private EntityManager manager;
    
    public List<Cliente> todos() {
        return manager.createQuery("FROM Cliente c", 
                Cliente.class).getResultList();
    }
    
    public Cliente salvar(Cliente cliente) {
        manager.persist(cliente);
        return cliente;
    }
    
    public Cliente comCpf(String cpf) {
        Cliente cliente = manager.find(Cliente.class, cpf);
        return cliente;
    }
    
}
