package hu.bme.aut.itunesmini.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import hu.bme.aut.itunesmini.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpressionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFab();
        initRecyclerView();
    }

    private void initFab() {
        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: new search dialog
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.MainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpressionAdapter(
                new OnExpressionSelectedListener() {
                    @Override
                    public void onExpressionSelected(String city) {
                        // Todo: ResultActivity
                    }
                });

        adapter.addExpression("Macklemore");
        adapter.addExpression("John Legend");
        adapter.addExpression("Interstellar");
        adapter.addExpression("Game of Thrones");
        recyclerView.setAdapter(adapter);
    }
}
