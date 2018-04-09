package hr.murielkamgang.moviehunt.components.home;

import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseContentFragment;
import hr.murielkamgang.moviehunt.components.base.BaseToolBarActivity;
import hr.murielkamgang.moviehunt.components.base.GridSpacingItemDecoration;
import hr.murielkamgang.moviehunt.components.details.MovieDetailActivity;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;

/**
 * Created by muriel on 3/3/18.
 */
public class HomeFragment extends BaseContentFragment<Movie, HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    @Inject
    HomeContract.Presenter presenter;

    @Inject
    HomeAdapter homeAdapter;

    @Inject
    public HomeFragment() {
    }

    @Override
    protected void onItemClicked(Movie item) {
        MovieDetailActivity.view(getContext(), item);
    }

    @Override
    protected void initRecyclerView() {
        final int spanCount = getResources().getInteger(R.integer.span_count);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing), true));
    }

    @Override
    public void updateToolbarName(int nameResId) {
        ((BaseToolBarActivity) getActivity()).getToolbar().setTitle(nameResId);
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
            case R.id.actionFavorite:
                if (presenter != null) presenter.delegateFavorite();
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


}
