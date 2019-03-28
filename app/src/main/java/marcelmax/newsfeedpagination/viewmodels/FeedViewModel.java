package marcelmax.newsfeedpagination.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.datasource.factory.FeedDataFactory;

public class FeedViewModel extends ViewModel {
    private LiveData<PagedList<Article>> articlePagedList;
    private LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    //todo maybe add a repository
    //todo fragment -> viewmodel -> repo -> datasource


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
