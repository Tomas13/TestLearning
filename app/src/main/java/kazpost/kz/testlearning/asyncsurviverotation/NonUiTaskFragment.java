package kazpost.kz.testlearning.asyncsurviverotation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by root on 9/13/17.
 */

public class NonUiTaskFragment extends Fragment {
    MyTask mMyTask;
    Activity mActivity;

    public NonUiTaskFragment() {
    }

    public void beginTask(String... arguments) {
        mMyTask = new MyTask(mActivity);
        mMyTask.execute(arguments);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        if (mMyTask != null) {
            mMyTask.onAttach(mActivity);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mMyTask != null) {
            mMyTask.onDetach();
        }

    }
}
