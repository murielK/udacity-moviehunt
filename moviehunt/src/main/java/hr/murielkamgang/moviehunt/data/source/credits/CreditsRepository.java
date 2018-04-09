package hr.murielkamgang.moviehunt.data.source.credits;

import javax.inject.Inject;

import hr.murielkamgang.moviehunt.data.model.credits.Credits;
import hr.murielkamgang.moviehunt.data.source.base.BaseKVH;
import hr.murielkamgang.moviehunt.data.source.base.BaseLocalDataSource;
import hr.murielkamgang.moviehunt.data.source.base.BaseRemoteDataSource;
import hr.murielkamgang.moviehunt.data.source.base.BaseRepository;

/**
 * Created by muriel on 3/24/18.
 */
public class CreditsRepository extends BaseRepository<Credits, BaseKVH> {

    private final CreditsLocalSource creditsLocalSource;
    private final CreditsRemoteSource creditsRemoteSource;

    @Inject
    public CreditsRepository(CreditsLocalSource creditsLocalSource, CreditsRemoteSource creditsRemoteSource) {
        this.creditsLocalSource = creditsLocalSource;
        this.creditsRemoteSource = creditsRemoteSource;
    }

    @Override
    public BaseLocalDataSource<Credits, BaseKVH> getLocalDataSource() {
        return creditsLocalSource;
    }

    @Override
    public BaseRemoteDataSource<Credits, BaseKVH> getRemoteDataSource() {
        return creditsRemoteSource;
    }
}
