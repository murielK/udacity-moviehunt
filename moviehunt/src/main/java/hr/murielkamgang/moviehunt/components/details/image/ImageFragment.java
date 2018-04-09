package hr.murielkamgang.moviehunt.components.details.image;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.AdapterItemDivider;
import hr.murielkamgang.moviehunt.components.base.BaseContentFragment;
import hr.murielkamgang.moviehunt.data.model.image.Image;

/**
 * Created by muriel on 3/25/18.
 */
public class ImageFragment extends BaseContentFragment<Image, ImageContract.View, ImageContract.Presenter> implements ImageContract.View {

    @Inject
    ImageContract.Presenter presenter;

    @Inject
    ImageAdapter imageAdapter;

    @Inject
    public ImageFragment() {
    }

    @Override
    protected ImageContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_recycler_view_info;
    }

    @Override
    protected void onItemClicked(Image item) {
        ImageViewerActivity.viewImage(getContext(), item);
    }

    @Override
    protected void initRecyclerView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new AdapterItemDivider(getContext(), getResources().getDimensionPixelOffset(R.dimen.recycler_view_spacing_2), AdapterItemDivider.ORIENTATION_HORIZONTAL));
        recyclerView.setAdapter(imageAdapter);
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
