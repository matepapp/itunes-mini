package hu.bme.aut.itunesmini.network;

import hu.bme.aut.itunesmini.model.ResultData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by matepapp on 2016. 12. 09..
 */

public interface iTunesAPI {
    @GET("/search")
    Call<ResultData> getResult(
            @Query("term") String term,
            @Query("media") String media,
            @Query("limit") String limit
    );
}
