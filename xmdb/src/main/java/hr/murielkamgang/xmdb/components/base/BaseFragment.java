package hr.murielkamgang.xmdb.components.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * Created by muriel on 03/3/18.
 */

/**
 * Base fragment for this project, it will handle view Binding and view unbinding efficiently.
 */
public abstract class BaseFragment extends DaggerFragment {

    private Unbinder unbinder;

    /**
     * Provide the resource layout for this fragment
     *
     * @return resource layout id
     */
    protected abstract int getResourceView();

    /**
     * In case there is a need for additional implementation when view is created, just override this!
     *
     * @param view the newly created and inflated view
     */
    protected void onPostViewCreate(View view) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final int layoutId = getResourceView();
        if (layoutId <= 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        final View v = inflater.inflate(layoutId, null);
        unbinder = ButterKnife.bind(this, v);
        // do whatever you like with this view but be careful of it's lifecycle.
        onPostViewCreate(v);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }
}
