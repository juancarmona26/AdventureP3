package co.mobilemakers.chooseyourownadventure;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    View view;
    private String textToShow;
    private int status;

    private TextView mTextViewResult;

    public ResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_result, container, false);
        prepareTextView();

        return view;
    }

    public void prepareTextView() {
        mTextViewResult = (TextView) view.findViewById(R.id.text_view_result);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateTextView(args.getString("resultToShow"), args.getInt("status"));
        }
    }

    private void updateTextView(String resultToShow, int status) {
        mTextViewResult.setText(resultToShow);
        textToShow = resultToShow;
        this.status = status;
        displayUserIntro();
    }

    private void displayUserIntro() {
        String customMessage;
        if(status == 0)
            customMessage = getCustomWinnerMessage();
        else
            customMessage = getCustomLooserMessage();

        mTextViewResult.setText(customMessage);
    }

    private String getCustomLooserMessage() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(MainFragment.LOOSER_MESSAGE_PREFERENCE, textToShow);
    }

    private String getCustomWinnerMessage() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(MainFragment.WINNER_MESSAGE_PREFERENCE, textToShow);
    }

}
