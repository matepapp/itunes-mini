package hu.bme.aut.itunesmini.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.SearchItem;
import hu.bme.aut.itunesmini.ui.result.ResultActivity;

public class MainActivity extends AppCompatActivity implements AddSearchItemDialogListener {
    private RecyclerView recyclerView;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        initFloatingActionButton();
        initRecyclerView();
    }

    private void loadItemsInBackground() {
        new AsyncTask<Void, Void, List<SearchItem>>() {
            @Override
            protected List<SearchItem> doInBackground(Void... voids) {
                return SearchItem.listAll(SearchItem.class);
            }

            @Override
            protected void onPostExecute(List<SearchItem> searchItems) {
                super.onPostExecute(searchItems);
                adapter.update(searchItems);
            }
        }.execute();
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NewSearchDialogFragment().show(
                        getSupportFragmentManager(), NewSearchDialogFragment.TAG);
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);
        adapter = new SearchAdapter(
                new OnSearchItemSelectedListener() {
                    @Override
                    public void onSearchItemSelected(SearchItem searchItem) {
                        Intent showResultsIntent = new Intent();
                        showResultsIntent.setClass(MainActivity.this, ResultActivity.class);
                        showResultsIntent.putExtra(ResultActivity.QUERY, searchItem.expression);
                        showResultsIntent.putExtra(ResultActivity.MEDIA, searchItem.type);
                        showResultsIntent.putExtra(ResultActivity.LIMIT, searchItem.resultCount);
                        startActivity(showResultsIntent);
                    }
                });

        loadItemsInBackground();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onSearchItemAdded(SearchItem searchItem) {
        adapter.addSearchItem(searchItem);
    }
}
