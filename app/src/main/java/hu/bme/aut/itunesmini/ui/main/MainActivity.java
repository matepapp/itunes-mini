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

public class MainActivity extends AppCompatActivity implements AddExpressionDialogListener {
    private RecyclerView recyclerView;
    private ExpressionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    public void onExpressionAdded(String expression) {
        adapter.addExpression(expression);
    }
}
