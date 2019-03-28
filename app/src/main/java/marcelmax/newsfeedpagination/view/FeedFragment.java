package marcelmax.newsfeedpagination.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marcelmax.newsfeedpagination.R;
import marcelmax.newsfeedpagination.adapter.MainAdapter;
import marcelmax.newsfeedpagination.viewmodels.FeedViewModel;

public class FeedFragment extends Fragment {

    @BindView(R.id.rv_list_feed)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private FeedViewModel mFeedViewModel;
    private MainAdapter mainAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        mFeedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        subscribeObservers();
        return rootView;
    }

    private void subscribeObservers() {
        /*
        mFeedViewModel.getArticle().observe(getViewLifecycleOwner(), new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                if (articles != null){
                    prepareRecyclerViewMain();
                    mainAdapter.setViewTypeList(articles);
                    Log.v("","ARTICLES " + articles);
                }
            }
        });


        mFeedViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), articles -> {
            prepareRecyclerViewMain();
            mainAdapter.submitList(articles);
            mainAdapter.setViewTypeList(articles);
        });
        */

        mFeedViewModel.getArticlePagedList().observe(getViewLifecycleOwner(), articles -> {
            prepareRecyclerViewMain();
         //   mainAdapter.setViewTypeList(articles);
            mainAdapter.submitList(articles);

        });

    }

    private void prepareRecyclerViewMain() {
        mainAdapter = new MainAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainAdapter);

    }
}
