package cc.yuanlee.labtalk;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.avos.avoscloud.AVOSCloud;

public class MyApplication extends Application {

	public static Context applicationContext;
	private static MyApplication instance;
	// login user name
	public final String PREF_USERNAME = "username";

	/**
	 * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
	 */
	public static String currentUserNick = "";

	@Override
	public void onCreate() {
		MultiDex.install(this);
		super.onCreate();
        applicationContext = this;
        instance = this;

		//init demo helper
        AppHelper.getInstance().init(applicationContext);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"0a5FQL8q2viOhk4NPckwQrPU-gzGzoHsz","oJQotoDyBIiqE9HMifGwHylU");
        AVOSCloud.setDebugLogEnabled(true);
	}

	public static MyApplication getInstance() {
		return instance;
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}
}
