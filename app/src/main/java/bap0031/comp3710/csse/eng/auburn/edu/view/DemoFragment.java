package bap0031.comp3710.csse.eng.auburn.edu.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

import bap0031.comp3710.csse.eng.auburn.edu.R;
import bap0031.comp3710.csse.eng.auburn.edu.controller.Grid2048Controller;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DemoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private GridLayout gridView;
    private TextView scoreTv;
    private static Grid2048Controller controller;
    private static MediaPlayer mp;
    private static Handler handler;

    public DemoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DemoFragment newInstance() {
        DemoFragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        handler = new Handler();
        mp = MediaPlayer.create(this.getContext(), R.raw.clownhorn);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);

        gridView = (GridLayout) view.findViewById(R.id.gridView);
        scoreTv = (TextView) view.findViewById(R.id.textViewScore);
        controller = new Grid2048Controller(gridView, scoreTv, this.getContext());
        controller.resumeState();
        controller.refreshGridLayout();
        controller.refreshScore();
        startReapeatingTask();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void startReapeatingTask() {
        demoMode.run();
    }

    public static void stopRepeatingTask() {
        handler.removeCallbacks(demoMode);
    }

    private static Runnable demoMode =
            new Runnable() {
                @Override
                public void run() {
                    try {
                    }
                    finally {
                        Random rand = new Random();
                        int direction = rand.nextInt(4);
                        switch (direction) {
                            case 0:
                                upButton();
                                break;
                            case 1:
                                leftButton();
                                break;
                            case 2:
                                downButton();
                                break;
                            case 3:
                                rightButton();
                                break;
                        }
                        handler.postDelayed(demoMode, 1500);
                    }
                }
            };

    private static void downButton() {
        controller.shiftDown();
        controller.refreshGridLayout();
        controller.refreshScore();
        playSound();
    }

    private static void leftButton() {
        controller.shiftLeft();
        controller.refreshGridLayout();
        controller.refreshScore();
        playSound();
    }

    private static void upButton() {
        controller.shiftUp();
        controller.refreshGridLayout();
        controller.refreshScore();
        playSound();
    }

    private static void rightButton() {
        controller.shiftRight();
        controller.refreshGridLayout();
        controller.refreshScore();
        playSound();
    }

    private static void playSound() {
            mp.start();
    }
}
