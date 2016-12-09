package hu.bme.aut.itunesmini.network;

import hu.bme.aut.itunesmini.model.ResultData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matepapp on 2016. 12. 09..
 */

public class NetworkManager {
    private static final String ENDPOINT_ADDRESS = "https://itunes.apple.com";

    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private Retrofit retrofit;
    private iTunesAPI iTunesApi;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_ADDRESS)
                .client(new OkHttpClient.Builder().build()).addConverterFactory(GsonConverterFactory.create()).build();
        iTunesApi = retrofit.create(iTunesAPI.class);
    }

    public Call<ResultData> getResult(String term, String media, String limit) {
        return iTunesApi.getResult(term, media, limit);
    }
}
