package hacine.mohamed.abdelhakim.searchroommvvm;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class    BusListAdapter extends RecyclerView.Adapter<BusListAdapter.WordViewHolder> {




    class WordViewHolder extends RecyclerView.ViewHolder {

        // when click on item is shows or hides the information
        LinearLayout expandableLayout;
        LinearLayout statilayouttitle;

        private final TextView departtextview;
        private final TextView arrivetextview;
        private final TextView buslntextview;

        private WordViewHolder(View itemView) {

            super(itemView);
            departtextview = itemView.findViewById(R.id.dprt);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            statilayouttitle = itemView.findViewById(R.id.ltxt);
            arrivetextview =itemView.findViewById(R.id.arv);
            buslntextview=itemView.findViewById(R.id.busln);

            statilayouttitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bus bus = mBuses.get(getAdapterPosition());
                    bus.setExpanded(!bus.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<Bus> mBuses; // Cached copy of words

    BusListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.bus_item_row, parent, false);
        return new WordViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {

        if (mBuses != null) {
            Bus current = mBuses.get(position);
            holder.departtextview.setText(current.getDepart());
            holder.arrivetextview.setText(current.getArrive());
            holder.buslntextview.setText(current.getBusln());
            boolean isExpanded = mBuses.get(position).isExpanded();
            holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        } else {
            // Covers the case of data not being ready yet.

            holder.buslntextview.setText("No Bus");
            holder.departtextview.setText("No Bus");
            holder.arrivetextview.setText("No Bus");
        }
    }

    void setWords(List<Bus> buses) {
        mBuses = buses;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mBuses has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mBuses != null)
            return mBuses.size();
        else return 0;
    }
}


