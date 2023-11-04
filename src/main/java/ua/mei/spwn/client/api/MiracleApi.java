package ua.mei.spwn.client.api;

import com.google.gson.*;
import net.minecraft.text.*;
import ua.mei.spwn.client.*;
import ua.mei.spwn.client.api.types.*;
import ua.mei.spwn.client.ui.*;

import java.net.*;
import java.net.http.*;
import java.util.*;

public class MiracleApi {
    public static final String API_URL = "http://132.226.132.108:25600/api";
    private static final Gson gson = new Gson();
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static List<City> getCitiesSp() {
        JsonObject json = request("/cities/sp");
        if (json != null) {
            JsonArray list = json.get("data").getAsJsonArray();
            List<City> cities = new ArrayList<>();
            for (JsonElement element : list.asList()) {
                JsonObject object = element.getAsJsonObject();
                cities.add(new City(
                        object.get("id").getAsString(),
                        object.get("name").getAsString(),
                        object.get("x").getAsInt(),
                        object.get("z").getAsInt()
                ));
            }
            return cities;
        } else {
            return new ArrayList<>();
        }
    }

    public static List<City> getCitiesSpm() {
        JsonObject json = request("/cities/spm");
        if (json != null) {
            JsonArray list = json.get("data").getAsJsonArray();
            List<City> cities = new ArrayList<>();
            for (JsonElement element : list.asList()) {
                JsonObject object = element.getAsJsonObject();
                cities.add(new City(
                        object.get("id").getAsString(),
                        object.get("name").getAsString(),
                        object.get("x").getAsInt(),
                        object.get("z").getAsInt()
                ));
            }
            return cities;
        } else {
            return new ArrayList<>();
        }
    }

    public static JsonObject request(String endpoint) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + endpoint))
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .method("GET", HttpRequest.BodyPublishers.noBody());

            HttpResponse<String> response = httpClient.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), JsonObject.class);
        } catch (Exception e) {
            MessageScreen.openMessage(Text.translatable("gui.spwn.title.error"), Text.literal(e.getMessage()));
            return null;
        }
    }
}
