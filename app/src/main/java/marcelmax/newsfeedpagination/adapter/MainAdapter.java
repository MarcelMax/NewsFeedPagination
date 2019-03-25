package marcelmax.newsfeedpagination.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import marcelmax.newsfeedpagination.R;
import marcelmax.newsfeedpagination.model.Article;
import marcelmax.newsfeedpagination.model.ViewType;

public class MainAdapter extends PagedListAdapter<Article,RecyclerView.ViewHolder> {


    private static final int ARTICLE_TYPE = 1;
    private static final int PROGRESS_TYPE = 2;

    private ArrayList<Article> mArticles;
    private List<ViewType> mViewTypes;

    private Context mContext;

    public MainAdapter(Context context) {
        //super(Article.DIFF_CALLBACK);
        super(DIFF_CALLBACK);
        this.mContext = context;
    }

    private static DiffUtil.ItemCallback<Article> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Article>() {
                @Override
                public boolean areItemsTheSame(Article oldItem, Article newItem) {
                    return oldItem.getTitle() == newItem.getTitle();
                }

                @Override
                public boolean areContentsTheSame(Article oldItem, Article newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public void setViewTypeList(List<? extends ViewType> viewTypeList) {

        if (mViewTypes == null) {
            mViewTypes = new ArrayList<>();
        }
        mViewTypes.clear();
        mViewTypes.addAll(viewTypeList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {

            case PROGRESS_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_list_network_state, viewGroup, false);
                return new ArticleViewHolder(view);
            }
            /*
            case ARTICLE_TYPE: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_list_item, viewGroup, false);
                return new ArticleViewHolder(view);
            }
            */
            default: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_list_item, viewGroup, false);
                return new ArticleViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);

        if (itemViewType == PROGRESS_TYPE) {

        }
        else if (itemViewType == ARTICLE_TYPE){
            if (mArticles == null){
                mArticles = new ArrayList<>();
            }

            for (ViewType source : mViewTypes){
                mArticles.add((Article) source);
            }

            Article article = mArticles.get(i);
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
            articleViewHolder.feedTitle.setText(article.getTitle());
            Picasso.get()
                    .load(article.getUrlToImage())
                    .into(articleViewHolder.feedImage);

        }
    }



    @Override
    public int getItemViewType(int position) {
        return mViewTypes.get(position).getType();
    }

    @Override
    public int getItemCount() {
        if (mViewTypes == null) {
            return 0;
        } else {
            return mViewTypes.size();
        }
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_feed_title)
        TextView feedTitle;
        @BindView(R.id.img_feed_image)
        ImageView feedImage;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
