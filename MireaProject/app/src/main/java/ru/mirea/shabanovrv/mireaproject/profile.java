package ru.mirea.shabanovrv.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class profile extends Fragment {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_NAME = "key_name";
    private static final String KEY_SURNAME = "key_surname";
    private static final String KEY_GROUP = "key_group";

    private EditText FIO;
    private EditText group;
    private Button Save;
    private Button Load;

    private SharedPreferences sharedPref;

    public profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FIO = view.findViewById(R.id.FIO);
        group = view.findViewById(R.id.group);
        Save = view.findViewById(R.id.save);
        Load = view.findViewById(R.id.load);

        sharedPref = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Save.setOnClickListener(v -> {
            savePreferences();
            Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_LONG).show();
        });

        Load.setOnClickListener(v -> {
            loadPreferences();
            Toast.makeText(getContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
        });
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY_NAME, FIO.getText().toString());
        editor.putString(KEY_GROUP, group.getText().toString());
        editor.apply();
    }

    private void loadPreferences() {
        FIO.setText(sharedPref.getString(KEY_NAME, ""));
        group.setText(sharedPref.getString(KEY_GROUP, ""));
    }
}