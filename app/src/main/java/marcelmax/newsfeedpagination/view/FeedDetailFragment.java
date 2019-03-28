package marcelmax.newsfeedpagination.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import marcelmax.newsfeedpagination.R;
import marcelmax.newsfeedpagination.model.Article;

public class FeedDetailFragment extends Fragment {
    private Unbinder unbinder;
    private Article mArticle;
    @BindView(R.id.img_image)
    ImageView imageView;
    @BindView(R.id.tv_head)
    TextView textViewHead;
    @BindView(R.id.tv_body)
    TextView textViewBody;
    @BindView(R.id.ct_collapsing_toolbar)
    net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_author)
    TextView textViewAuthor;
    @BindView(R.id.tv_published)
    TextView textViewPublished;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_detail_screen, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        fillWithContent();

        return rootView;
    }

    private void fillWithContent() {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            mArticle = bundle.getParcelable("pass_article");
            Picasso.get()
                    .load(mArticle.getUrlToImage())
                    .into(imageView);
            textViewHead.setText(mArticle.getDescription());
            textViewBody.setText(mArticle.getContent());
            collapsingToolbarLayout.setTitle(mArticle.getTitle());
            textViewAuthor.setText(mArticle.getAuthor());
            textViewPublished.setText(formatDate(mArticle.getPublishedAt()));

        } else {
            Log.v("", "BUNDLE IS NULL");
        }
    }

    private String formatDate(String dateToFormat){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse(dateToFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat fmtOut = new SimpleDateFormat("dd.MM.yyyy");
        return fmtOut.format(date);
    }
}
