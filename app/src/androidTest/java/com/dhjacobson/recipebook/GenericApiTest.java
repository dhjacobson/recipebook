package com.example.recipebook;

import com.example.recipebook_commons.models.Recipe;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GenericApiTest {

    @Test
    public void genericApiTest() {
        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("https://jsonplaceholder.typicode.com/todos/1");
        WebTarget target = client.target("http://10.0.2.2:8080/recipes/1");
        Recipe r = target.request().get(Recipe.class);



    }

}
