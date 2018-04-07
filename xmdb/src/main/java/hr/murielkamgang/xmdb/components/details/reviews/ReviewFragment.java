package hr.murielkamgang.xmdb.components.details.reviews;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.AdapterItemDivider;
import hr.murielkamgang.xmdb.components.base.BaseContentFragment;
import hr.murielkamgang.xmdb.data.model.review.Review;

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
        return R.layout.fragment_default_recycler_view;
    }

    @Override
    protected void onItemClicked(Review item) {

    }

    @Override
    protected void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new AdapterItemDivider(getContext(), getResources().getDrawable(R.drawable.default_drawable_divider), AdapterItemDivider.ORIENTATION_VERTICAL));
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
    }

    @Override
    protected void onPostViewCreate(View view) {
        super.onPostViewCreate(view);
        swipeRefreshLayout.setEnabled(false);
    }
}
