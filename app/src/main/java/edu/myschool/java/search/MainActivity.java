package edu.myschool.java.search;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch swNewsFilter = (Switch)findViewById(R.id.swNewsFilter);
        swNewsFilter.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton switchButton, boolean isChecked) {
                RadioButton rbCnn = (RadioButton)findViewById(R.id.rbCNN);
                if(isChecked){
                    rbCnn.setVisibility(View.VISIBLE);
                } else {
                    rbCnn.setVisibility(View.INVISIBLE);
                }
            }
        });

        Button btnBrowser = (Button)findViewById(R.id.btnBrowser);
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rgButtons = (RadioGroup)findViewById(R.id.rgButtons);

                int selected = rgButtons.getCheckedRadioButtonId();
                RadioButton rbSelected = (RadioButton)findViewById(selected);

                StringBuilder url = new StringBuilder();
                String engine = rbSelected.getText().toString();

                EditText textSearch = (EditText)findViewById(R.id.textSearch);
                String searchString = textSearch.getText().toString();

                switch (engine){
                    case "DuckDuckGo":
                        url.append("https://duckduckgo.com/?q=" + searchString);
                        if(swNewsFilter.isChecked()){
                            url.append("&ia=news&iar=news");
                        }
                        break;
                    case "Google":
                        url.append("https://www.google.com/search?q=" + searchString);
                        if(swNewsFilter.isChecked()){
                            url.append("&tbm=nws");
                        }
                        break;
                    case "CNN":
                        url.append("https://www.cnn.com/SEARCH/?size=20&q=" + searchString);
                        break;

                }

                Uri uri = Uri.parse(url.toString());
                Intent goSearch = new Intent(Intent.ACTION_VIEW, uri);
                if(goSearch.resolveActivity(getPackageManager()) != null){
                    startActivity(goSearch);
                }
            }
        });


        Button btnInternal = (Button)findViewById(R.id.btnInternal);
        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rgButtons = (RadioGroup)findViewById(R.id.rgButtons);
                int selected = rgButtons.getCheckedRadioButtonId();
                RadioButton rbSelected = (RadioButton)findViewById(selected);
                String engine = rbSelected.getText().toString();
                StringBuilder url = new StringBuilder();
                EditText textSearch = (EditText)findViewById(R.id.textSearch);
                String searchString = textSearch.getText().toString();
                switch (engine){
                    case "DuckDuckGo":
                        url.append("https://duckduckgo.com/?q=" + searchString);
                        if(swNewsFilter.isChecked()){
                            url.append("&ia=news&iar=news");
                        }
                        break;
                    case "Google":
                        url.append("https://www.google.com/search?q=" + searchString);
                        if(swNewsFilter.isChecked()){
                            url.append("&tbm=nws");
                        }
                        break;
                    case "CNN":
                        url.append("https://www.cnn.com/SEARCH/?size=20&q=" + searchString);
                        break;

                }

                Intent internalIntent = new Intent(getApplicationContext(), SecondActivity.class);
                internalIntent.putExtra("edu.myschool.java.newssearch", url.toString());
                startActivity(internalIntent);
            }
        });



    }
}
