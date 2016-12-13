package tech.thdev.app.view.weekboxoffice;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;
import tech.thdev.app.R;
import tech.thdev.app.adapter.movie.adapter.BoxOfficeAdapter;
import tech.thdev.app.data.source.MovieSearchRepository;
import tech.thdev.app.view.weekboxoffice.presenter.WeeklyBoxOfficeContract;
import tech.thdev.app.view.weekboxoffice.presenter.WeeklyBoxOfficePresenter;
import tech.thdev.base.view.BasePresenterFragment;

/**
 * Created by tae-hwan on 12/13/16.
 */

public class WeeklyBoxOfficeFragment extends BasePresenterFragment<WeeklyBoxOfficeContract.View, WeeklyBoxOfficeContract.Presenter> implements WeeklyBoxOfficeContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.rl_empty_view)
    RelativeLayout rlEmptyView;

    @BindView(R.id.tv_no_content)
    TextView tvNoContent;

    @Nullable
    @Override
    public WeeklyBoxOfficeContract.Presenter onCreatePresenter() {
        return new WeeklyBoxOfficePresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_box_office;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BoxOfficeAdapter boxOfficeAdapter = new BoxOfficeAdapter(getContext());
        recyclerView.setAdapter(boxOfficeAdapter);

        getPresenter().setSampleOneModel(boxOfficeAdapter);
        getPresenter().setSampleOneView(boxOfficeAdapter);

        getPresenter().setMovieSearchRepository(MovieSearchRepository.INSTANCE);

        getPresenter().getWeeklyBoxOffice(getContext(), new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - ((1000 * 60 * 60 * 24) * 7)));
    }

    @Override
    public boolean isFinishing() {
        return getActivity().isFinishing();
    }

    @Override
    public void loadFail(String message) {
        rlEmptyView.setVisibility(View.VISIBLE);
        tvNoContent.setText(message);
    }

    @OnClick(R.id.btn_reload)
    public void onClickReload(View view) {
        getPresenter().getWeeklyBoxOffice(getContext(), new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis() - ((1000 * 60 * 60 * 24) * 7)));
    }
}
