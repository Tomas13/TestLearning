package kazpost.kz.testlearning.downloadImage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);

        presenter = new DownloadPresenter(this);
        listOfImages = getResources().getStringArray(R.array.imageUrl);
        listViewDownload.setOnItemClickListener((adapterView, view, i, l) -> etDownload.setText(listOfImages[i]));
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
        this.runOnUiThread(() -> linearLayout.setVisibility(View.GONE));
    }
}
