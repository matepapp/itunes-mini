package hu.bme.aut.itunesmini.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.SearchItem;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter(
                new OnSearchItemSelectedListener() {
                    @Override
                    public void onSearchItemSelected(SearchItem searchItem) {
                        // Todo: ResultActivity
                    }
                });

        SearchItem searchItem1 = new SearchItem("Macklemore", 25, SearchItem.Type.MUSIC);
        SearchItem searchItem2 = new SearchItem("Punnany Massif", 50, SearchItem.Type.MUSIC);
        SearchItem searchItem3 = new SearchItem("Facebook", 25, SearchItem.Type.SOFTWARE);

        adapter.addSearchItem(searchItem1);
        adapter.addSearchItem(searchItem2);
        adapter.addSearchItem(searchItem3);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSearchItemAdded(SearchItem searchItem) {
        adapter.addSearchItem(searchItem);
    }
}
