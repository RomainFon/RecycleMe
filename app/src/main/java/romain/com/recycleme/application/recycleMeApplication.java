package romain.com.recycleme.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import romain.com.recycleme.R;

public class recycleMeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .name(getResources().getString(R.string.app_name)+".realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

    }
}
