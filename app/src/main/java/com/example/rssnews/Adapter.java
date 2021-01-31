package com.example.rssnews;
import android.content.ClipData;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class Adapter extends FirebaseRecyclerAdapter<news, Adapter.newsViewholder> {

    private OnClickListener monClickListener;
    int selected_position = 0;
    public Adapter(@NonNull FirebaseRecyclerOptions<news> options, OnClickListener onClickListener) {
        super(options);
        this.monClickListener = onClickListener;
    }

    @Override
    protected void
    onBindViewHolder(@NonNull newsViewholder holder, int position, @NonNull news model) {
       // ClipData.Item item = items.get(position);

        // Here I am just highlighting the background
        holder.itemView.setBackgroundColor(selected_position == position ? Color.YELLOW : Color.WHITE);

        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.link.setText(model.getLink());
        holder.pubDate.setText(model.getPubDate());
    }

    @NonNull
    @Override
    public newsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news, parent, false);
        return new newsViewholder(view, monClickListener);
    }

    // Sub Class to create references of the views in Crad
    // view (here "news.xml")
    class newsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, description, link, pubDate;

        OnClickListener onClickListener;

        public newsViewholder(@NonNull View itemView, OnClickListener onClickListener) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            link = itemView.findViewById(R.id.link);
            pubDate = itemView.findViewById(R.id.pubDate);

            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            onClickListener.onClick(link.getText().toString());
        }
    }

    public interface OnClickListener{
        void onClick(String url);
    }
} // end class