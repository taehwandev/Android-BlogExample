package tech.thdev.app.view.weekboxoffice.presenter;

import android.content.Context;

import tech.thdev.app.adapter.movie.adapter.model.BoxOfficeAdapterContract;
import tech.thdev.app.data.source.MovieSearchRepository;
import tech.thdev.base.presenter.BasePresenter;
import tech.thdev.base.presenter.BaseView;

/**
 * Created by tae-hwan on 12/13/16.
 */

public interface WeeklyBoxOfficeContract {

    interface View extends BaseView {
        boolean isFinishing();

        void loadFail(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void setSampleOneModel(BoxOfficeAdapterContract.Model model);

        void setSampleOneView(BoxOfficeAdapterContract.View view);

        void setMovieSearchRepository(MovieSearchRepository movieSearchRepository);

        void getWeeklyBoxOffice(Context context, String targetDate);
    }
}
