package hr.murielkamgang.moviehunt.components.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by muriel on 03/3/18.
 */

/**
 * Implementation of the BaseDialogView
 *
 * @param <P> the presenter
 */
public abstract class BaseDialogFragment<V extends BaseView, P extends BasePresenter<V>> extends BasePresenterFragment<V, P> implements BaseDialogView {

    private ProgressDialog progressDialog;
    private Toast toast;

    /**
     * {@inheritDoc}
     */
    @Override
    public void showDialog(String msg, boolean cancelable) {
        if (!isDetached() && progressDialog != null) {
            progressDialog.setCancelable(cancelable);
            progressDialog.setMessage(msg);
            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showDialog(int msgResId, boolean cancelable) {
        Context context;
        if (!isDetached() && progressDialog != null && (context = getContext()) != null) {
            progressDialog.setCancelable(cancelable);
            progressDialog.setMessage(context.getResources().getString(msgResId));

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toast(int msgResId) {
        Context context;
        if (!isDetached() && (context = getContext()) != null) {
            if (toast != null) {
                toast.cancel();
            }

            toast = Toast.makeText(getContext(), context.getResources().getString(msgResId), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissDialog();
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
        toast = null;
    }
}
