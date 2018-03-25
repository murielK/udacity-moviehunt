package hr.murielkamgang.xmdb.components.details.trailers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.xmdb.data.model.video.Video;
import hr.murielkamgang.xmdb.util.Utils;

/**
 * Created by muriel on 3/24/18.
 */
public class TrailerAdapter extends BaseRecyclerViewAdapter<Video, BaseRecyclerViewAdapter.ItemBaseVH> {

    Picasso picasso;

    public TrailerAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public ItemBaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrailerVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false));
    }

    class TrailerVH extends ItemBaseVH {

        @BindView(R.id.imageViewTrailer)
        ImageView imageViewTrailer;

        @BindView(R.id.textViewTrailerName)
        TextView textViewTrailerName;

        TrailerVH(View itemView) {
            super(itemView);
        }

        @OnClick(R.id.imageViewClick)
        void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(TrailerAdapter.this, this, getAdapterPosition());
            }
        }

        @Override
        protected void performBinding(Video video) {
            textViewTrailerName.setText(video.getName());
            Utils.loadVideo(imageViewTrailer, picasso, video);
        }
    }
}
