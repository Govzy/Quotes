package xyz.android.discover.quotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.android.discover.quotes.network.model.QuotesResponse;

/**
 * Created by Govarthanan on 10/10/2017.
 */

public class QuotesCardViewAdapter extends RecyclerView.Adapter<QuotesCardViewAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView quoteText;
        public TextView authorText;

        public MyViewHolder(View view) {
            super(view);
            quoteText = view.findViewById(R.id.quote_text);
            authorText = view.findViewById(R.id.author_text);
        }
    }
    private Context mContext;
    private List<QuotesResponse> quotesResponseList;
    public QuotesCardViewAdapter(Context context, List<QuotesResponse> quotesResponseList) {
        this.mContext = context;
        this.quotesResponseList = quotesResponseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_card_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuotesResponse quotesResponse = quotesResponseList.get(position);
        holder.quoteText.setText(quotesResponse.getQuote());
        holder.authorText.setText(quotesResponse.getAuthor());
    }

    @Override
    public int getItemCount() {
        return quotesResponseList.size();
    }

    public void updateList(List<QuotesResponse> updateQuoteResponseList){
        quotesResponseList.addAll(updateQuoteResponseList);
    }
}
