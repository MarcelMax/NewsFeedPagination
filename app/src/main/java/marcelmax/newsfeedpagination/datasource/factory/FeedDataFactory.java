package marcelmax.newsfeedpagination.datasource.factory;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.datasource.FeedDataSource;

public class FeedDataFactory extends DataSource.Factory {
    private MutableLiveData<PageKeyedDataSource<Integer, Article>> articleLiveDataSource;
    private MutableLiveData<FeedDataSource> mutableLiveData;

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

}


