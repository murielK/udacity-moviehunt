package hr.murielkamgang.moviehunt.components.details.image;

import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.components.base.BaseContentListPresenter;
import hr.murielkamgang.moviehunt.data.model.image.Image;
import hr.murielkamgang.moviehunt.data.source.Repository;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import io.reactivex.Observable;

/**
 * Created by muriel on 3/25/18.
 */
public class ImagePresenter extends BaseContentListPresenter<Image, ImageContract.View> implements ImageContract.Presenter {

    private final Repository<Image, BaseKVH> imageRepository;
    private final int movieId;

    @Inject
    public ImagePresenter(Repository<Image, BaseKVH> imageRepository, int movieId) {
        this.imageRepository = imageRepository;
        this.movieId = movieId;
    }

    @Override
    protected Observable<List<Image>> provideLoadObservable(boolean sync) {
        return imageRepository.getAllDataAsObservable(new BaseKVH("movieId", movieId), sync);
    }
}
