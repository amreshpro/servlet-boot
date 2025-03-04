package com.amreshpro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Embedded Tomcat...");
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);  // Set the port explicitly

        // Add servlet context
        Context context = tomcat.addContext("", null);
        Tomcat.addServlet(context, "HelloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello", "HelloServlet");

        // Start Tomcat
        tomcat.start();
        tomcat.getConnector();
        // Wait for the server to be fully up
        Thread.sleep(5000); // Wait 5 seconds before making the request

        // Now send the request
        sendRequest("http://localhost:8080/hello");

        tomcat.getServer().await(); // Keep server running
    }

    // Helper method to send an HTTP request
    private static void sendRequest(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
