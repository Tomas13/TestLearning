package kazpost.kz.testlearning.progressbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kazpost.kz.testlearning.R;

public class ProgressActivity extends AppCompatActivity implements ProgressContract.ProgressView {

    @BindView(R.id.progress_activity)
    ProgressBar progressBar;

    ProgressPresenter progressPresenter;


    Thread thread;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.bind(this);

        progressPresenter = new ProgressPresenter(this);


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                progressBar.setProgress(msg.arg1);
                
                if (msg.obj != null){
                    Toast.makeText(ProgressActivity.this, msg.obj + " ", Toast.LENGTH_SHORT).show();
                }
            }
        };


        thread = new Thread(new MyThread(handler));
        thread.start();
    }


}
