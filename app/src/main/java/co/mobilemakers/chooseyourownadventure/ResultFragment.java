package co.mobilemakers.chooseyourownadventure;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    View view;

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
            updateTextView(args.getString("resultToShow"));
        }
    }

    private void updateTextView(String resultToShow) {
        mTextViewResult.setText(resultToShow);
    }

}
