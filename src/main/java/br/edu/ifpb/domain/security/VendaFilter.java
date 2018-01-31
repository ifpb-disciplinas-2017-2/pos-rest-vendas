package br.edu.ifpb.domain.security;

import br.edu.ifpb.domain.service.Users;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.enterprise.inject.spi.CDI;
import javax.json.Json;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 30/01/2018, 13:37:49
 */
@Provider
//@Priority(Priorities.AUTHENTICATION)
//@PreMatching
public class VendaFilter implements ContainerRequestFilter {

    private final Users users;

    public VendaFilter() throws NamingException {
        String name = "java:global/${rootArtifactId}/Users";
        this.users = (Users) new InitialContext()
                .lookup(name);
    }
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        boolean contains = requestContext.getUriInfo().getPath().contains("venda");
        if (!contains) {
            return;
        }
//        requestContext.getHeaders()
//                .forEach((k, v) -> System.out.println(k + " -> " + v));
        String headerString = requestContext.getHeaderString("Authorization");

        boolean vazio = (headerString == null || headerString.isEmpty());
//        System.out.println("vazio = " + vazio + " " + headerString);
        if (vazio) {
            JsonObject add = Json.createObjectBuilder().add("msg", "header not valid").build();
            Response response = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(add)
                    .build();
            requestContext.abortWith(response);
//            return;
        } else {

            String withoutBasic = headerString.replaceAll("Basic ", "");
//        System.out.println("headerString = " + headerString);
            //Basic YWRtaW46bWVzbWFjb2lzYQ==
            byte[] decode = Base64.getDecoder().decode(withoutBasic);
//        System.out.println(new String(decode));
            String usuarioComSenha = new String(decode);
            StringTokenizer string = new StringTokenizer(usuarioComSenha, ":");
            String usuario = string.nextToken();
            String senha = string.nextToken();
//        System.out.println("usuario = " + usuario + " senha: " + senha);

            boolean usarioExiste = "admin".equals(usuario) && "mesmacoisa".equals(senha);

            if (!usarioExiste) {
                JsonObject add = Json.createObjectBuilder().add("msg", "user not find").build();
                Response response = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .type(MediaType.APPLICATION_JSON)
                        .entity(add)
                        .build();
                requestContext.abortWith(response);
//            return;
            }
        }
//        System.out.println(".,jhaklsjhfak");

    }

}
