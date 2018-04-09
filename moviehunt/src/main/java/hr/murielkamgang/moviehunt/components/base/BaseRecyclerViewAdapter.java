package hr.murielkamgang.moviehunt.components.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by muriel on 3/4/18.
 */

/**
 * Base recyclerViewAdapter with support for multi and single selection
 */
public abstract class BaseRecyclerViewAdapter<T, VH extends BaseRecyclerViewAdapter.BaseVH> extends RecyclerView.Adapter<VH> implements SelectableAdapter, SelectableAdapter.SelectableAdapterCallback {

    /**
     * The selectable adapter
     */
    private final SelectableAdapter selectableAdapter;

    /**
     * Listener for item click events
     */
    protected OnItemClickListener listener;

    /**
     * List of items of this adapter
     */
    private List<T> items;

    protected BaseRecyclerViewAdapter() {
        selectableAdapter = new SelectableAdapterImp(this);
    }

    /**
     * Set the listener
     *
     * @param listener the listener to register
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void toggleMultiSelection(int position) {
        selectableAdapter.toggleMultiSelection(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleSingleSelection(int position) {
        selectableAdapter.toggleSingleSelection(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearSelections() {
        selectableAdapter.clearSelections();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedCount() {
        return selectableAdapter.getSelectedCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSelected(int position) {
        return selectableAdapter.isSelected(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SparseBooleanArray getSelectedIds() {
        return selectableAdapter.getSelectedIds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyMultiSelection(int position) {
        notifyItemChanged(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifySingleSelection(int position) {
        notifyItemChanged(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyClearSelection() {
        notifyDataSetChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.doBind(position, payloads);
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        vh.doBind(i);
    }

    /**
     * {@inheritDoc}
     */


    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public T getItemAt(int index) {
        try {
            if (items != null) {
                return items.get(index);
            }
        } catch (IndexOutOfBoundsException e) {
            //Ignore
        }

        return null;
    }

    /**
     * The callback to be register on {@link BaseRecyclerViewAdapter} in order to invoke row item click events
     */
    public abstract static class OnItemClickListener {

        /**
         * Invoke when a View is click
         *
         * @param adapter    the adapter
         * @param viewHolder the row/item viewHolder
         * @param view       the view that was clicked
         * @param position   the position of the view in the item List
         */
        public void onViewClick(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, View view, int position) {

        }

        /**
         * Invoke when a View is LongClick
         *
         * @param adapter    the adapter
         * @param viewHolder the row/item viewHolder
         * @param view       the view that was longClicked
         * @param position   the position of the view in the item List
         */
        public void onViewLongClick(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, View view, int position) {

        }

        /**
         * Invoke when an item is click
         *
         * @param adapter    the adapter
         * @param viewHolder the row/item viewHolder
         * @param position   the position of the item
         */
        public void onItemClick(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, int position) {

        }

        /**
         * Invoke when an item is LongClick
         *
         * @param adapter    the adapter
         * @param viewHolder the row/item viewHolder
         * @param position   the position of the item
         */
        public void onItemLongClick(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, int position) {

        }
    }

    /**
     * Created by muriel on 9/13/17.
     */
    public abstract class ItemBaseVH extends BaseVH {

        public ItemBaseVH(View itemView) {
            super(itemView);
        }

        @Override
        protected void doBind(int position) {
            performBinding(getItemAt(position));
        }

        @Override
        protected void doBind(int position, List<Object> payload) {
            performBinding(getItemAt(position), payload);
        }

        protected abstract void performBinding(T t);

        protected void performBinding(T t, List<Object> payload) {

        }

    }

    public abstract class BaseVH extends RecyclerView.ViewHolder {

        public BaseVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected abstract void doBind(int position);

        protected void doBind(int position, List<Object> payload) {

        }
    }
}