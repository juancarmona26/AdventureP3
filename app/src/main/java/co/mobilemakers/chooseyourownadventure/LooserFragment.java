package co.mobilemakers.chooseyourownadventure;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LooserFragment extends Fragment {

    View view;
    private TextView mTextViewLooserText;

    public LooserFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_looser, container, false);
        mTextViewLooserText = (TextView) view.findViewById(R.id.text_view_looser_text);
        displayUserIntro();

        return view;

    }

    private void displayUserIntro() {
        String username = getUsername();
        String looserMessage = getCustomLooserMessage();

        mTextViewLooserText.setText(looserMessage);
    }

    private String getUsername() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(MainFragment.USERNAME_PREFERENCE, getString(R.string.default_username));
    }

    private String getCustomLooserMessage() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(MainFragment.LOOSER_MESSAGE_PREFERENCE, getString(R.string.text_view_you_lost));
    }
}
