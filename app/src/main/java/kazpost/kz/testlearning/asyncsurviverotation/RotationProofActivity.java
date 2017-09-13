package kazpost.kz.testlearning.asyncsurviverotation;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.testlearning.R;

public class RotationProofActivity extends AppCompatActivity implements RotationProofContract.RotationProofView {

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

    private RotationProofPresenter presenter;
    private Handler handler;

    private static final String TAG = RotationProofActivity.class.getSimpleName();
    private static final String FRAGMENT_TAG = "TaskFragment";

    NonUiTaskFragment mNonUiTaskFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_proof);

        ButterKnife.bind(this);

        handler = new Handler();
        handler.sendMessageAtFrontOfQueue(Message.obtain());
        presenter = new RotationProofPresenter(this);
        listOfImages = getResources().getStringArray(R.array.imageUrl);
        listViewDownload.setOnItemClickListener((adapterView, view, i, l) -> etDownload.setText(listOfImages[i]));

        if (savedInstanceState == null) {
            mNonUiTaskFragment = new NonUiTaskFragment();
            getSupportFragmentManager().beginTransaction().add(mNonUiTaskFragment, FRAGMENT_TAG).commit();
        } else {
            mNonUiTaskFragment = (NonUiTaskFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            Log.d(TAG, savedInstanceState.toString());
//            Toast.makeText(this, savedInstanceState.get("key").toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {
        String url = etDownload.getText().toString();

        if (url.length() > 0) {
//            presenter.downloadImage(url);

            mNonUiTaskFragment.beginTask(url);
        }

    }


    @Override
    public void showLoading() {
        this.runOnUiThread(() -> linearLayout.setVisibility(View.VISIBLE));

    }

    @Override
    public void hideLoading() {
        handler.post(() -> {
            linearLayout.setVisibility(View.GONE);
        });

    }
}
