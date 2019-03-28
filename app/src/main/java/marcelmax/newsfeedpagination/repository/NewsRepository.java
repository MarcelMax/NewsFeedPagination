package marcelmax.newsfeedpagination.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PageKeyedDataSource;

import marcelmax.newsfeedpagination.model.Article;

public class NewsRepository {

    private LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

}
