package com.example.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.termproject.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ListView list;
    String[] titles = {
            "Face",
            "Action",
            "여보세요",
            "잠꼬대",
            "Re:BIRTH",
            "I'm Bad",
            "Q is.",
            "CANVAS",
            "Happily Ever After",
            "The Table",
            "The Nocturne"
    };
    String[] EngTitles = {
            "Face",
            "Action",
            "Hello",
            "sleep talking",
            "Re:BIRTH",
            "I'm Bad",
            "Q is.",
            "CANVAS",
            "Happily Ever After",
            "The Table",
            "The Nocturne"
    };
    String[] albumTypes = {
            "싱글",
            "미니",
            "미니",
            "미니",
            "정규",
            "스페셜 싱글",
            "미니",
            "미니",
            "미니",
            "미니",
            "미니"
    };
    String[] releaseYears = {
            "2012",
            "2012",
            "2013",
            "2013",
            "2014",
            "2015",
            "2016",
            "2016",
            "2019",
            "2019",
            "2020"
    };

    Integer[] albumcovers = {
            R.drawable.albumcover1,
            R.drawable.albumcover2,
            R.drawable.albumcover3,
            R.drawable.albumcover4,
            R.drawable.albumcover5,
            R.drawable.albumcover6,
            R.drawable.albumcover7,
            R.drawable.albumcover8,
            R.drawable.albumcover9,
            R.drawable.albumcover10,
            R.drawable.albumcover11
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.bad);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        CustomList adapter = new CustomList(MainActivity.this);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),titles[+position],Toast.LENGTH_SHORT).show();
                if(position == 4) {
                    Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    public class CustomList extends ArrayAdapter<String>{
        private final Activity context;
        public CustomList(MainActivity context){
            super(context, R.layout.listitem, titles);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.listitem, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.albumcover);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView EngTitle = (TextView) rowView.findViewById(R.id.EngTitle);
            TextView albumType = (TextView) rowView.findViewById(R.id.albumType);
            TextView releaseYear = (TextView) rowView.findViewById(R.id.releaseYear);



            imageView.setImageResource(albumcovers[position]);
            title.setText(titles[position]);
            EngTitle.setText(EngTitles[position]);
            albumType.setText(albumTypes[position]);
            releaseYear.setText(releaseYears[position]);
            return rowView;
        }
    }
}