package marcelmax.newsfeedpagination.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import marcelmax.newsfeedpagination.datasource.factory.FeedDataFactory;
import marcelmax.newsfeedpagination.model.Article;

public class NewsRepository {
    private static NewsRepository instance;
    private LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;
    private LiveData<PagedList<Article>> mArticlePagedList;


    // singleton pattern to ensure there is only one repo
    public static NewsRepository getInstance() {
        if (instance == null) {
            instance = new NewsRepository();
        }
        return instance;
    }

    /**
     * Step 1: getting an instance of the DataSourceFactory class
     * Step 2: We are initializing the network state liveData as well.
     * This will update the UI on the network changes that take place
     * Step 3: configure the PagedList.Config.
     * Step 4: initializing the pageList using the config we created
     * as well as the DatasourceFactory
     */
    public LiveData<PagedList<Article>> getArticles() {
        FeedDataFactory feedDataFactory = new FeedDataFactory();
        liveDataSource = feedDataFactory.getArticleLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build();
        return mArticlePagedList = (new LivePagedListBuilder(feedDataFactory, pagedListConfig)).build();
    }


}
