package com.example.recipebook;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class InternetTest {

    @Test
    public void genericApiTest() {
        Client client = ClientBuilder.newClient();
        client.target("https://jsonplaceholder.typicode.com/todos/1").request().get();
        assert(true);
    }

}
