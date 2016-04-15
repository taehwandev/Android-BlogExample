package tech.thdev.recycler_lambda_example;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import tech.thdev.recycler_lambda_example.adapter.ImageViewRecyclerAdapter;
import tech.thdev.recycler_lambda_example.presenter.MainPresenter;
import tech.thdev.recycler_lambda_example.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private FloatingActionButton fab;
    private MainPresenter mainPresenter;

//    private BottomSheetBehavior behavior;
//    private View bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainPresenter = new MainPresenterImpl(this);

//        bottomSheet = findViewById(R.id.rl_bottom_sheet);
//        behavior = BottomSheetBehavior.from(bottomSheet);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        );

        findViewById(R.id.btn_on_click).setOnClickListener(view ->
            Snackbar.make(view, "Lambda with your own action", Snackbar.LENGTH_SHORT).show()
        );

        ArrayList<Image> imageList = new ArrayList<>();
        imageList.add(new Image(R.drawable.sample_image));

        ImageViewRecyclerAdapter adapter = new ImageViewRecyclerAdapter(this);
        adapter.setItemList(imageList);
        adapter.setOnTouchLongClickListener((Image, position) -> {
            mainPresenter.onImageItemLongClick(R.drawable.sample_image);
            return true;
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    @Override
    public void showFlickerImageBottomSheet(@DrawableRes int resource) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_image_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageResource(resource);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}
