package hr.murielkamgang.xmdb.components.base;

/**
 * Created by muriel on 03/3/18.
 */

public interface AdapterView extends BaseDialogView {

    void onUpdateChanged(int index, int length);

    void onUpdateInserted(int index, int length);

    void onUpdateRemoved(int index, int length);

    void notifyDataSetChanged();

    int indexOf(Object o);

    int adapterSize();

}
