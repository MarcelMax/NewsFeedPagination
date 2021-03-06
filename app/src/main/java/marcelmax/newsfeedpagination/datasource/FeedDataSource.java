package marcelmax.newsfeedpagination.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import marcelmax.newsfeedpagination.Util.Constants;
import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.model.FeedDBResponse;
import marcelmax.newsfeedpagination.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDataSource extends PageKeyedDataSource<Integer, Article> {

    private static final String TAG = FeedDataSource.class.getSimpleName();
    private static final int FIRST_PAGE = 1; //we will start from the first page which is 1


    public Call<FeedDBResponse> getArticleResponse(String query, String language, int page, int pagesize) {
        return RetrofitInstance.fetchFeedApi().
                fetchFeeds(Constants.API_KEY, query, language, page, pagesize);
    }


    /**
     * Step 2: This method is responsible to load the data initially
     * when app screen is launched for the first time.
     * We are fetching the first page data from the api
     * and passing it via the callback method to the UI.
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Article> callback) {

        getArticleResponse(Constants.QUERY,
                Constants.LANGUAGE_EN,
                FIRST_PAGE,
                params.requestedLoadSize)
                .enqueue(new Callback<FeedDBResponse>() {

                    @Override
                    public void onResponse(Call<FeedDBResponse> call,
                                           Response<FeedDBResponse> response) {

                        if (response.body() != null) {
                            Log.v(TAG, "INITIAL" + response.body().getTotalResults().toString());
                            callback.onResult(response.body()
                                            .getArticles(),
                                    null,
                                    FIRST_PAGE + 1);
                        }

                    }

                    @Override
                    public void onFailure(Call<FeedDBResponse> call, Throwable t) {
                        Log.v("***ONFAIL", "ONFAIL CALL IS EXECUTED:\n " + call.isExecuted()
                                + "\n CALL REQUEST " + call.request()
                                + "\n THROWABLE " + t);
                    }

                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {
/*
        getArticleResponse(Constants.QUERY,
                Constants.LANGUAGE_DE,
                FIRST_PAGE,
                params.requestedLoadSize)
                .enqueue(new Callback<FeedDBResponse>() {
            @Override
            public void onResponse(Call<FeedDBResponse> call, Response<FeedDBResponse> response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

                if (response.body() != null) {
                    callback.onResult(response.body().getArticles(), adjacentKey);
                    Log.v(TAG,"BEFORE" + response.body().getArticles().toString());

                }

            }

            @Override
            public void onFailure(Call<FeedDBResponse> call, Throwable t) {
                Log.v("***ONFAIL", "ONFAIL CALL IS EXECUTED:\n " + call.isExecuted()
                        + "\n CALL REQUEST " + call.request()
                        + "\n THROWABLE " + t);
            }

        });
*/
    }

    /**
     * Step 3: This method it is responsible for the subsequent call to load the data page wise.
     * This method is executed in the background thread
     * We are fetching the next page data from the api
     * and passing it via the callback method to the UI.
     * The "params.key" variable will have the updated value.
     */
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params,
                          @NonNull final LoadCallback<Integer, Article> callback) {
        Log.i(TAG, "Loading Rang " + params.key + " Count " + params.requestedLoadSize);

        getArticleResponse(Constants.QUERY,
                Constants.LANGUAGE_EN,
                params.key,
                params.requestedLoadSize)
                .enqueue(new Callback<FeedDBResponse>() {

                    @Override
                    public void onResponse(Call<FeedDBResponse> call,
                                           Response<FeedDBResponse> response) {
                        if (response.body() != null) {
                            /**
                             * If the request is successful, then we will update the callback
                             * with the latest feed items and
                             * "params.key+1" -> set the next key for the next iteration.
                             */
                            int nextKey = (params.key == response.body().getTotalResults()) ? null : params.key + 1;

                            callback.onResult(response.body()
                                            .getArticles(),
                                    nextKey);
                            Log.v(TAG, "AFTER " + params.key + ", " + nextKey);

                            Log.v(TAG, "AFTER BODY " + response.body().getTotalResults().toString());

                        }

                    }

                    @Override
                    public void onFailure(Call<FeedDBResponse> call, Throwable t) {
                        Log.v("***ONFAIL", "ONFAIL CALL IS EXECUTED:\n " + call.isExecuted()
                                + "\n CALL REQUEST " + call.request()
                                + "\n THROWABLE " + t);
                    }

                });

    }
}
