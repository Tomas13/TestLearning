package kazpost.kz.testlearning;

/**
 * Created by root on 8/24/17.
 */

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mainView;

    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }



    @Override
    public void onDownload() {
        mainView.updateProgress();
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    public MainContract.MainView getMainView() {
        return mainView;
    }
}
