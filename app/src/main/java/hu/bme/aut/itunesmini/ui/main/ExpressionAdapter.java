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

/**
 * Created by matepapp on 2016. 12. 05..
 */
public class ExpressionAdapter extends RecyclerView.Adapter<ExpressionAdapter.ExpressionViewHolder> {
    private final List<String> expressions;
    private OnExpressionSelectedListener listener;

    public ExpressionAdapter(OnExpressionSelectedListener listener) {
        this.listener = listener;
        expressions = new ArrayList<>();
    }

    @Override
    public ExpressionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_expression, parent, false);

        ExpressionViewHolder viewHolder = new ExpressionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExpressionViewHolder holder, int position) {
        holder.position = position;
        holder.nameTextView.setText(expressions.get(position));
    }

    @Override
    public int getItemCount() {
        return expressions.size();
    }

    public void addExpression(String newExpression) {
        expressions.add(newExpression);
        notifyItemInserted(expressions.size() - 1);
    }

    public void removeExpression(int position) {
        expressions.remove(position);
        notifyItemRemoved(position);
        if (position < expressions.size()) {
            notifyItemRangeChanged(position, expressions.size() - position);
        }
    }

    public class ExpressionViewHolder extends RecyclerView.ViewHolder {
        int position;
        TextView nameTextView;
        Button editButton;
        Button deleteButton;

        public ExpressionViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(
                            R.id.ExpressionItemNameTextView);
            deleteButton = (Button) itemView.findViewById(R.id.ExpressionItemDeleteButton);
            editButton = (Button) itemView.findViewById(R.id.ExpressionItemEditButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onExpressionSelected(expressions.get(position));
                    }
                }
            });
        }
    }

}
