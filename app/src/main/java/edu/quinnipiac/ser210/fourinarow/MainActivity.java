package edu.quinnipiac.ser210.fourinarow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view) {
        TextView welcomeText = (TextView) findViewById(R.id.textView);
        TextView nameText = (TextView) findViewById(R.id.textView2);
        EditText nameValue = (EditText) findViewById(R.id.userName);
        String name = nameValue.getText().toString();

        Intent intent = new Intent(this, StartGameActivity.class);
        intent.putExtra("nameKey", name);
        startActivity(intent);

    }
}