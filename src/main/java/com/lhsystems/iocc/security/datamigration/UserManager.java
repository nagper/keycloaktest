package com.lhsystems.iocc.security.datamigration;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;

public class UserManager {

    Keycloak keycloak;
    String[] userNames = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    RealmResource realm;

    public static void main(String[] args) {
        new UserManager().createUsers();
        new UserManager().createUsers();
    }

    public UserManager() {
        keycloak = KeycloakBuilder.builder()
                .realm("master")
                .clientId("admin-cli")
                .username("admin")
                .password("admin")
                .serverUrl("http://localhost:8085")
                .build();

        realm = keycloak.realm("master");
    }

    public void createUsers() {
        System.out.println("Creating users");
        for (String userName : userNames) {
            UserRepresentation user = new UserRepresentation();
            user.setUsername(userName);

            Response response = realm.users().create(user);

            System.out.println(user.getUsername() + ": " + response.getStatus());
        }
    }

}
