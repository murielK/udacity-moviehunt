package hr.murielkamgang.moviehunt.components.details.info;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.murielkamgang.moviehunt.R;
import hr.murielkamgang.moviehunt.components.di.ActivityScoped;
import hr.murielkamgang.moviehunt.data.model.credits.People;
import hr.murielkamgang.moviehunt.util.Utils;

/**
 * Created by muriel on 3/24/18.
 */
@ActivityScoped
class PeopleBinder {

    Picasso picasso;

    @BindView(R.id.imageViewPeople)
    ImageView imageViewPeople;

    @BindView(R.id.textViewPeopleName)
    TextView textViewPeopleName;

    @Inject
    public PeopleBinder(Picasso picasso) {
        this.picasso = picasso;
    }

    public void initTarget(View targetView) {
        ButterKnife.bind(this, targetView);
    }

    public void bind(People people) {
        if (!isInit()) {
            throw new NullPointerException("view might have not been initialized");
        }

        if (people != null) {
            textViewPeopleName.setText(people.getName());
            Utils.loadPeople(imageViewPeople, picasso, people);
        }
    }

    public boolean isInit() {
        return imageViewPeople != null && textViewPeopleName != null;
    }

}
