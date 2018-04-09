package hr.murielkamgang.moviehunt.components.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.moviehunt.data.model.movie.Movie;
import hr.murielkamgang.moviehunt.util.Utils;

/**
 * Created by muriel on 3/4/18.
 */
class HomeAdapter extends BaseRecyclerViewAdapter<Movie, BaseRecyclerViewAdapter.ItemBaseVH> {

    private final Logger logger = LoggerFactory.getLogger(HomeAdapter.class);
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
            logger.debug("Binding movie: {}", movie.getTitle());
            Utils.loadMoviePoster(imageView, picasso, movie);
        }
    }
}
