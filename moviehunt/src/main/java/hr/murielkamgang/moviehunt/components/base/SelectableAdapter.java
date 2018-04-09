package hr.murielkamgang.moviehunt.components.base;

import android.util.SparseBooleanArray;

/**
 * Created by muriel on 3/4/18.
 */

/**
 * Helper interface to perform multiSelection or single selection and notify the list adapter
 */
interface SelectableAdapter {

    /**
     * Toggle a multiSelection, the row item of the adapter will be notify about the event
     *
     * @param position of the row
     */
    void toggleMultiSelection(int position);

    /**
     * Toggle a single selection, the row of this item will be notify and all other row will be clear if the were previously selected
     *
     * @param position of the row
     */
    void toggleSingleSelection(int position);

    /**
     * Clear all the selection
     */
    void clearSelections();

    /**
     * Get the count of selected items in the list
     *
     * @return the selection count
     */
    int getSelectedCount();

    /**
     * Check whether an item is selected
     *
     * @param position of the row
     * @return true if item is selected
     */
    boolean isSelected(int position);

    /**
     * Get the selected id/position of all the selected items in the list
     *
     * @return the spareBoolArray containing all the selected item info
     */
    SparseBooleanArray getSelectedIds();

    /**
     * Callback to be register {@link SelectableAdapter}
     */
    interface SelectableAdapterCallback {

        /**
         * Notify a multiSelection
         *
         * @param position of the row
         */
        void notifyMultiSelection(int position);

        /**
         * Notify a single selection
         *
         * @param position of the row
         */
        void notifySingleSelection(int position);

        /**
         * Notify a clear selection
         */
        void notifyClearSelection();

    }
}
