package marcelmax.newsfeedpagination.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.datasource.factory.FeedDataFactory;
import marcelmax.newsfeedpagination.repository.NewsRepository;

public class FeedViewModel extends ViewModel {
    private LiveData<PagedList<Article>> mArticlePagedList;
    private NewsRepository mNewsRepository;


    public FeedViewModel() {
     init();
    }

    private void init() {
        if (this.mArticlePagedList == null){
            mNewsRepository = NewsRepository.getInstance();

            mArticlePagedList = mNewsRepository.getArticles();
        }
    }

    public LiveData<PagedList<Article>> getArticlePagedList() {
        return mArticlePagedList;
    }
}
