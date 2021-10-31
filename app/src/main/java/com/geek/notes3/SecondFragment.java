package com.geek.notes3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    static final String KEY_NAME = "keyName";
    Bundle b;
    private String textNote;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(Bundle b) {
        SecondFragment fragment = new SecondFragment();
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
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!b.isEmpty()) {
            System.out.println("in Second Bundle NOT Empty");
        } else {
            System.out.println("in Second Bundle EMPTY");
        }

        if (!b.isEmpty()) {
            NoteData noteData = b.getParcelable("key1");
            textNote = noteData.getNameText();
            System.out.println("in Second/ textNote " + textNote);

            TextView dateField = view.findViewById(R.id.noteText);
            dateField.setText("");
            dateField.setText(textNote);

        }
    }
}