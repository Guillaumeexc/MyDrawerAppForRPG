package com.excoffier.guillaume.mydrawerappforrpg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.dial;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InventoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment implements View.OnClickListener{


    ImageButton btnHelmet;
    ImageButton btnSword;
    ImageButton btnTorsal;
    TextView txtBonus;
    Spinner swordSpinner;
    Spinner helmetSpinner;
    Spinner torsoSpinner;
    ImageView swordImageView;

    Weapon mySword;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InventoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InventoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryFragment newInstance(String param1, String param2) {
        InventoryFragment fragment = new InventoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_inventory, container, false);
        txtBonus=(TextView)v.findViewById(R.id.txtBonus);
        btnHelmet= (ImageButton)v.findViewById(R.id.btnHelmet);
        btnSword= (ImageButton)v.findViewById(R.id.btnSword);
        btnTorsal= (ImageButton)v.findViewById(R.id.btnTorsal);
        swordSpinner=(Spinner)v.findViewById(R.id.spinner_sword);
        helmetSpinner=(Spinner)v.findViewById(R.id.spinner_helmet);
        torsoSpinner=(Spinner)v.findViewById(R.id.spinner_torso);
        btnTorsal.setOnClickListener(this);
        btnSword.setOnClickListener(this);
        btnHelmet.setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHelmet:
                txtBonus.setText("Helmet");
                AlertDialog.Builder builderHelmet = new AlertDialog.Builder(getActivity());
                builderHelmet
                        .setTitle(Html.fromHtml("<font color='#FF7F27'>Helmet</font>")).setItems(R.array.helmet, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item


                    }
                })
                        .setIcon(R.drawable.ic_helmet)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builderHelmet.show();
                break;

            case R.id.btnSword:
                txtBonus.setText("Sword");
                AlertDialog.Builder builderSword = new AlertDialog.Builder(getActivity());
                builderSword
                        .setTitle(Html.fromHtml("<font color='#FF7F27'>Swords</font>")).setItems(R.array.swords, new DialogInterface.OnClickListener()
                {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                })
                        .setIcon(R.drawable.ic_sword)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK, so save the mSelectedItems results somewhere
                            // or return them to the component that opened the dialog

                }
            })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                builderSword.show();
                break;

            case R.id.btnTorsal:
                txtBonus.setText("Torsal");
                AlertDialog.Builder builderTorsal = new AlertDialog.Builder(getActivity());
                builderTorsal
                        .setTitle(Html.fromHtml("<font color='#FF7F27'>Armor</font>")).setItems(R.array.armor, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .setIcon(R.drawable.ic_armor)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builderTorsal.show();
                break;
        }
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
    public void InitItems(){

    }

}
