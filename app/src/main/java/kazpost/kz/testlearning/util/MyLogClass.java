package kazpost.kz.testlearning.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by root on 8/25/17.
 */

public class MyLogClass {

    public static void m(String message){
        Log.d("MyTag", message);
    }

    public static void toast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
