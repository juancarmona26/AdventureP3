package co.mobilemakers.chooseyourownadventure;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    private static final String LOG_TAG = MainFragment.class.getName();
    View view;
    InitialEvents mCallBack;
    private Button mButtonEasy;
    private Button mButtonHard;

    public MainFragment() {
    }

    public interface InitialEvents {
        public void onButtonStartAdventureClicked(int difficulty);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Oncreate");
        view = inflater.inflate(R.layout.fragment_main, container, false);
        setViewsFromLayouts();
        setViewEvents();

        return view;
    }

    private void setViewsFromLayouts(){
        mButtonEasy = (Button) view.findViewById(R.id.button_easy);
        mButtonHard = (Button) view.findViewById(R.id.button_hard);
    }


    private void setViewEvents(){
        mButtonEasy.setOnClickListener(onClickListener);
        mButtonHard.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_easy:
                    mCallBack.onButtonStartAdventureClicked(0);
                    break;

                case R.id.button_hard:
                    mCallBack.onButtonStartAdventureClicked(1);
                    break;
            }
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (InitialEvents) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement InitialEvents");
        }

    }
}
