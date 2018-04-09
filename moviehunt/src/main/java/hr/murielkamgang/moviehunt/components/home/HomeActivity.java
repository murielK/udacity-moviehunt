package hr.murielkamgang.moviehunt.components.home;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.Lazy;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseFragment;
import hr.murielkamgang.moviehunt.components.base.BaseToolBarActivity;

/**
 * Created by muriel on 3/3/18.
 */

public class HomeActivity extends BaseToolBarActivity {

    @Inject
    Lazy<HomeFragment> homeFragmentLazy;

    @Override
    protected int provideLayoutRes() {
        return R.layout.activity_base_toolbar;
    }

    @Override
    protected BaseFragment provideFragment() {
        final HomeFragment hf = homeFragmentLazy.get();
        hf.setHasOptionsMenu(true);
        return hf;
    }

    @Override
    protected int provideToolbarTitleResId() {
        return 0;
    }

    @Override
    protected Class<? extends AppCompatActivity> provideParentActivityClass() {
        return null;
    }
}
