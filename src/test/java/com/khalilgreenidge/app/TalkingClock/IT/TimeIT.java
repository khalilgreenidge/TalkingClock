package com.khalilgreenidge.app.TalkingClock.IT;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TimeIT {

    @Test
    public void test200() throws IOException {

        // Given
        String time = "16:30";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/v1/time/" + time);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        // Then
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    public void test400() throws IOException {

        // Given
        String time = "163:30";
        HttpUriRequest request = new HttpGet( "http://localhost:8080/v1/time/" + time);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

        System.out.println("Response: " + httpResponse);

        // Then
        assertEquals(400, httpResponse.getStatusLine().getStatusCode());
    }
}
