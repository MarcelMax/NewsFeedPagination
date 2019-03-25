package marcelmax.newsfeedpagination.repository.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.repository.FeedDataSource;

public class FeedDataFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Article>> articleLiveDataSource;
    private MutableLiveData<FeedDataSource> mutableLiveData;

    public FeedDataFactory() {
      //  this.mutableLiveData = new MutableLiveData<FeedDataSource>();
      //  this.articleLiveDataSource = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        FeedDataSource feedDataSource = new FeedDataSource();
        articleLiveDataSource = new MutableLiveData<>();
        articleLiveDataSource.postValue(feedDataSource);
       // mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getArticleLiveDataSource() {
        return articleLiveDataSource;
    }

    public MutableLiveData<FeedDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}


