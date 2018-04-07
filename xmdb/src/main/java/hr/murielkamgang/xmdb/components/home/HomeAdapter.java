package hr.murielkamgang.xmdb.components.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.xmdb.data.model.movie.Movie;
import hr.murielkamgang.xmdb.util.Utils;

/**
 * Created by muriel on 3/4/18.
 */
class HomeAdapter extends BaseRecyclerViewAdapter<Movie, BaseRecyclerViewAdapter.ItemBaseVH> {

    private final Picasso picasso;

    HomeAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public ItemBaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    class MovieVH extends ItemBaseVH {

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.clickView)
        View view;

        MovieVH(View itemView) {
            super(itemView);
            view.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(HomeAdapter.this, MovieVH.this, getAdapterPosition());
                }
            });
        }

        @Override
        protected void performBinding(Movie movie) {
            Utils.loadMoviePoster(imageView, picasso, movie);
        }
    }
}
