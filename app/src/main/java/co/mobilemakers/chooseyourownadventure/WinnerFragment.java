package co.mobilemakers.chooseyourownadventure;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WinnerFragment extends Fragment {

    View view;
    private TextView mTextViewWinnerMessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_winner, container, false);
        mTextViewWinnerMessage = (TextView) view.findViewById(R.id.text_view_winner_message);
        displayUserIntro();
        return view;
    }

    private void displayUserIntro() {
        String winnerMessage = getWinnerMessage();

        mTextViewWinnerMessage.setText(winnerMessage);
    }

    private String getWinnerMessage() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(MainFragment.WINNER_MESSAGE_PREFERENCE, getString(R.string.text_view_you_won));
    }
}
