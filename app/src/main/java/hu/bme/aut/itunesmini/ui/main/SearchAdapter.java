package hu.bme.aut.itunesmini.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.SearchItem;

/**
 * Created by matepapp on 2016. 12. 05..
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private final List<SearchItem> items;
    private OnSearchItemSelectedListener listener;

    public SearchAdapter(OnSearchItemSelectedListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.search_item, parent, false);

        SearchViewHolder viewHolder = new SearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.position = position;
        holder.expressionTextView.setText(items.get(position).expression);
        holder.typeTextView.setText(items.get(position).type.toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addSearchItem(SearchItem newSearchItem) {
        items.add(newSearchItem);
        notifyItemInserted(items.size() - 1);
    }

    public void removeSearchItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        if (position < items.size()) {
            notifyItemRangeChanged(position, items.size() - position);
        }
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        int position;
        TextView expressionTextView;
        TextView typeTextView;
        Button editButton;
        Button deleteButton;

        public SearchViewHolder(View itemView) {
            super(itemView);
            expressionTextView = (TextView) itemView.findViewById(
                            R.id.SearchItemExpressionTextView);
            typeTextView = (TextView) itemView.findViewById(
                            R.id.SearchItemTypeTextView);
            deleteButton = (Button) itemView.findViewById(R.id.SearchItemDeleteButton);
            editButton = (Button) itemView.findViewById(R.id.SearchItemEditButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onSearchItemSelected(items.get(position));
                    }
                }
            });
        }
    }

}
