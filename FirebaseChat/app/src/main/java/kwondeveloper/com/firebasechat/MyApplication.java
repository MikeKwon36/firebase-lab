package kwondeveloper.com.firebasechat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Todo on 3/23/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}
