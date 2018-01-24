
package br.edu.ifpb.domain.resource;

import br.edu.ifpb.domain.model.Cliente;
import br.edu.ifpb.domain.model.Venda;
import br.edu.ifpb.domain.service.Clientes;
import br.edu.ifpb.domain.service.Vendas;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michelle Oliveira
 * @mail miolivc@outlook.com
 * @since 24/01/2018
 */

@Path("/")
public class ClienteVendaSubResource {
    
    @EJB
    private Vendas serviceVenda;
    @EJB
    private Clientes serviceClientes;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response clienteVenda(@PathParam("id") int id) {
        
        Cliente retorno = serviceVenda.vendaCom(id).getCliente();
        
        if (retorno == null) {
            String mensagem = "{ \"msg\": \"resource not found\"}";
            return Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
        }
        
        return Response.ok()
                .entity(retorno)
                .build();
    }
    
    @PUT
    @Path("{cpf}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response adcionarClienteVenda(@PathParam("id") int id, 
            @PathParam("cpf") String cpf) {
        
        Venda venda = serviceVenda.vendaCom(id);
        Cliente cliente = serviceClientes.comCpf(cpf);
        venda.setCliente(cliente);
        
        Venda retorno = serviceVenda.atualizar(venda);
        
        return Response.ok()
                .entity(retorno)
                .build();
    }
}
