package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class cretaeartplay extends BottomSheetDialogFragment {
    private EditText editText;
    private TextView create;
    public static  String GetEditText;
    RelativeLayout laycreate;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.createartplay, container, false);

        create =v.findViewById(R.id.createe);
        editText = v.findViewById(R.id.editText222);
        GetEditText = editText.getText().toString();
        laycreate=v.findViewById(R.id.laycreatee);
        laycreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetEditText = editText.getText().toString();
                artPlaylistFragment artPlaylistFragment=new artPlaylistFragment("start");

                dismiss();
            }
        });
        return v;
    }
}
