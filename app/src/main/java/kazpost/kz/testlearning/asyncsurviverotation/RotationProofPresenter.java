package kazpost.kz.testlearning.asyncsurviverotation;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 9/13/17.
 */

public class RotationProofPresenter implements RotationProofContract.RotationProofPresenter {

    RotationProofContract.RotationProofView mView;

    private String url;

    public RotationProofPresenter(RotationProofContract.RotationProofView mView) {
        this.mView = mView;
    }

    @Override
    public void downloadImage(String url) {


        this.url = url;
        Thread myThread = new Thread(new RotationProofPresenter.DownloadImagesThread());
        myThread.start();
    }

    private class DownloadImagesThread implements Runnable {

        @Override
        public void run() {
            mView.showLoading();
            try {
                downloadImageUsingThreads(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean downloadImageUsingThreads(String url) throws IOException {
        boolean successful = false;
        URL downloadUrl = null;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            downloadUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) downloadUrl.openConnection();
            inputStream = httpURLConnection.getInputStream();
            file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                    "/" + Uri.parse(url).getLastPathSegment());

            fileOutputStream = new FileOutputStream(file);

            int read = -1;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, read);
            }
            successful = true;

//        }catch (IOException e){
//            MyLogClass.m(e.getMessage());
        } finally {
            mView.hideLoading();
            if (httpURLConnection != null) httpURLConnection.disconnect();
            if (inputStream != null) inputStream.close();
            if (fileOutputStream != null) fileOutputStream.close();
        }

        return successful;

    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
