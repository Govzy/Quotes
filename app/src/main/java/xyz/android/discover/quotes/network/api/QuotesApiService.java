package xyz.android.discover.quotes.network.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import xyz.android.discover.quotes.network.model.QuotesResponse;

/**
 * Created by Govarthanan on 10/9/2017.
 */

//https://andruxnet-random-famous-quotes.p.mashape.com/?count=10&cat=fam (958ms)
public interface QuotesApiService {
    //@GET("?count=10&cat=fam")
    //Call<List<QuotesResponse>> getFamousQuotes();
    @Headers("X-Mashape-Key: Up7UTlqGmImshLt2ZUvpBFYIZ05Vp1buAsDjsneFUwVtFtbUQK")
    @GET("/")
    Call<List<QuotesResponse>> getFamousQuotes(@Query("count") String count, @Query("cat") String cat);
}
