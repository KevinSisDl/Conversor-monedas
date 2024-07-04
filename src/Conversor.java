import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Conversor {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/46959cbb582477ef9c8bafd2/pair/";
    private static final Gson gson = new Gson();
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                   .uri(URI.create(API_URL + monedaOrigen + "/" + monedaDestino))
                   .GET()
                   .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement jsonElement = gson.fromJson(response.body(), JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            double tipoCambio = jsonObject.get("conversion_rate").getAsDouble();

            return cantidad * tipoCambio;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al realizar la conversión: " + e.getMessage());
            return 0;
        }
    }
}


