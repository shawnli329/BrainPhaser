package de.fhdw.ergoholics.brainphaser.activities.Challenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import de.fhdw.ergoholics.brainphaser.BrainPhaserComponent;
import de.fhdw.ergoholics.brainphaser.R;

/**
 * Fragment to let the user decide whether an answer is right or not
 */
public class SelfTestDialogFragment extends AnswerFragment {

    /**
     * Interface
     */
    public interface SelfCheckAnswerListener{
        void onSelfCheckAnswerChecked();
    }
    SelfCheckAnswerListener mSelfCheckAnswerListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //load the interface
        loadSelfCheckAnswerListener();
        //infalte the view
        mView = inflater.inflate(R.layout.fragment_challenge_self_test, container, false);
        Button btnRight = (Button)mView.findViewById(R.id.answerRight);
        Button btnWrong = (Button)mView.findViewById(R.id.answerWrong);
        //set on click listener to the button
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //execute AnswerListener and unloads the fragment
                mListener.onAnswerChecked(true);
                mSelfCheckAnswerListener.onSelfCheckAnswerChecked();
            }
        });
        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //execute AnswerListener and unloads the fragment
                mListener.onAnswerChecked(false);
                mSelfCheckAnswerListener.onSelfCheckAnswerChecked();
            }
        });

        //Loads the possible answers into a list
        loadAnswers(R.id.answerListSelfCheck, null);
        return mView;
    }

    /**
     * Loads the AnswerListener of the opening activity
     */
    private void loadSelfCheckAnswerListener(){
        // The activity that opens these fragments must implement AnswerListener.
        // This method stores the listener when the activity is attached.
        // Verify that the host activity implements the callback interface
        try {
            // Cast to SelfTestDialogListener so we can send events to the host
            mSelfCheckAnswerListener = (SelfCheckAnswerListener) getActivity();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }

    /**
     * Check answer
     */
    @Override
    public void checkAnswers() {
        mSelfCheckAnswerListener.onSelfCheckAnswerChecked();
    }

    /**
     * Inject components
     * @param component
     */
    @Override
    protected void injectComponent(BrainPhaserComponent component) {
        component.inject(this);
    }
}