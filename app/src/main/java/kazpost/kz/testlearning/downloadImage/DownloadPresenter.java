package kazpost.kz.testlearning.downloadImage;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 8/25/17.
 */

public class DownloadPresenter implements DownloadContract.DownloadPresenter {

    private DownloadContract.DownloadView downloadView;

    public DownloadPresenter(DownloadContract.DownloadView downloadView) {
        this.downloadView = downloadView;
    }

    private String url;

    @Override
    public void downloadImage(String url) {
//        downloadImageUsingThreads(url);

        this.url = url;

        Thread myThread = new Thread(new DownloadImagesThread());
        myThread.start();

    }


    private class DownloadImagesThread implements Runnable {

        @Override
        public void run() {

            downloadView.showLoading();
            try {
                downloadImageUsingThreads(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 1 create the url object that represent the url
     * 2 open connection using that url object
     * 3 read data using input stream into a byte array
     * 4 open a file output stream to save data on sd card
     * 5 write data to the fileoutputstream
     */
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
            downloadView.hideLoading();
            if (httpURLConnection != null) httpURLConnection.disconnect();
            if (inputStream != null) inputStream.close();
            if (fileOutputStream != null) fileOutputStream.close();
        }

        return successful;
    }


    @Override
    public void onDestroy() {
        downloadView = null;
    }
}
