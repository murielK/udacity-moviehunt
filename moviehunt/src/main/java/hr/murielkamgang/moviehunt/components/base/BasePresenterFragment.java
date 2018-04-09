package hr.murielkamgang.moviehunt.components.base;

/**
 * Created by muriel on 03/3/18.
 */

/**
 * Base class to help eliminate all the boiler plate code for anything that implement basePresenter
 *
 * @param <P> the presenter
 */
public abstract class BasePresenterFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseFragment {

    /**
     * Provide presenter of this
     *
     * @return the current presenter of this View
     */
    protected abstract P providePresenter();

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        BasePresenter p;
        if ((p = providePresenter()) != null) {
            p.onDestroy();
        }
    }
}
