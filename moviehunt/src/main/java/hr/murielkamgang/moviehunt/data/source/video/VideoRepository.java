package hr.murielkamgang.moviehunt.data.source.video;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.data.model.video.Video;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.base.BaseLocalDataSource;
import hr.murielkamgang.moviehunt.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.moviehunt.data.source.base.BaseRepository;

/**
 * Created by muriel on 3/24/18.
 */
public class VideoRepository extends BaseRepository<Video, BaseKVH> {

    private final VideoLocalSource videoLocalSource;
    private final VideoRemoteSource videoRemoteSource;

    @Inject
    public VideoRepository(VideoLocalSource videoLocalSource, VideoRemoteSource videoRemoteSource) {
        this.videoLocalSource = videoLocalSource;
        this.videoRemoteSource = videoRemoteSource;
    }

    @Override
    public BaseLocalDataSource<Video, BaseKVH> getLocalDataSource() {
        return videoLocalSource;
    }

    @Override
    public BaseRemoteDataSource<Video, BaseKVH> getRemoteDataSource() {
        return videoRemoteSource;
    }
}
