package com.geek.notes3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    Bundle b = new Bundle();

    FirstFragment firstFragment = FirstFragment.newInstance(b); // создаю объект без new. С instance
    SecondFragment secondFragment = SecondFragment.newInstance(b);
    EntryNoteFragment entryNoteFragment = EntryNoteFragment.newInstance(b);
    NoteData savedNotes = new NoteData();
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

    private TextInputEditText noteName;
    private TextInputEditText noteText;
    private Button addNote;

    public int x = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            b = savedInstanceState.getBundle("keyBundle");
            NoteData noteData = b.getParcelable("key1");
            Log.d("in Create", noteData.getNameNote());
        }

        Button button = (Button) findViewById(R.id.button);
        noteName = findViewById(R.id.noteName);
        noteText = findViewById(R.id.noteText);
        addNote = findViewById(R.id.addNoteButton);

        boolean isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        firstFragment = FirstFragment.newInstance(b);
        secondFragment = SecondFragment.newInstance(b);
        if (!isLandscape) {
            addFirstFrag(firstFragment);
        } else if (isLandscape) {
            addFirstSecondFrag(firstFragment, secondFragment);
        }

        button.setOnClickListener(new View.OnClickListener() { // кнопка добавления заметки, чтобы не вводить вручную
            @Override
            public void onClick(View v) {

                savedNotes.setDataTimeNote(timeStamp);
                savedNotes.setNameNote("Name 1");
                savedNotes.setNameText("This Text 1");

                b.putParcelable("key1", savedNotes); // кладу Parcelable Data в Bundle

                firstFragment = FirstFragment.newInstance(b);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.firstFragment, firstFragment).commit();
            }
        });


    }

    // Сохранение данных
    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putBundle("keyBundle", b);
    }

    // Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        b = instanceState.getBundle("keyBundle");

    }

    @Override
    protected void onPause() {
        super.onPause();
        removeAllFrags(firstFragment, secondFragment);
    }

    private void addFirstFrag(Fragment firstFrag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.firstFragment, firstFrag, "firstFragTag").addToBackStack(null).commit();
    }

    private void addFirstSecondFrag(Fragment firstFrag, Fragment secondFrag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.firstFragment, firstFrag, "firstFragTag").commit();
        fragmentManager.beginTransaction().add(R.id.secondFragment, secondFrag, "secondFragTag").commit();
    }

    private void removeAllFrags(Fragment firstFrag, Fragment secondFrag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Открыть транзакцию
        fragmentManager.beginTransaction().remove(firstFragment).commit();
        if (secondFrag.isVisible()) {
            fragmentManager.beginTransaction().remove(secondFragment).commit();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem addNoteItem = menu.findItem(R.id.addNoteItem); // объект пункта меню

        addNoteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // Listener на пункт меню
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Fragment fragment1 = getSupportFragmentManager()
                        .findFragmentByTag("firstFragTag");

                Fragment fragment2 = getSupportFragmentManager()
                        .findFragmentByTag("secondFragTag");

                if(fragment2 != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .hide(fragment2)
                            .addToBackStack(null)
                            .commit();
                };
                entryNoteFragment = EntryNoteFragment.newInstance(b);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.firstFragment, entryNoteFragment, "EntryNoteFragTag")
                        .addToBackStack(null)
                        .commit();


                return false;
            }
        });
        return true;
    }

}

