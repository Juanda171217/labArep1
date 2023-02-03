package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    private static final String userAgent = "Mozilla/5.0";
    private static String urlApi = "http://www.omdbapi.com/?t=";
    private static final String urlApiKey = "&apikey=537fcdd";

    /**
     * Metodo que obtiene la informacion de la pelicula
     * 
     * @param title
     * @return titulo de la pelicula
     * @throws IOException class exception
     */
    public static String filmTitle(String title) throws IOException {

        // Connection Api Cinema
        String urlComplete = urlApi + title + urlApiKey;
        URL obj = new URL(urlComplete);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgent);
        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print result
            System.out.println(response.toString());
            String ans = "[" + response.toString() + "]";
            return ans;
        } else {
            System.out.println("GET request not worked");
        }
        return "Failed";
    }

}