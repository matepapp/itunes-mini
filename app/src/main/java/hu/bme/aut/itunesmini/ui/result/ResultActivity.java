package hu.bme.aut.itunesmini.ui.result;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.SearchItem;

public class ResultActivity extends AppCompatActivity {
    public static final String QUERY = "query_key";
    public static final String MEDIA = "media_key";
    public static final String LIMIT = "limit_key";
    private String query;
    private String media;
    private String limit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        query = getIntent().getStringExtra(QUERY);

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
}
