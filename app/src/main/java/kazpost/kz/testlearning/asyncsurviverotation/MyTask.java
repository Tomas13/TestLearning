package kazpost.kz.testlearning.asyncsurviverotation;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by root on 9/13/17.
 */

public class MyTask extends AsyncTask<String, Integer, Boolean> {

    private int mContentLength = -1;
    private int mCounter = 0;
    private int mCalculatedProgress = 0;
    private Activity mActivity;

    public MyTask(Activity activity) {
        onAttach(activity);
    }

    public void onAttach(Activity activity) {
        this.mActivity = activity;
    }

    public void onDetach() {
        mActivity = null;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        return null;
    }
}
