package com.aliendroid.tescovid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aliendroid.covid17.R;
import com.aliendroid.covid17.Ui.MainActivity;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class GameActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewsemuasoal;

    private Button buttonTrue;
    private Button buttonFalse;
    private Quiz game;
    int soalke=1;
    public static final String TAG = GameActivity.class.getSimpleName();
    int scorehitungan =0;
    @Override
    public String toString() {

        return super.toString();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        SharedPreferences Myscorehitungan = this.getSharedPreferences("Myscoretes", Context.MODE_PRIVATE);
        scorehitungan = Myscorehitungan.getInt("scoretes", 0);

        TextView txthasil = findViewById(R.id.textView15);
        if (scorehitungan==0) {
            txthasil.setText("Selesaikan semua pertanyaan untuk menampilkan hasil tes.");
        } else if (scorehitungan<=7) {
            txthasil.setText("Pengetahuan anda tentang Covid19 sangat minim, hasil tes menunjukan anda belum bisa membedakan antara fakta dan hoax covid19.");

        } else {
            txthasil.setText("Pengetahuan anda tentang Covid19 sangat bagus, hasil tes menunjukan anda suda bisa membedakan antara fakta dan hoax covid19.");

        }
        wireWidgets();
        setListeners();

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.questions); // getting XML
        String jsonString = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
        Question[] questions = gson.fromJson(jsonString, Question[].class);
        List<Question> questionList = Arrays.asList(questions);
        Log.d(TAG, "onCreate: " + questionList.toString());

        game = new Quiz(questionList);

        textViewsemuasoal.setText(Integer.toString(questionList.size()-1));

        updateGameDisplay();
    }

    private void updateGameDisplay() {


        textViewQuestion.setText(String.valueOf(game.getListOfQuestions().get(game.getCurrentQuestion() - 1).getQuestion()));

        textViewScore.setText("Soal ke : " + soalke);

    }

    private void wireWidgets() {
        textViewsemuasoal = findViewById(R.id.txtsemuasoal);
        textViewQuestion = findViewById(R.id.textView_main_question);
        textViewScore = findViewById(R.id.textView_main_score);
        buttonTrue = findViewById(R.id.button_main_true);
        buttonFalse = findViewById(R.id.button_main_false);

    }

    private void setListeners() {
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.checkAnswer(true);
                if(!game.hasQuestions()) {

                    int scorealam = game.getScoretest();
                    SharedPreferences Myscorealam = getSharedPreferences("Myscoretes", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorscorealam = Myscorealam.edit();
                    editorscorealam.putInt("scoretes", scorealam);
                    editorscorealam.apply();

                    Intent targetIntent = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(targetIntent);
                    finish();
                }
                soalke++;

                updateGameDisplay();
            }
        });
        buttonFalse.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                game.checkAnswer(false);
                if(!game.hasQuestions()) {
                    int scorealam = game.getScoretest();
                    SharedPreferences Myscorealam = getSharedPreferences("Myscoretes", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editorscorealam = Myscorealam.edit();
                    editorscorealam.putInt("scoretes", scorealam);
                    editorscorealam.apply();


                    Intent targetIntent = new Intent(GameActivity.this, MainActivity.class);
                    startActivity(targetIntent);
                    finish();
                }
                soalke++;

                updateGameDisplay();
            }
        });
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}