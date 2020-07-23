package validate;

import local.Validation;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/validate")
public class HelloServer {
    Validation validation = new Validation();

    public HelloServer() {
    }

    @POST
    @Path("/form")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String helloForm(@FormParam("name") String name) throws IOException {

        if (validation.validate(name, false)) {
            return name + " is valid IBAN number";
        } else {
            return name + " is not valid IBAN number";
        }
    }

    @POST
    @Path("/multiple")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<String> multipleForm(@FormParam("name") String name, @FormParam("path") String path) throws IOException {
        List<String> read = Validation.readWebMultipleInput(name, path);
        List<String> newList = new ArrayList<>();
        for (String item : read) {
            newList.add(item + ";" + validation.validate(item, false) + "\n");
        }
        return newList;
    }
}