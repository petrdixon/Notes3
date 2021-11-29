package com.geek.notes3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EntryNoteFragment extends Fragment {

    NoteData savedNotes = new NoteData();
    static final String KEY_NAME = "keyName";
    int keyNoteInBundle = 0;
    private TextInputEditText noteName;
    private TextInputEditText noteText;
    private Button addNoteButton;
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    Bundle b;

    public EntryNoteFragment() {
        // Required empty public constructor
    }

    public static EntryNoteFragment newInstance(Bundle b) {
        EntryNoteFragment fragment = new EntryNoteFragment();
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
        return inflater.inflate(R.layout.fragment_entry_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteName = view.findViewById(R.id.noteName);
        noteText = view.findViewById(R.id.noteText);
        addNoteButton = view.findViewById(R.id.addNoteButton);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedNotes.setDataTimeNote(timeStamp);
                savedNotes.setNameNote(String.valueOf(noteName.getText()));
                savedNotes.setNameText(String.valueOf(noteText.getText()));

//              беру данные из полей и кладу в bundle с уникальным свободным ключом
                int keysQuantity = b.size();
                b.putParcelable(String.valueOf(keysQuantity + 1), savedNotes); // кладу Parcelable Data в Bundle c очередным свободным ключом
                System.out.println("b.size() " + b.size());



                Fragment fragment1 = getActivity().getSupportFragmentManager()
                        .findFragmentByTag("firstFragTag");

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.firstFragment, fragment1)
                        .addToBackStack(null)
                        .commit();


            }
        });
    }
}