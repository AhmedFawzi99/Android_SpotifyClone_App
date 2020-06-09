package com.example.spotifyclone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

abstract class BottomSheetDialogFragment {
    public abstract void onCreate(Bundle savedInstance);

    public abstract View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState);

    public abstract void onClick(View view);
}
