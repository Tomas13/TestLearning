package kazpost.kz.testlearning.downloadImage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.testlearning.R;

public class DownloadActivity extends AppCompatActivity implements DownloadContract.DownloadView {

    @BindView(R.id.et_download)
    EditText etDownload;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.lv_download)
    ListView listViewDownload;
    @BindView(R.id.progress_download)
    ProgressBar progressDownload;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;

    private String[] listOfImages;

    private DownloadPresenter presenter;
    private Handler handler;

    private static final String TAG = DownloadActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        handler  = new Handler();
        handler.sendMessageAtFrontOfQueue(Message.obtain());
        presenter = new DownloadPresenter(this);
        listOfImages = getResources().getStringArray(R.array.imageUrl);
        listViewDownload.setOnItemClickListener((adapterView, view, i, l) -> etDownload.setText(listOfImages[i]));

        if (savedInstanceState == null){
            Toast.makeText(this, "First time", Toast.LENGTH_SHORT).show();
        }else{
            Log.d(TAG, savedInstanceState.toString());
            Toast.makeText(this, savedInstanceState.get("key").toString(), Toast.LENGTH_SHORT).show();
        }


//        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vec);
//
//        etDownload.setAnimation(animation);
//        animation.start();
    }

//    Animation animation;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "Zhangali");

    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {
        String url = etDownload.getText().toString();


        presenter.downloadImage(url);
    }

    @Override
    public void showLoading() {
        this.runOnUiThread(() -> linearLayout.setVisibility(View.VISIBLE));

    }

    @Override
    public void hideLoading() {
//        this.runOnUiThread(() -> linearLayout.setVisibility(View.GONE));

        handler.post(() -> {
           linearLayout.setVisibility(View.GONE);
        });
    }
}
