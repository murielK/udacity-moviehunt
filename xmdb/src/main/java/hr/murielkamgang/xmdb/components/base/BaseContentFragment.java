package hr.murielkamgang.xmdb.components.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import hr.murielkamgang.xmdb.R;

/**
 * Created by muriel on 3/24/18.
 */
public abstract class BaseContentFragment<T, V extends BaseContentListContract.View<T>, P extends BaseContentListContract.Presenter<V>> extends BaseDialogFragment<V, P>
        implements BaseContentListContract.View<T> {

    @Nullable
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @Nullable
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private BaseRecyclerViewAdapter adapter;

    protected abstract void onItemClicked(T item);

    protected abstract void initRecyclerView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final P p = providePresenter();
        if (p != null) {
            p.setView((V) this);
        }
    }

    @Override
    protected void onPostViewCreate(View view) {
        final Resources res = getResources();

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setColorSchemeColors(res.getColor(R.color.colorPrimary),
                    res.getColor(R.color.colorSecondaryInactive));

            final P p = providePresenter();
            swipeRefreshLayout.setOnRefreshListener(() -> {
                if (p != null) {
                    p.load();
                }
            });
        }

        initRecyclerView();

        if (recyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {
            final BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
            this.adapter = adapter;
            this.adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseRecyclerViewAdapter adapter, RecyclerView.ViewHolder viewHolder, int position) {
                    onItemClicked((T) adapter.getItemAt(position));
                }
            });
        }
    }

    @Override
    public void onUpdateChanged(int index, int length) {
        if (adapter != null) {
            adapter.notifyItemRangeChanged(index, length);
        }
    }

    @Override
    public void onUpdateInserted(int index, int length) {
        if (adapter != null) {
            adapter.notifyItemRangeInserted(index, length);
        }
    }

    @Override
    public void onUpdateRemoved(int index, int length) {
        if (adapter != null) {
            adapter.notifyItemRangeRemoved(index, length);
        }
    }

    @Override
    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int adapterSize() {
        if (adapter != null) {
            return adapter.getItemCount();
        }

        return 0;
    }

    @Override
    public void onLoaded(List<T> ts) {
        if (adapter != null) {
            adapter.setItems(ts);
        }
    }

    @Override
    public void swipeToRefresh(boolean show) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(show);
        }
    }

}
