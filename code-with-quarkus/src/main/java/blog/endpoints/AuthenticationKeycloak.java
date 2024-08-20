package blog.endpoints;

import java.util.List;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/admin")
public class AuthenticationKeycloak {

    @Inject
    Keycloak keycloak;

    @GET
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        return keycloak.realm("blog-realm").roles().list();
    }
}
