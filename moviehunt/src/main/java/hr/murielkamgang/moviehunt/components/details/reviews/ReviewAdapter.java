package hr.murielkamgang.moviehunt.components.details.reviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.moviehunt.data.model.review.Review;

/**
 * Created by muriel on 3/25/18.
 */
public class ReviewAdapter extends BaseRecyclerViewAdapter<Review, BaseRecyclerViewAdapter.ItemBaseVH> {

    @Override
    public ItemBaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false));
    }

    class ReviewVh extends ItemBaseVH {

        @BindView(R.id.textViewAuthor)
        TextView textViewAuthor;

        @BindView(R.id.textViewContent)
        TextView textViewContent;

        ReviewVh(View itemView) {
            super(itemView);
        }

        @Override
        protected void performBinding(Review review) {
            textViewAuthor.setText(review.getAuthor());
            textViewContent.setText(textViewContent.getContext().getResources().getString(R.string.quote_content_wrapper, review.getContent()));

        }
    }
}
