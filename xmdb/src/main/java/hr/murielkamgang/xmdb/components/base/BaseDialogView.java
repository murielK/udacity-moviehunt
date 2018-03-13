package hr.murielkamgang.xmdb.components.base;

/**
 * Created by muriel on 03/3/18.
 */

/**
 * Base dialogView, basically to avoid boiler place just extend this interface to get basic Android dial componnent
 */
public interface BaseDialogView extends BaseView {

    /**
     * Show dialog
     *
     * @param msgResId   the resource id of this dialog message
     * @param cancelable false if you want the user no to cancelToPreferences the dialog
     */
    void showDialog(int msgResId, boolean cancelable);

    /**
     * Show dialog
     *
     * @param msg        the dialog msg
     * @param cancelable false if you want the user no to cancelToPreferences the dialog
     */
    void showDialog(String msg, boolean cancelable);

    /**
     * Dismiss the previously created dialog
     * <p>
     * <p>Note that implementation of this interface might decide whether or not an exception should be thrown in case
     * dialog were not previously created</p>
     */
    void dismissDialog();

    /**
     * Toast a message on the view
     *
     * @param msgResId the resource id of this toast string message
     */
    void toast(int msgResId);

    /**
     * Toast a message on the view
     *
     * @param msgResId the message to be displayed
     */
    void toast(String msgResId);

}