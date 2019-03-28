package marcelmax.newsfeedpagination.service;

import marcelmax.newsfeedpagination.model.FeedDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IFeedDataService {

    @GET("/v2/everything")
    Call<FeedDBResponse> fetchFeed(@Query("apiKey") String apiKey,
                                   @Query("q") String q,
                                   @Query("language") String language);

    /**
     *
     * @param apiKey = the API Key
     * @param q = the query parameter
     * @param language = language in which the article should be retrieved.
     * @param page = initial page
     * @param pageSize = how much data should be preloaded
     * @return
     */
    @GET("/v2/everything")
    Call<FeedDBResponse> fetchFeeds(@Query("apiKey") String apiKey,
                                   @Query("q") String q,
                                   @Query("language") String language,
                                   @Query("page") int page,
                                   @Query("pageSize") int pageSize);

}
