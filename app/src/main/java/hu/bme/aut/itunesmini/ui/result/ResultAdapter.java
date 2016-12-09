package hu.bme.aut.itunesmini.ui.result;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hu.bme.aut.itunesmini.R;
import hu.bme.aut.itunesmini.model.Result;

/**
 * Created by matepapp on 2016. 12. 09..
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private Context context;
    private List<Result> results;
    private OnResultCardSelectedListener listener;

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        int position;
        public TextView title;
        public TextView subtitle;
        public ImageView artwork;

        public ResultViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subtitle = (TextView) view.findViewById(R.id.subtitle);
            artwork = (ImageView) view.findViewById(R.id.artwork);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onResultCardSelected(results.get(position));
                }
            });
        }
    }

    public ResultAdapter(Context context, List<Result> results, OnResultCardSelectedListener listener) {
        this.context = context;
        this.results = results;
        this.listener = listener;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_card, parent, false);

        return new ResultViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ResultViewHolder holder, int position) {
        holder.position = position;
        Result result = results.get(position);
        holder.subtitle.setText(result.getArtistName());
        if(!result.getTrackName().equals("")) {
           holder.title.setText(result.getTrackName());
        } else if(!result.getCollectionName().equals("")) {
            holder.title.setText(result.getCollectionName());
        }

        Glide.with(context).load(result.getArtworkUrl100()).crossFade().into(holder.artwork);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
