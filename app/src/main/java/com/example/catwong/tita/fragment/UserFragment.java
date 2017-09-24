package com.example.catwong.tita.fragment;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.catwong.tita.R;
import com.example.catwong.tita.activity.HomeActivity;
import com.example.catwong.tita.activity.LikeListActivity;
import com.example.catwong.tita.activity.MainActivity;
import com.example.catwong.tita.common.CommonKey;
import com.example.catwong.tita.util.PreferencesManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private RelativeLayout layoutEmailLink, layoutFbLink, layoutTwLink;
    private RelativeLayout layoutLike, layoutLogout;
    private HomeActivity homeActivity;


    public UserFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public  UserFragment(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    private void init() {
        layoutEmailLink = (RelativeLayout) getView().findViewById(R.id.email_link);
        layoutFbLink = (RelativeLayout) getView().findViewById(R.id.fb_link);
        layoutTwLink = (RelativeLayout) getView().findViewById(R.id.twitter_link);
        layoutLike = (RelativeLayout) getView().findViewById(R.id.user_like_list);
        layoutLogout = (RelativeLayout) getView().findViewById(R.id.logout);

        final PreferencesManager prefManage = PreferencesManager.getInstance(getView().getContext());

        layoutEmailLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alter = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                View v_iew=inflater.inflate(R.layout.layout_email_link, null);
                alter.setView(v_iew);

                alter.setTitle("Link Email");
                final TextView txtEmailAdd = (TextView) v_iew.findViewById(R.id.link_email_input);
                final TextView txtEmaliPwd = (TextView) v_iew.findViewById(R.id.link_pwd_input);

                alter.setPositiveButton("Link",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.i("Person Dialog", "modify");
                                        prefManage.putString(CommonKey.EMAIL_ADD,
                                                String.valueOf(txtEmailAdd.getText()));
                                        prefManage.putString(CommonKey.EMAIL_PWD,
                                                String.valueOf(txtEmaliPwd.getText()));
                                        System.out.println("Debug_info" +
                                                String.valueOf(txtEmailAdd.getText()));
                                    }
                                }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Person Dialog", "cancel");
                            }
                        });
                alter.show();
            }
        });

        layoutFbLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alter = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                View v_iew=inflater.inflate(R.layout.layout_social_link, null);
                alter.setView(v_iew);

                alter.setTitle("Link Facebook");
                final TextView txtUsername = (TextView) v_iew.findViewById(R.id.link_social_input);
                final TextView txtPwd = (TextView) v_iew.findViewById(R.id.link_social_pwd_input);

                alter.setPositiveButton("Link",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Person Dialog", "modify");
                                prefManage.putString(CommonKey.FB_USERNAME,
                                        String.valueOf(txtUsername.getText()));
                                prefManage.putString(CommonKey.FB_PWD,
                                        String.valueOf(txtPwd.getText()));
                                System.out.println("Debug_info" +
                                        String.valueOf(txtUsername.getText()));
                            }
                        }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Person Dialog", "cancel");
                            }
                        });
                alter.show();
            }
        });

        layoutTwLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alter = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                View v_iew=inflater.inflate(R.layout.layout_social_link, null);
                alter.setView(v_iew);

                alter.setTitle("Link Twitter");
                final TextView txtUsername = (TextView) v_iew.findViewById(R.id.link_social_input);
                final TextView txtPwd = (TextView) v_iew.findViewById(R.id.link_social_pwd_input);

                alter.setPositiveButton("Link",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Person Dialog", "modify");
                                prefManage.putString(CommonKey.TW_USERNAME,
                                        String.valueOf(txtUsername.getText()));
                                prefManage.putString(CommonKey.TW_PWD,
                                        String.valueOf(txtPwd.getText()));
                                System.out.println("Debug_info" +
                                        String.valueOf(txtUsername.getText()));
                            }
                        }).setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Person Dialog", "cancel");
                            }
                        });
                alter.show();
            }
        });


        layoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LikeListActivity.class));
            }
        });

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManage.setBoolean(CommonKey.LOGGEDIN, false);
                prefManage.putString(CommonKey.NAME, "");
                prefManage.putString(CommonKey.EMAIL, "");

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

    }
}


