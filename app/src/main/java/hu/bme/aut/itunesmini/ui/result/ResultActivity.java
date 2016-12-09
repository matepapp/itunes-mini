package hu.bme.aut.itunesmini.ui.result;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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
    private RecyclerView recyclerView;
    private ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        query = getIntent().getStringExtra(QUERY);
        media = getIntent().getStringExtra(MEDIA);
        limit = getIntent().getIntExtra(LIMIT, 0);

        getSupportActionBar().setTitle(getString(R.string.results, query));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        Call<ResultData> call = NetworkManager.getInstance().getResult(query, media, limit.toString());
        call.enqueue(new Callback<ResultData>() {
            @Override
            public void onResponse(Call<ResultData> call, Response<ResultData> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    displayData(response.body());
                } else {
                    Toast.makeText(ResultActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultData> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(ResultActivity.this,
                        "Error in network request, check LOG",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayData(ResultData result) {
        resultData = result;
        recyclerView = (RecyclerView) findViewById(R.id.result_recycler_view);
        adapter = new ResultAdapter(this, resultData.getResults(), new OnResultCardSelectedListener() {
            @Override
            public void onResultCardSelected(Result result) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(result.getTrackViewUrl()));
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
