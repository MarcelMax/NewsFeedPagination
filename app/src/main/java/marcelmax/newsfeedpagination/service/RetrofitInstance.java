package marcelmax.newsfeedpagination.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static String BASE_URL = "https://newsapi.org";

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static IFeedDataService feedApi = retrofit.create(IFeedDataService.class);

    public static IFeedDataService fetchFeedApi(){
        return feedApi;
    }

}
