package com.example.spotifyclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
/**
 * fragment which is going to appear when the user wants to add a bew playlist
 */

public class FloatingFragment extends DialogFragment implements View.OnClickListener {
    static FloatingFragment newInstance() {
        return new FloatingFragment();
    }
    private   EditText editText;
    private   TextView skip;
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
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        skip = (TextView) view.findViewById(R.id.skip);
        editText = (EditText) view.findViewById(R.id.editText22);
        String GetEditText = editText.getText().toString();
        cancel.setOnClickListener(this);
        skip.setOnClickListener(this);
        skip.setText("Create");

    /*   String sUsername = editText.getText().toString();

        if (sUsername.matches("")) {
            Toast.makeText(getContext(), "You did not enter a username", Toast.LENGTH_SHORT).show();
            skip.setText("skip");
        }
        else { skip.setText("Create");}*/

        return view;
    }

    /**
     * determining the response of the user selection
     * @param view
     */
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
                    getTargetFragment().onActivityResult(
                            getTargetRequestCode(), REQUEST_CODE, intent);
                    dismiss();
                    break;
                }

            }
        }
    }}
