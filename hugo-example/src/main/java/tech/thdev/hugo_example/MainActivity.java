package tech.thdev.hugo_example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import butterknife.BindView;
import butterknife.OnClick;
import hugo.weaving.DebugLog;
import tech.thdev.base.adapter.BaseRecyclerAdapter;
import tech.thdev.base.model.BaseItem;
import tech.thdev.base.view.BaseActivity;
import tech.thdev.base.view.BasePresenterActivity;
import tech.thdev.hugo_example.adapter.ListAdapter;
import tech.thdev.hugo_example.data.Items;
import tech.thdev.hugo_example.listener.OnClickListener;

public class MainActivity extends BasePresenterActivity<MainContract.View, MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public MainContract.Presenter onCreatePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @DebugLog
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getName("First", "Last"), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListAdapter listAdapter = new ListAdapter(this);

        // Model, View 등록
        getPresenter().setListModel(listAdapter);
        getPresenter().setListView(listAdapter);

        listAdapter.setOnClickListener(new OnClickListener() {

            @DebugLog
            @Override
            public <T extends BaseItem> void onItemClick(@Nullable BaseRecyclerAdapter<T> baseRecyclerView, int position) {
                getPresenter().showItem(position);
            }
        });
        recyclerView.setAdapter(listAdapter);
    }

    @DebugLog
    @Override
    public void updateNumber() {

    }

    @Override
    public void showItemsView(Items items) {
        Snackbar.make(recyclerView, items.getNumber(), Snackbar.LENGTH_LONG).show();
    }

    @DebugLog
    @OnClick(R.id.btn_one)
    public void onClickBtnOne(View view) {
        getPresenter().getNumber();
    }

    @DebugLog
    public String getName(String first, String last) {
        SystemClock.sleep(15); // Don't ever really do this!
        return first + " " + last;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @DebugLog
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
