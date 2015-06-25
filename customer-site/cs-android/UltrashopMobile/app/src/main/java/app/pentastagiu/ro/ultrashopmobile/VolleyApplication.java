package app.pentastagiu.ro.ultrashopmobile;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Razvan on 22/06/2015.
 */
public class VolleyApplication extends Application {

    private static VolleyApplication sInstance;
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public synchronized static VolleyApplication getsInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
