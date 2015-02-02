package co.mobilemakers.chooseyourownadventure;

import android.app.Activity;
import android.preference.PreferenceFragment;
import android.os.Bundle;

public class ActivitySettings extends Activity  {

    public static class AppSettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction().
                add(R.id.settings, new AppSettingsFragment()).
                commit();
    }
}
