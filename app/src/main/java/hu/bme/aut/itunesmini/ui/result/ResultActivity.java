package hu.bme.aut.itunesmini.ui.result;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.Result;
import hu.bme.aut.itunesmini.model.ResultData;
import hu.bme.aut.itunesmini.model.ResultDataHolder;
import hu.bme.aut.itunesmini.model.SearchItem;
import hu.bme.aut.itunesmini.network.NetworkManager;
import hu.bme.aut.itunesmini.network.iTunesAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity implements ResultDataHolder {
    public static final String TAG = "ResultActivity";
    public static final String QUERY = "query_key";
    public static final String MEDIA = "media_key";
    public static final String LIMIT = "limit_key";
    private String query;
    private String media;
    private Integer limit;
    public ResultData resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        query = getIntent().getStringExtra(QUERY);
        media = getIntent().getStringExtra(MEDIA);
        limit = getIntent().getIntExtra(LIMIT, 0);

        getSupportActionBar().setTitle(getString(R.string.results, query));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Call<ResultData> call = NetworkManager.getInstance().getResult(query, media, limit.toString());
        call.enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                List<Result> results = response.body().results;
                Integer resultCount = response.body().resultCount;
                Log.d(TAG, "Number of results received: " + resultCount);
            }

            @Override
            public void onFailure(Call<ResultData>call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public ResultData getResultData() {
        return resultData;
    }
}
