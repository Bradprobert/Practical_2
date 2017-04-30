package bap0031.comp3710.csse.eng.auburn.edu.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import bap0031.comp3710.csse.eng.auburn.edu.R;
import bap0031.comp3710.csse.eng.auburn.edu.controller.Grid2048Controller;
import bap0031.comp3710.csse.eng.auburn.edu.controller.OnSwipeTouchListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private GridLayout gridView;
    private Button leftButton, rightButton, upButton, downButton;
    private TextView scoreTv;
    private Grid2048Controller controller;
    private Button restartButton;
    private MediaPlayer mp;

    public GameFragment() {
        // Required empty public constructor
    }

    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mp = MediaPlayer.create(this.getContext(), R.raw.clownhorn);
    }

    @Override
    public void onPause() {
        super.onPause();
        controller.saveState();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        gridView = (GridLayout) view.findViewById(R.id.gridView);
        scoreTv = (TextView) view.findViewById(R.id.textViewScore);
        controller = new Grid2048Controller(gridView, scoreTv, this.getContext());
        controller.resumeState();
        controller.refreshGridLayout();
        controller.refreshScore();

        leftButton = (Button) view.findViewById(R.id.buttonLeft);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftLeft();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }
        });
        rightButton = (Button) view.findViewById(R.id.buttonRight);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftRight();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }
        });
        upButton = (Button) view.findViewById(R.id.buttonUp);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftUp();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }
        });
        downButton = (Button) view.findViewById(R.id.buttonDown);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftDown();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }
        });


        gridView.setOnTouchListener(new OnSwipeTouchListener(this.getContext()) {
            @Override
            public void onSwipeRight() {
                controller.shiftRight();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }

            @Override
            public void onSwipeLeft() {
                controller.shiftLeft();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }

            @Override
            public void onSwipeTop() {
                controller.shiftUp();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }

            @Override
            public void onSwipeBottom() {
                controller.shiftDown();
                controller.refreshGridLayout();
                controller.refreshScore();
                playSound();
            }
        });

        restartButton = (Button) view.findViewById(R.id.buttonRestart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.reset();
            }
        });
        return view;
    }

    private void playSound() {
        SharedPreferences sp = getActivity().getSharedPreferences("setttings", Context.MODE_PRIVATE);
        if (sp.getBoolean("sound", true)) {
            mp.start();
        }
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
}
