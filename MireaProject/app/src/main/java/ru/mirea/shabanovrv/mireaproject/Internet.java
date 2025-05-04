package ru.mirea.shabanovrv.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link internet#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.widget.Button;
import android.widget.EditText;

public class Internet extends Fragment {

    public Internet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_internet, container, false);

        Button button = view.findViewById(R.id.button3);
        EditText editText = view.findViewById(R.id.editTextText3);

        button.setOnClickListener(v -> {
            if (isInternetAvailable()) {
                editText.setText("Интернет есть =)");
                startMyWorker();
            } else {
                editText.setText("Нет интернета =(");
            }
        });

        return view;
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void startMyWorker() {
        WorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest);
    }
}