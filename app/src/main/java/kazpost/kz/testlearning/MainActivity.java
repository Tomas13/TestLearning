package kazpost.kz.testlearning;

import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{


    private ProgressBar progressBar;

    private MainContract.MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init presenter
        presenter = new MainPresenter(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_main);
        Button btnStartThread = (Button) findViewById(R.id.btn_start_thread);
        Button btnTest = (Button) findViewById(R.id.btn_test);
        btnStartThread.setOnClickListener(view -> {
//            backMethod();
            presenter.onDownload();
        });

        btnTest.setOnClickListener(view -> Toast.makeText(this, "SDFD", Toast.LENGTH_SHORT).show());

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




    @Override
    public void updateProgress() {
        progressBar.setProgress(50);
        Toast.makeText(this, "update progress called", Toast.LENGTH_SHORT).show();
    }



    private void backMethod() {
        Thread t = new Thread(() -> {

            Looper.prepare();

            try {
                Toast.makeText(getApplicationContext(), "Hello fromThread", Toast.LENGTH_SHORT).show();

                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), "Hello fromThread", Toast.LENGTH_SHORT).show();
                });
                Log.d("TAG", "thread is sleeping");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
        Looper.loop();

        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
    }


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

}
