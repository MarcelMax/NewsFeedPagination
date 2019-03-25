package marcelmax.newsfeedpagination.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.repository.factory.FeedDataFactory;

public class FeedViewModel extends ViewModel {
    private LiveData<PagedList<Article>> articlePagedList;
    private LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    /*
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Article>> articleLiveData;
    private Executor executor;
    private AppController appController;
*/

    public FeedViewModel() {
        init();
    }


    private void init() {
        FeedDataFactory feedDataFactory = new FeedDataFactory();
        liveDataSource = feedDataFactory.getArticleLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build();

        articlePagedList = (new LivePagedListBuilder(feedDataFactory,pagedListConfig))
                .build();
    }

    public LiveData<PagedList<Article>> getArticlePagedList() {
        return articlePagedList;
    }
}