package co.mobilemakers.chooseyourownadventure;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class AlleyFragment extends Fragment {
    public OnClickAdventureButtons mCallback;

    private Button mButtonToWin;
    private Button mButtonToRoomView;
    private Button mButtonToLoose;
    public int numberToWin;

    View view;


    public AlleyFragment() {

    }

    public interface OnClickAdventureButtons {
        public void onClickButtons(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_alley, container, false);
        prepareAdventureButtonsEvent();

        ContainerActivity.randomAlleyStates = new ArrayList<>();
        setDifferentRandomNumbers();
        chooseDifficulty();

        return view;
    }

    private void chooseDifficulty() {
        if(ContainerActivity.difficulty == 0)
            setAdventureButtonEventsForEasyMode();
        else if(ContainerActivity.difficulty == 1) {
            setAdventureButtonEventsForLegendaryMode();
            setRandomNumberToWin();
        }
    }

    private void setDifferentRandomNumbers() {
        for(int i = 0; i < 3; i++) {
            int randomNumber = ContainerActivity.randomNumberToShowView(100);
            while(ContainerActivity.randomAlleyStates.contains(randomNumber) && i != 0) {
                randomNumber = ContainerActivity.randomNumberToShowView(100);
            }
            ContainerActivity.randomAlleyStates.add(randomNumber);
        }
    }

    private void setRandomNumberToWin() {
        numberToWin = ContainerActivity.randomNumberToShowView(100);
    }

    private void prepareAdventureButtonsEvent() {
        mButtonToWin = (Button) view.findViewById(R.id.button_go_right);
        mButtonToLoose = (Button) view.findViewById(R.id.button_go_left);
        mButtonToRoomView = (Button) view.findViewById(R.id.button_continue);
    }

    private void setAdventureButtonEventsForEasyMode(){

        mButtonToWin.setOnClickListener(clickListenerEasy);

        mButtonToLoose.setOnClickListener(clickListenerEasy);

        mButtonToRoomView.setOnClickListener(clickListenerEasy);
    }

    private void setAdventureButtonEventsForLegendaryMode(){
        mButtonToWin.setOnClickListener(clickListenerLegendary);

        mButtonToLoose.setOnClickListener(clickListenerLegendary);

        mButtonToRoomView.setOnClickListener(clickListenerLegendary);
    }

    View.OnClickListener clickListenerEasy = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean firstValidation;
            boolean secondValidation;

            switch (v.getId()){
                case R.id.button_go_right:
                    firstValidation = validateValueInPosition(0, 1);
                    secondValidation = validateValueInPosition(0, 2);

                    selectWhereToGo(firstValidation, secondValidation);

                    break;

                case R.id.button_continue:
                    firstValidation = validateValueInPosition(1, 0);
                    secondValidation = validateValueInPosition(1, 2);

                    selectWhereToGo(firstValidation, secondValidation);
                break;

                case R.id.button_go_left:
                    firstValidation = validateValueInPosition(2, 0);
                    secondValidation = validateValueInPosition(2, 1);

                    selectWhereToGo(firstValidation, secondValidation);
                break;
            }

        }
    };

    View.OnClickListener clickListenerLegendary = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_go_right:  validationLegendaryMode(0);
                break;

                case R.id.button_continue:
                    validationLegendaryMode(1);
                break;

                case R.id.button_go_left:
                    validationLegendaryMode(2);
                break;
            }

        }
    };

    private void validationLegendaryMode(int position) {
        boolean firstValidation;
        boolean secondValidation;
        if(ContainerActivity.randomAlleyStates.get(position) == numberToWin){
            mCallback.onClickButtons(0);
        } else {
            firstValidation = validateValueInPosition(position, 1);
            secondValidation = validateValueInPosition(position, 2);
            selectionOnLegendaryMode(firstValidation, secondValidation);
        }
    }

    private void selectWhereToGo(boolean firstValidation, boolean secondValidation) {
        if(firstValidation && secondValidation){
            mCallback.onClickButtons(0);
        } else if((!firstValidation && secondValidation) || (firstValidation && !secondValidation)) {
            mCallback.onClickButtons(1);
        } else mCallback.onClickButtons(2);
    }

    private void selectionOnLegendaryMode(boolean firstValidation, boolean secondValidation){
        if(firstValidation && secondValidation){
            mCallback.onClickButtons(1);
        } else if((!firstValidation && secondValidation) || (firstValidation && !secondValidation)) {
            mCallback.onClickButtons(1);
        } else mCallback.onClickButtons(2);
    }

    private boolean validateValueInPosition(int currentPosition, int validationPosition ) {
        return ContainerActivity.randomAlleyStates.get(currentPosition) > ContainerActivity.randomAlleyStates.get(validationPosition);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnClickAdventureButtons) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnClickAdventureButtons");
        }
    }
}
