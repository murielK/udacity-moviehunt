package hr.murielkamgang.moviehunt.components.base;

import android.util.SparseBooleanArray;

/**
 * Created by muriel on 3/4/18.
 */

/**
 * Implementation of the selectableAdapter interface
 */
class SelectableAdapterImp implements SelectableAdapter {

    /**
     * The adapter which should implement {@link SelectableAdapter.SelectableAdapterCallback}
     */
    private final SelectableAdapter.SelectableAdapterCallback adapter;

    /**
     * The spareBoolArray which will hold selection info
     */
    private final SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    /**
     * Package private constructor
     *
     * @param adapter The adapter which should implement {@link SelectableAdapter.SelectableAdapterCallback}
     */
    SelectableAdapterImp(SelectableAdapter.SelectableAdapterCallback adapter) {
        this.adapter = adapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleMultiSelection(int position) {
        selectView(position, !sparseBooleanArray.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleSingleSelection(int position) {
        final int oldSize = sparseBooleanArray.size();
        final int oldSelection = sparseBooleanArray.keyAt(0);

        sparseBooleanArray.clear();
        if (oldSelection != position && oldSize == 1) {
            adapter.notifySingleSelection(oldSelection);
        } else if (oldSize > 1) {
            adapter.notifyClearSelection();
        }
        sparseBooleanArray.put(position, true);
        adapter.notifySingleSelection(position);
    }

    /**
     * {@inheritDoc}
     */
    private void selectView(int position, boolean value) {
        if (value) {
            sparseBooleanArray.put(position, value);
        } else {
            sparseBooleanArray.delete(position);
        }
        adapter.notifyMultiSelection(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearSelections() {
        sparseBooleanArray.clear();
        adapter.notifyClearSelection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedCount() {
        return sparseBooleanArray.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelected(int position) {
        return sparseBooleanArray.get(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SparseBooleanArray getSelectedIds() {
        return sparseBooleanArray;
    }
}