package kazpost.kz.testlearning.downloadImage;

import java.io.IOException;

/**
 * Created by root on 8/25/17.
 */

public interface DownloadContract {

    interface DownloadView {
        void showLoading();
        void hideLoading();
    }

    interface DownloadPresenter {
        void downloadImage(String url);

        boolean downloadImageUsingThreads(String url) throws IOException;

        void onDestroy();
    }
}
