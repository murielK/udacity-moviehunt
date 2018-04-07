package hr.murielkamgang.xmdb.components.details.trailers;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.AdapterItemDivider;
import hr.murielkamgang.xmdb.components.base.BaseContentFragment;
import hr.murielkamgang.xmdb.data.model.video.Video;
import hr.murielkamgang.xmdb.util.Utils;

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
        return R.layout.fragment_trailer;
    }

    @Override
    protected void onItemClicked(Video item) {
        Utils.watchYoutubeVideo(getContext(), item.getKey());
    }

    @Override
    protected void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new AdapterItemDivider(getContext(), getResources().getDimensionPixelOffset(R.dimen.recycler_view_spacing_2), AdapterItemDivider.ORIENTATION_VERTICAL));
        recyclerView.setAdapter(trailerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onPostViewCreate(View view) {
        super.onPostViewCreate(view);
        swipeRefreshLayout.setEnabled(false);
    }
}
