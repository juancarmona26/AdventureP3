package co.mobilemakers.chooseyourownadventure;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class ContainerActivity extends ActionBarActivity implements  AlleyFragment.OnClickAdventureButtons,
                                                                    RoomFragment.OnClickButtons,
                                                                    MainFragment.InitialEvents{

    public static final int RESULT_SETTINGS = 1;
    public static final String LOG_TAG = ContainerActivity.class.getName();
    public static List<Integer> randomAlleyStates = null;
    public static List<Integer> randomRoomStates = null;
    public static int difficulty;
    private static Random random = new Random();
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        showMainFragment();
        customizeActionBar();
    }

    private void showMainFragment() {
        mainFragment  = new MainFragment();
        getFragmentManager().beginTransaction().add(R.id.layout_container, mainFragment).commit();
    }

    private void chooseFragmentRandomly(){
        int randomNumber = randomNumberToShowView(2);
        FragmentTransaction transaction;
        Log.d(LOG_TAG, "Random number = "+ randomNumber);

        switch (randomNumber){

            case 1: Toast.makeText(getApplicationContext(), "That button brought you to Alley", Toast.LENGTH_LONG).show();
                    AlleyFragment alleyFragment = new AlleyFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, alleyFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);
                    transaction.commit();
            break;

            case 2: Toast.makeText(getApplicationContext(), "That button brought you to Room", Toast.LENGTH_LONG).show();
                    RoomFragment roomFragment = new RoomFragment();
                    transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.layout_container, roomFragment);
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    transaction.addToBackStack(null);

                    transaction.commit();
            break;

            default:
        }

    }

    public static int randomNumberToShowView (int numberToGenerateRandom) {
        return random.nextInt(numberToGenerateRandom) + 1;
    }

    @Override
    public void onClickButtons(int probabilityGoTo) {
        switch (probabilityGoTo){

            case 0:
                winnerScreen();
            break;

            case 1:
                chooseFragmentRandomly();
            break;

            case 2:
                looserScreen();
             break;
            }
    }

    private void looserScreen() {
        FragmentTransaction transaction;
        LooserFragment newFragment = new LooserFragment();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, newFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    private void winnerScreen() {
        FragmentTransaction transaction;
        WinnerFragment winnerFragment = new WinnerFragment();

        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, winnerFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);
        transaction.commit();
    }



     public void showResult(String resultToShow, int status) {
        ResultFragment newFragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString("resultToShow", resultToShow);
        args.putInt("status",status );
        newFragment.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_container, newFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    @Override
    public void onButtonClicked(int whereToFall) {
        switch (whereToFall) {

            case 0:
                showResult("You've reached the gold!", whereToFall);
                break;

            case 1:
                showResult("You've fallen in to the pit of despair", whereToFall);
                break;
        }
    }

    @Override
    public void onButtonRandomClicked() {
        chooseFragmentRandomly();
    }

    @Override
    public void onButtonStartAdventureClicked(int difficulty) {
        ContainerActivity.difficulty = difficulty;
        chooseFragmentRandomly();
    }

    private void customizeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.action_bar_title);
        actionBar.setIcon(R.drawable.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(this, ActivitySettings.class);
                startActivityForResult(intent, RESULT_SETTINGS);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
       if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
