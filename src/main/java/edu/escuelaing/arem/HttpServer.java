package edu.escuelaing.arem;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(38000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 38000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String urlTitle = "";
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("hello?name=")) {
                    String[] res = inputLine.split("name=");
                    urlTitle = (res[1].split("HTTP")[0]).replace(" ", "");
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            if (!urlTitle.equals("")) {
                String answer = HttpClient.filmTitle(urlTitle);
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<br>"
                        + "<table border=\" 0.5 \"> \n "
                        + createTable(answer)
                        + "</table>";
            } else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + htmlWithForms();
            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Metodo que crea tabla en formato Html
     * 
     * @param document Archivo para crea tabla
     * @return String tabla
     */
    private static String createTable(String document) {
        HashMap<String, String> dict = new HashMap<String, String>();
        JSONArray Array = new JSONArray(document);
        for (int i = 0; i < Array.length(); i++) {
            JSONObject object = Array.getJSONObject(i);
            for (String key : object.keySet()) {
                dict.put(key.toString(), object.get(key).toString());
            }
        }
        String table = "<tr> \n";
        for (String keys : dict.keySet()) {
            String value = dict.get(keys);
            table = table + "<td>" + keys + "</td>\n";
            table = table + "<td>" + value + "</td>\n";
            table = table + "<tr> \n";
        }
        return table;
    }

    public static String htmlWithForms() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Movie Search Engine</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Movie Search Engine</h1>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Title:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                "        </form> \n" +
                "        <div id=\"getrespmsg\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function loadGetMsg() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                if (nameVar) {\n" +
                "                   console.log(\"Nombre \" + nameVar)\n" +
                "                   const xhttp = new XMLHttpRequest();\n" +
                "                   xhttp.onload = function() {\n" +
                "                       document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                       this.responseText;\n" +
                "                   }\n" +
                "                   xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n" +
                "                   xhttp.send();\n" +
                "                };\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }
}