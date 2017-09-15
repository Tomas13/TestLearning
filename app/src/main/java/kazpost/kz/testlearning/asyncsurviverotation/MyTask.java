package kazpost.kz.testlearning.asyncsurviverotation;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kazpost.kz.testlearning.util.MyLogClass;

/**
 * Created by root on 9/13/17.
 */

public class MyTask extends AsyncTask<String, Integer, Boolean> {

    private int mContentLength = -1;
    private int mCounter = 0;
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
    protected void onPreExecute() {
        if (mActivity != null) ((RotationProofActivity) mActivity).showLoading();
    }

    @Override
    protected Boolean doInBackground(String[] strings) {

        boolean successful = false;
        URL downloadUrl;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file;
        try {
            downloadUrl = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) downloadUrl.openConnection();
            mContentLength = httpURLConnection.getContentLength();
            inputStream = httpURLConnection.getInputStream();
            file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                    "/" + Uri.parse(strings[0]).getLastPathSegment());

            fileOutputStream = new FileOutputStream(file);

            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
                mCounter += read;
                publishProgress(mCounter);
                MyLogClass.m("count " + mCounter);
            }
            successful = true;

            return successful;
        } catch (IOException e) {
            e.printStackTrace();
            MyLogClass.m(e.getMessage());
        } finally {
//            mView.hideLoading();
            if (httpURLConnection != null) httpURLConnection.disconnect();
            if (inputStream != null) try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fileOutputStream != null) try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        ((RotationProofActivity) mActivity).mPresenter.downloadImage(strings[0]);
        return successful;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        if (mActivity == null) {
            MyLogClass.m("Skipping progress update since activity is null");
        } else {
            int mCalculatedProgress = (int) (((double) values[0] / mContentLength) * 100);
            ((RotationProofActivity) mActivity).updateProgress(mCalculatedProgress);
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (mActivity != null) ((RotationProofActivity) mActivity).hideLoading();
        mActivity.runOnUiThread(() -> Toast.makeText(mActivity, "Finished", Toast.LENGTH_SHORT).show());
        MyLogClass.m("Finished");

    }
}
