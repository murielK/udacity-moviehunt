package hr.murielkamgang.moviehunt.components.details.trailers;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.AdapterItemDivider;
import hr.murielkamgang.moviehunt.components.base.BaseContentFragment;
import hr.murielkamgang.moviehunt.data.model.video.Video;
import hr.murielkamgang.moviehunt.util.Utils;

/**
 * Created by muriel on 3/24/18.
 */
public class TrailerFragment extends BaseContentFragment<Video, TrailerContract.View, TrailerContract.Presenter> {

    @Inject
    TrailerContract.Presenter presenter;

    @Inject
    TrailerAdapter trailerAdapter;

    @Inject
    public TrailerFragment() {
    }

    @Override
    protected TrailerContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_recycler_view_info;
    }

    @Override
    protected void onItemClicked(Video item) {
        Utils.watchYoutubeVideo(getContext(), item.getKey());
    }

    @Override
    protected void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new AdapterItemDivider(getContext(), getResources().getDimensionPixelOffset(R.dimen.recycler_view_spacing_2), AdapterItemDivider.ORIENTATION_HORIZONTAL));
        recyclerView.setAdapter(trailerAdapter);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onPostViewCreate(View view) {
        super.onPostViewCreate(view);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(false);
        }
    }
}
