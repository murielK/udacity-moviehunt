package hr.murielkamgang.xmdb.components.details.info;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.xmdb.data.model.credits.Cast;

/**
 * Created by muriel on 3/24/18.
 */
public class MovieInfoCastAdapter extends BaseRecyclerViewAdapter<Cast, BaseRecyclerViewAdapter.ItemBaseVH> {

    private final Picasso picasso;

    public MovieInfoCastAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public ItemBaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CastVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people, parent, false));
    }


    class CastVH extends ItemBaseVH {

        private final PeopleBinder peopleBinder;

        CastVH(View itemView) {
            super(itemView);
            peopleBinder = new PeopleBinder(picasso);
            peopleBinder.initTarget(itemView);
        }

        @Override
        protected void performBinding(Cast cast) {
            peopleBinder.bind(cast);
        }
    }
}
