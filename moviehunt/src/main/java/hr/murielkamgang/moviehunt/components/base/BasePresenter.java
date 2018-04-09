package hr.murielkamgang.moviehunt.components.base;

/**
 * Created by muriel on 03/3/18.
 */

/**
 * Base presenter for this project
 */
public interface BasePresenter<V extends BaseView> {

    /**
     * Notify the presenter when the View is destroyed
     */
    void onDestroy();

    /**
     * Pass the view to the presenter
     */
    void setView(V v);

}
