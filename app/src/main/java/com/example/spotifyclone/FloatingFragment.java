package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FloatingFragment extends DialogFragment implements View.OnClickListener {
    static FloatingFragment newInstance() {
        return new FloatingFragment();
    }
    private   EditText editText;
    private   TextView skip;
    private   TextView cancel;
    private  String GetEditText;
    private int REQUEST_CODE=0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.floating, container, false);
         cancel = (TextView) view.findViewById(R.id.cancel);
      skip = (TextView) view.findViewById(R.id.skip);
      editText = (EditText) view.findViewById(R.id.editText22);
         GetEditText = editText.getText().toString();
        cancel.setOnClickListener(this);
        skip.setOnClickListener(this);
            skip.setText("Create");

        return view;
    }


    @Override
    public void onClick(View view) {
        {
            int id = view.getId();
            switch (id) {
                case R.id.cancel:
                    dismiss();
                    break;
                case R.id.skip: {
                    GetEditText = editText.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("STRING_RESULT", GetEditText);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), REQUEST_CODE, intent);
                    dismiss();
                    break;
                }

            }
        }
    }}