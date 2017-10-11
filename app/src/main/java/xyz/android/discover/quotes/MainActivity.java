package xyz.android.discover.quotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.android.discover.quotes.network.Access;
import xyz.android.discover.quotes.network.api.QuotesApiService;
import xyz.android.discover.quotes.network.model.QuotesResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BASE_URL = "https://andruxnet-random-famous-quotes.p.mashape.com/";
    private static final String QUERY_FAMOUS = "famous";
    private static final String QUERY_MOVIES = "movies";
    private Button mFamousQButton;
    private Button mMoviesQButton;
    private RecyclerView recyclerView;
    private LinearLayout buttonContainer;
    List<QuotesResponse> quotesResponseResponses = new ArrayList<>();
    QuotesCardViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFamousQButton = (Button) findViewById(R.id.famous_button);
        mMoviesQButton = (Button) findViewById(R.id.movie_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        buttonContainer = (LinearLayout) findViewById(R.id.button_container);

        mFamousQButton.setOnClickListener(this);
        mMoviesQButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.famous_button:
                makeApiCall("10", QUERY_FAMOUS);
                buttonContainer.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.movie_button:
                makeApiCall("10", QUERY_MOVIES);
                buttonContainer.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void makeApiCall(String count, String category) {
        final QuotesApiService quotesApiService = Access.getInstance().getQuotesApi(BASE_URL);
        Call<List<QuotesResponse>> call = quotesApiService.getFamousQuotes(count, category);
        call.enqueue(new Callback<List<QuotesResponse>>() {
            @Override
            public void onResponse(Call<List<QuotesResponse>> call, Response<List<QuotesResponse>> response) {
                if (response != null) {
                    quotesResponseResponses = response.body();
                    adapter = new QuotesCardViewAdapter(MainActivity.this,quotesResponseResponses);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<QuotesResponse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(buttonContainer.getVisibility()==View.VISIBLE){
            finish();
        }
        else if(buttonContainer.getVisibility()==View.GONE){
            buttonContainer.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}
