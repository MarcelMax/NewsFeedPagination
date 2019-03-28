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

public class MainAdapter extends PagedListAdapter<Article, MainAdapter.ArticleViewHolder> {


    private Context mContext;

    public MainAdapter(Context context) {
        //super(Article.DIFF_CALLBACK);
        super(DIFF_CALLBACK);
        this.mContext = context;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.feed_list_item, viewGroup, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder viewHolder, int i) {

            Article article = getItem(i);

            if(article != null){
                viewHolder.feedTitle.setText(article.getTitle());
                Picasso.get()
                        .load(article.getUrlToImage())
                        .into(viewHolder.feedImage);
            }



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
