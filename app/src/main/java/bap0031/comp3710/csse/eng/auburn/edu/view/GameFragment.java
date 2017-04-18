package bap0031.comp3710.csse.eng.auburn.edu.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.List;

import bap0031.comp3710.csse.eng.auburn.edu.R;
import bap0031.comp3710.csse.eng.auburn.edu.controller.Grid2048Controller;
import bap0031.comp3710.csse.eng.auburn.edu.model.Tile;
import bap0031.comp3710.csse.eng.auburn.edu.model.GameGrid;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private GridLayout gridView;
    private Button leftButton, rightButton, upButton, downButton;
    private Grid2048Controller controller;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        gridView = (GridLayout) view.findViewById(R.id.gridView);

        controller = new Grid2048Controller(gridView, this.getContext());

        leftButton = (Button) view.findViewById(R.id.buttonLeft);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftLeft();
                controller.refreshGridLayout();
            }
        });
        rightButton = (Button) view.findViewById(R.id.buttonRight);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftRight();
                controller.refreshGridLayout();
            }
        });
        upButton = (Button) view.findViewById(R.id.buttonUp);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftUp();
                controller.refreshGridLayout();
            }
        });
        downButton = (Button) view.findViewById(R.id.buttonDown);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.shiftDown();
                controller.refreshGridLayout();
            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLeft:
                controller.shiftLeft();
                break;
            case R.id.buttonRight:
                controller.shiftRight();
                break;
            case R.id.buttonUp:
                controller.shiftUp();
                break;
            case R.id.buttonDown:
                controller.shiftDown();
                break;
        }
        gridView = controller.refreshGridLayout();
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
