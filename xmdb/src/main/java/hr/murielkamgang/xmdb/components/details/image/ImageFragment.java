package hr.murielkamgang.xmdb.components.details.image;

import android.support.v7.widget.GridLayoutManager;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseContentFragment;
import hr.murielkamgang.xmdb.components.base.GridSpacingItemDecoration;
import hr.murielkamgang.xmdb.data.model.image.Image;

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
        return R.layout.fragment_default_recycler_view;
    }

    @Override
    protected void onItemClicked(Image item) {
        ImageViewerActivity.viewImage(getContext(), item);
    }

    @Override
    protected void initRecyclerView() {
        final int spanCount = getResources().getInteger(R.integer.image_details_span_count);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
