package com.example.calc;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText userAnswerInput;
    private TextView firstNumberTextView, secondNumberTextView;
    private TextView scoreCorrectTextView, scoreIncorrectTextView;
    private int correctCount = 0;
    private int incorrectCount = 0;
    private int num1, num2;
    private String selectedDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        userAnswerInput = findViewById(R.id.user_answer_input);
        firstNumberTextView = findViewById(R.id.first_number);
        secondNumberTextView = findViewById(R.id.second_number);
        scoreCorrectTextView = findViewById(R.id.score_correct);
        scoreIncorrectTextView = findViewById(R.id.score_incorrect);

        Button basicButton = findViewById(R.id.button_basic);
        Button mediumButton = findViewById(R.id.button_medium);
        Button professionalButton = findViewById(R.id.button_professional);
        Button checkAnswerButton = findViewById(R.id.button_check_answer);


        basicButton.setOnClickListener(view -> {
            selectedDifficulty = "BASIC";
            generateRandomNumbers();
        });

        mediumButton.setOnClickListener(view -> {
            selectedDifficulty = "MEDIUM";
            generateRandomNumbers();
        });

        professionalButton.setOnClickListener(view -> {
            selectedDifficulty = "PROFESSIONAL";
            generateRandomNumbers();
        });


        checkAnswerButton.setOnClickListener(view -> checkAnswer());
    }

    private void generateRandomNumbers() {
        Random random = new Random();

        switch (selectedDifficulty) {
            case "BASIC":

                num1 = random.nextInt(9) + 1;
                num2 = random.nextInt(9) + 1;
                break;
            case "MEDIUM":

                num1 = random.nextInt(9) + 1;
                num2 = random.nextInt(20) + 1;
                break;
            case "PROFESSIONAL":

                num1 = random.nextInt(9) + 1;
                num2 = random.nextInt(99) + 1;
                break;
        }


        firstNumberTextView.setText(String.valueOf(num1));
        secondNumberTextView.setText(String.valueOf(num2));
    }

    private void checkAnswer() {

        String userAnswerStr = userAnswerInput.getText().toString();
        if (userAnswerStr.isEmpty()) {
            Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        int userAnswer = Integer.parseInt(userAnswerStr);
        int correctAnswer = num1 * num2;

        if (userAnswer == correctAnswer) {

            correctCount++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {

            incorrectCount++;
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }


        scoreCorrectTextView.setText("Correct: " + correctCount);
        scoreIncorrectTextView.setText("Incorrect: " + incorrectCount);


        userAnswerInput.setText("");
    }
}
