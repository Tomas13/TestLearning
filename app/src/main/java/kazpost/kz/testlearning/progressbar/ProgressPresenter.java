package kazpost.kz.testlearning.progressbar;

/**
 * Created by root on 9/4/17.
 */

public class ProgressPresenter implements ProgressContract.ProgressPresenter {

    private ProgressContract.ProgressView mProgressView;

    public ProgressPresenter(ProgressContract.ProgressView mProgressView) {
        this.mProgressView = mProgressView;
    }



    @Override
    public void onDestroy() {
        mProgressView = null;
    }
}
