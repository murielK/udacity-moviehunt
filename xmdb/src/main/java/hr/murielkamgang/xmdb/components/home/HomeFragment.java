package hr.murielkamgang.xmdb.components.home;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseDialogFragment;
import hr.murielkamgang.xmdb.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.xmdb.components.base.BaseToolBarActivity;
import hr.murielkamgang.xmdb.components.base.GridSpacingItemDecoration;
import hr.murielkamgang.xmdb.components.details.MovieDetailActivity;
import hr.murielkamgang.xmdb.components.di.ActivityScoped;
import hr.murielkamgang.xmdb.data.model.movie.Movie;

/**
 * Created by muriel on 3/3/18.
 */

@ActivityScoped
public class HomeFragment extends BaseDialogFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    @Inject
    HomeContract.Presenter presenter;

    @Inject
    HomeAdapter homeAdapter;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void onPostViewCreate(View view) {
        final int spanCount = getResources().getInteger(R.integer.span_count);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.home_grid_spacing), true));
        homeAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, int position) {
                final Movie movie = (Movie) adapter.getItemAt(position);
                MovieDetailActivity.view(getContext(), movie);
            }
        });

        final Resources res = getResources();
        swipeRefreshLayout.setColorSchemeColors(res.getColor(R.color.colorPrimary),
                res.getColor(R.color.colorSecondaryInactive));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (presenter != null) {
                presenter.load();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.actionPopular:
                if (presenter != null) presenter.delegatePopular();
                return true;
            case R.id.actionTopRating:
                if (presenter != null) presenter.delegateTopRating();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected HomeContract.Presenter providePresenter() {
        return presenter;
    }

    @Override
    protected int getResourceView() {
        return R.layout.fragment_home;
    }

    @Override
    public void onItemLoaded(List<Movie> movies) {
        homeAdapter.setItems(movies);
    }

    @Override
    public void swipeToRefresh(boolean refreshing) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(refreshing);
        }
    }

    @Override
    public void updateToolbarName(int nameResId) {
        ((BaseToolBarActivity) getActivity()).getToolbar().setTitle(nameResId);
    }

    @Override
    public void onUpdateChanged(int index, int length) {
        homeAdapter.notifyItemRangeChanged(index, length);
    }

    @Override
    public void onUpdateInserted(int index, int length) {
        homeAdapter.notifyItemRangeInserted(index, length);
    }

    @Override
    public void onUpdateRemoved(int index, int length) {
        homeAdapter.notifyItemRangeRemoved(index, length);
    }

    @Override
    public void notifyDataSetChanged() {
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public int indexOf(Object o) {
        return -1; // for now
    }

    @Override
    public int adapterSize() {
        return -1; // for now
    }
}
