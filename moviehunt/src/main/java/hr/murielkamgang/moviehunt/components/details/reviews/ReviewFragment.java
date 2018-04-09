package hr.murielkamgang.moviehunt.components.details.reviews;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.AdapterItemDivider;
import hr.murielkamgang.moviehunt.components.base.BaseContentFragment;
import hr.murielkamgang.moviehunt.data.model.review.Review;

/**
 * Created by muriel on 3/25/18.
 */
public class ReviewFragment extends BaseContentFragment<Review, ReviewContract.View, ReviewContract.Presenter> implements ReviewContract.View {

    @Inject
    ReviewContract.Presenter presenter;

    @Inject
    ReviewAdapter reviewAdapter;

    @Inject
    public ReviewFragment() {
    }

    @Override
    protected ReviewContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_recycler_view_info;
    }

    @Override
    protected void onItemClicked(Review item) {

    }

    @Override
    protected void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new AdapterItemDivider(getContext(), getResources().getDimensionPixelOffset(R.dimen.recycler_view_spacing_2), AdapterItemDivider.ORIENTATION_HORIZONTAL));
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setNestedScrollingEnabled(true);
    }

    @Override
    protected void onPostViewCreate(View view) {
        super.onPostViewCreate(view);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(false);
        }
    }
}
