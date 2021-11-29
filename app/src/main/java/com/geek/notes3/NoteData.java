package com.geek.notes3;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteData implements Parcelable {
    private String dataTimeNote;
    private String nameNote;
    private String nameText;

    public NoteData(){
    }

    public NoteData(String dataTimeNote, String nameNote, String nameText) {
        this.dataTimeNote = dataTimeNote;
        this.nameNote = nameNote;
        this.nameText = nameText;
    }

    protected NoteData(Parcel in) {
        dataTimeNote = in.readString();
        nameNote = in.readString();
        nameText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataTimeNote);
        dest.writeString(nameNote);
        dest.writeString(nameText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };

    public String getDataTimeNote() {
        return dataTimeNote;
    }

    public void setDataTimeNote(String dataTimeNote) {
        this.dataTimeNote = dataTimeNote;
    }

    public String getNameNote() {
        return nameNote;
    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

}


// 1. Метод запихивания в bundle с уникальным ключом
//      это лучше делать в EntryNoteFragment
// 2. Метод доставания из bundle. Перебор уникальных ключей, вывод в FirstFrag
//      это лучше делать в FirstFragment

// Класс работы с Bundle
//  классу передаю parcelable
//  класс получает из main активити или FirstFrag актуальный bundle
//  в bundle находит очередной свободный ключ
//  сохраняет parcelable в bundle со свободным ключом
//  bundle передает в main активити или FirstFrag newInstance

// в FirstFrag  bundle