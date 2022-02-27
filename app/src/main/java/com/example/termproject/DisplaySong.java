package com.example.termproject;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplaySong extends AppCompatActivity {
    private DBHelper mydb;
    TextView name;
    TextView year;
    TextView lyric;
    TextView composer;
    TextView album;
    int id = 0;
    EditText edit_name;
    EditText edit_year;
    EditText edit_lyric;
    EditText edit_composer;
    EditText edit_album;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_music);
        name = (TextView) findViewById(R.id.editTextName);
        year = (TextView) findViewById(R.id.editTextYear);
        lyric = (TextView) findViewById(R.id.editTextlyric);
        composer = (TextView) findViewById(R.id.editTextcomposer);
        album = (TextView) findViewById(R.id.editTextAlbum);

        edit_name = (EditText)findViewById(R.id.editTextName);
        edit_year = (EditText)findViewById(R.id.editTextYear);
        edit_lyric = (EditText)findViewById(R.id.editTextlyric);
        edit_composer = (EditText)findViewById(R.id.editTextcomposer);
        edit_album = (EditText)findViewById(R.id.editTextAlbum);

        mydb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id = Value;
                rs.moveToFirst();
                String n = rs.getString(rs.getColumnIndex(DBHelper.SONGS_COLUMN_NAME));
                String y = rs.getString(rs.getColumnIndex(DBHelper.SONGS_COLUMN_YEAR));
                String ly = rs.getString(rs.getColumnIndex(DBHelper.SONGS_COLUMN_LYRIC));
                String c = rs.getString(rs.getColumnIndex(DBHelper.SONGS_COLUMN_COMPOSER));
                String al = rs.getString(rs.getColumnIndex(DBHelper.SONGS_COLUMN_ALBUM));
                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button1);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) n);
                year.setText((CharSequence) y);
                lyric.setText((CharSequence) ly);
                composer.setText((CharSequence) c);
                album.setText((CharSequence) al);

            }
        }
    }

    public void insert(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                if (mydb.updateSong(id, name.getText().toString(), year.getText().toString(), lyric.getText().toString(), composer.getText().toString(), album.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), com.example.termproject.IntroActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (mydb.insertSong(name.getText().toString(), year.getText().toString(), lyric.getText().toString(), composer.getText().toString(), album.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "추가되었음", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "추가되지 않았음", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        }
    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                mydb.deleteSong(id);
                Toast.makeText(getApplicationContext(), "삭제되었음", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "삭제되지 않았음", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void search(View view) {
                    String searchName = edit_name.getText().toString();
                    mydb.searchSong(searchName);
                    edit_name.setText(mydb.s_name);
                    edit_year.setText(mydb.s_year);
                    edit_lyric.setText(mydb.s_lyric);
                    edit_composer.setText(mydb.s_composer);
                    edit_album.setText(mydb.s_album);
    }

    public void edit(View view) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt("id");
            if (value > 0) {
                if (mydb.updateSong(id, name.getText().toString(), year.getText().toString(), lyric.getText().toString(), composer.getText().toString(), album.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "수정되었음", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "수정되지 않았음", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
