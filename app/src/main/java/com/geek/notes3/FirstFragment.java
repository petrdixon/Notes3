package com.geek.notes3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class FirstFragment extends Fragment {
    private boolean isLandscape;

    static final String KEY_NAME = "1";
    private String textDate = "";
    private String textName = "";
    private String textNote = "";

    Bundle b;


    public static FirstFragment newInstance(Bundle b) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putBundle(KEY_NAME, b);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = getArguments().getBundle(KEY_NAME); // получаю bundle, переданный через newInstance
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!b.isEmpty()) {
            TextView dateField = view.findViewById(R.id.dateText);
            TextView nameField = view.findViewById(R.id.nameText);

            textDate = "";
            textName = "";
            textNote = "";

            // В bundle перебираю ключи, забираю данные, сохраненные с этими ключами, формирую строку и показываю итог в текстовом поле
            for(String key:b.keySet()) {

                NoteData noteData = b.getParcelable(key);
                textDate = textDate + noteData.getDataTimeNote() + "\n";
                textName = textName + noteData.getNameNote() + "\n";
                textNote = textNote + noteData.getNameNote() + "\n";

            };

            dateField.setText(textDate);
            nameField.setText(textName);
        }

        LinearLayout textLinearLayout = view.findViewById(R.id.textLinearLayout);
        textLinearLayout.setOnClickListener(new View.OnClickListener() { // по клику на текст - переход в secondActivity
            @Override
            public void onClick(View view) {
                // Откроем вторую activity
                Intent intent = new Intent();
                intent.setClass(getActivity(), SecondActivity.class);
                // и передадим туда параметры
                intent.putExtra(KEY_NAME, b);
                startActivity(intent);
            }
        });
    }


}