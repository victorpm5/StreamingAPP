package com.isdcm.streamingapp.services;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ReproduccioRestService {

    private static String URL_REPRODUCCIO = "http://localhost:8081/api/video/reproduccio/";
    private static String API_KEY = "ThisIsStreamingAppApiKey";


    public static void reprodueix(Integer id){
        ClientConfig clientConfig = new DefaultClientConfig();
        Client client = Client.create(clientConfig);

        String url = URL_REPRODUCCIO + id;
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource
                .header("api_key", API_KEY)
                .put(ClientResponse.class, null);

        System.out.println("Resultat Petició reproducció REST:" + response.getStatus());
    }

}
