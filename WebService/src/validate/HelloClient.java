package validate;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import webserver.TomcatServer;

import java.util.List;

public class HelloClient {

    public WebTarget webTarget;

    public HelloClient() {
        webTarget = ClientBuilder.newClient().target(TomcatServer.HELLO_URL);
    }

    public String helloForm(String name) {
        Form form = new Form();
        form.param("name", name);
        return webTarget.path("form").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED))
                .readEntity(String.class);
    }

    public List multipleForm(String name, String path) {
        Form form = new Form();
        form.param("name", name);
        form.param("path", path);
        return webTarget.path("multiple").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED)).readEntity(List.class);
    }

    public static void main(String[] args) {
        HelloClient client = new HelloClient();
        System.out.println("Go to http://localhost:8085/webApp/");
    }
}
