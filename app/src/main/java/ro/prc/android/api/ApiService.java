package ro.prc.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class ApiService {

    public static final String API_URL = "http://192.168.100.209:8080";
    //public static final String API_URL = "http://apollo.eed.usv.ro:8080";

    public static ApiCalls getService() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json,
                                            Type typeOfT,
                                            JsonDeserializationContext context)
                            throws JsonParseException {

                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .serializeNulls()
                .registerTypeAdapter(String.class, new StringConverter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setConverter(new GsonConverter(gson))
                .build();

        return restAdapter.create(ApiCalls.class);
    }
}
