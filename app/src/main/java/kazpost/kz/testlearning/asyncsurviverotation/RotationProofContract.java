package kazpost.kz.testlearning.asyncsurviverotation;

import java.io.IOException;

/**
 * Created by root on 9/13/17.
 */

public interface RotationProofContract {

    interface RotationProofView {
        void showLoading();

        void hideLoading();
    }

    interface RotationProofPresenter {
        void downloadImage(String url);

        boolean downloadImageUsingThreads(String url) throws IOException;

        void onDestroy();
    }
}
