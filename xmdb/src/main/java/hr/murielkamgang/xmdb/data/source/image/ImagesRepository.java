package hr.murielkamgang.xmdb.data.source.image;

import javax.inject.Inject;

import hr.murielkamgang.xmdb.data.model.image.Image;
import hr.murielkamgang.xmdb.data.source.base.BaseKVH;
import hr.murielkamgang.xmdb.data.source.base.BaseLocalDataSource;
import hr.murielkamgang.xmdb.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.xmdb.data.source.base.BaseRepository;

/**
 * Created by muriel on 3/25/18.
 */
public class ImagesRepository extends BaseRepository<Image, BaseKVH> {

    private final ImageLocalSource imageLocalSource;
    private final ImageRemoteSource imageRemoteSource;

    @Inject
    public ImagesRepository(ImageLocalSource imageLocalSource, ImageRemoteSource imageRemoteSource) {
        this.imageLocalSource = imageLocalSource;
        this.imageRemoteSource = imageRemoteSource;
    }

    @Override
    public BaseLocalDataSource<Image, BaseKVH> getLocalDataSource() {
        return imageLocalSource;
    }

    @Override
    public BaseRemoteDataSource<Image, BaseKVH> getRemoteDataSource() {
        return imageRemoteSource;
    }
}
