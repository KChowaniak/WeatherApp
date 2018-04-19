package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Ina on 15.04.2018.
 */

public class CityPreference {

    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public String getCity() {
        return prefs.getString("city", "Spokane, US");
    }

    public void setCity(String city) {
        prefs.edit().putString("city", city).commit();
    }

}
