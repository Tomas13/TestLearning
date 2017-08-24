package kazpost.kz.testlearning;

/**
 * Created by root on 8/24/17.
 */

public interface MainContract {

    interface MainPresenter {
        void onDownload();

        void onDestroy();
    }

    interface MainView {
        void updateProgress();
    }
}
