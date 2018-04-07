package hr.murielkamgang.xmdb.components.details.image;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import hr.murielkamgang.xmdb.R;
import hr.murielkamgang.xmdb.components.base.BaseRecyclerViewAdapter;
import hr.murielkamgang.xmdb.data.model.image.Image;
import hr.murielkamgang.xmdb.util.Utils;

/**
 * Created by muriel on 3/25/18.
 */
public class ImageAdapter extends BaseRecyclerViewAdapter<Image, BaseRecyclerViewAdapter.ItemBaseVH> {

    private Picasso picasso;

    @Inject
    public ImageAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public ItemBaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageVh(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    class ImageVh extends ItemBaseVH {

        @BindView(R.id.imageView)
        ImageView imageView;

        public ImageVh(View itemView) {
            super(itemView);
        }

        @OnClick(R.id.clickView)
        void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(ImageAdapter.this, this, getAdapterPosition());
            }
        }

        @Override
        protected void performBinding(Image image) {
            Utils.loadMovieBackDrop(imageView, picasso, image);
        }
    }
}
