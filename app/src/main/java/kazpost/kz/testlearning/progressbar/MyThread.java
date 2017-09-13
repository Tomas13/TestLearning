package kazpost.kz.testlearning.progressbar;

import android.os.Handler;
import android.os.Message;

/**
 * Created by root on 9/4/17.
 */

public class MyThread implements Runnable {
    Handler handler;

    public MyThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message = Message.obtain();

            if (i == 99){
                message.obj = "That's it";
            }

            message.arg1 = i;
            handler.sendMessage(message);

        }
    }
}
