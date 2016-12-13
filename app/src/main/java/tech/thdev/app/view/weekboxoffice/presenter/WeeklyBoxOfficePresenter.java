package tech.thdev.app.view.weekboxoffice.presenter;

import android.content.Context;
import android.text.TextUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import tech.thdev.app.BuildConfig;
import tech.thdev.app.R;
import tech.thdev.app.adapter.movie.adapter.model.BoxOfficeAdapterContract;
import tech.thdev.app.data.BoxOfficeItem;
import tech.thdev.app.data.MovieChartItem;
import tech.thdev.app.data.WeeklyBoxOfficeContainer;
import tech.thdev.app.data.source.MovieSearchRepository;
import tech.thdev.base.presenter.AbstractPresenter;

/**
 * Created by tae-hwan on 12/13/16.
 */

public class WeeklyBoxOfficePresenter extends AbstractPresenter<WeeklyBoxOfficeContract.View> implements WeeklyBoxOfficeContract.Presenter {

    private BoxOfficeAdapterContract.Model model;
    private BoxOfficeAdapterContract.View view;
    private MovieSearchRepository movieSearchRepository;


    @Override
    public void setSampleOneModel(BoxOfficeAdapterContract.Model model) {
        this.model = model;
    }

    @Override
    public void setSampleOneView(BoxOfficeAdapterContract.View view) {
        this.view = view;
    }

    @Override
    public void setMovieSearchRepository(MovieSearchRepository movieSearchRepository) {
        this.movieSearchRepository = movieSearchRepository;
    }

    @Override
    public void getWeeklyBoxOffice(final Context context, String targetDate) {
        if (TextUtils.isEmpty(BuildConfig.KOBIS_API_KEY)) {
            if (!getView().isFinishing()) {
                getView().loadFail(context.getString(R.string.api_key_not_fount_error));
            }
            return;
        }
        movieSearchRepository.getWeekBoxOffice(targetDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (getView().isFinishing()) {
                            getView().loadFail(context.getString(R.string.weekly_load_fail));
                        }
                    }
                })
                .subscribe(new Consumer<WeeklyBoxOfficeContainer>() {
                    @Override
                    public void accept(WeeklyBoxOfficeContainer boxOfficeContainer) throws Exception {
                        model.addItem(new MovieChartItem(
                                boxOfficeContainer.getBoxOfficeResult().getBoxofficeType(),
                                "",
                                boxOfficeContainer.getBoxOfficeResult().getShowRange(),
                                "",
                                "",
                                100));

                        Flowable.fromIterable(boxOfficeContainer.getBoxOfficeResult().getWeeklyBoxOfficeList())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        if (!getView().isFinishing()) {
                                            view.reload();
                                        }
                                    }
                                })
                                .doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        if (!getView().isFinishing()) {
                                            getView().loadFail(context.getString(R.string.weekly_load_fail));
                                        }
                                    }
                                })
                                .subscribe(new Consumer<BoxOfficeItem>() {
                                    @Override
                                    public void accept(BoxOfficeItem dailyBoxOffice) throws Exception {
                                        model.addItem(new MovieChartItem(
                                                dailyBoxOffice.getMovieNm(),
                                                dailyBoxOffice.getRank(),
                                                dailyBoxOffice.getOpenDt(),
                                                dailyBoxOffice.getAudiAcc(),
                                                dailyBoxOffice.getRankInten(),
                                                0));
                                    }
                                });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!getView().isFinishing()) {
                            getView().loadFail(context.getString(R.string.weekly_load_fail));
                        }
                    }
                });
    }
}
