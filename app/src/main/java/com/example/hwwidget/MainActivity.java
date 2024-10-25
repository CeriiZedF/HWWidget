package com.example.hwwidget;

import static com.example.hwwidget.utils.AppConstant.CARD_ID_ELEMENT;
import static com.example.hwwidget.utils.AppConstant.CARD_TEXT_TASK1;
import static com.example.hwwidget.utils.AppConstant.CARD_TEXT_TASK2;
import static com.example.hwwidget.utils.AppConstant.CARD_TEXT_TASK3;
import static com.example.hwwidget.utils.AppConstant.CARD_TEXT_TASK4;
import static com.example.hwwidget.utils.AppConstant.CARD_TEXT_TASK5;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import com.example.hwwidget.utils.MyTask;


public class MainActivity extends AppCompatActivity {
    private LinearLayout taskContainer;
    private CheckBox checkBoxExperience;
    private CheckBox checkBoxTeamSkills;
    private CheckBox checkBoxTravel;
    private TextView resultTextView;
    private EditText editTextInputPib;
    private EditText editTextInputAge;
    private Button submitButton;

    private final List<MyTask> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        taskContainer = findViewById(R.id.task_container);
        checkBoxExperience = findViewById(R.id.experienceCheckbox);
        checkBoxTeamSkills = findViewById(R.id.teamworkCheckbox);
        checkBoxTravel = findViewById(R.id.travelCheckbox);
        resultTextView = findViewById(R.id.resultTextView);
        submitButton = findViewById(R.id.submitButton);

        editTextInputPib = findViewById(R.id.edit_text_input_pib);
        editTextInputAge = findViewById(R.id.edit_text_input_age);

        submitButton.setOnClickListener(v -> {
            if (validateInputs()) {
                String pib = editTextInputPib.getText().toString().trim();
                int age = Integer.parseInt(editTextInputAge.getText().toString().trim());

                if (age < 21 || age > 40) {
                    resultTextView.setText("Возраст не подходит.");
                    resultTextView.setVisibility(View.VISIBLE);
                    return;
                }

                resultTextView.setText("ПІБ: " + pib + ", Возраст: " + age);
                Toast.makeText(this, "Данные успешно отправлены", Toast.LENGTH_SHORT).show();
                calculateScore();
                lockAnswers();
            } else {
                resultTextView.setText("Ошибка в данных. Проверьте введенные значения.");
            }
        });


        // Додавання задач
        addTask(CARD_ID_ELEMENT, CARD_TEXT_TASK1, 0);
        addTask(CARD_ID_ELEMENT, CARD_TEXT_TASK2, 0);
        addTask(CARD_ID_ELEMENT, CARD_TEXT_TASK3, 0);
        addTask(CARD_ID_ELEMENT, CARD_TEXT_TASK4, 1);
        addTask(CARD_ID_ELEMENT, CARD_TEXT_TASK5, 0);
    }

    private boolean validateInputs() {
        boolean isValid = true;

        String pib = editTextInputPib.getText().toString().trim();
        if (TextUtils.isEmpty(pib)) {
            editTextInputPib.setError("Поле ПІБ не должно быть пустым");
            isValid = false;
        }

        String ageStr = editTextInputAge.getText().toString().trim();
        if (TextUtils.isEmpty(ageStr)) {
            editTextInputAge.setError("Поле возраста не должно быть пустым");
            isValid = false;
        } else {
            try {
                int age = Integer.parseInt(ageStr);
                if (age < 1 || age > 120) {
                    editTextInputAge.setError("Возраст должен быть от 1 до 120");
                    isValid = false;
                }
            } catch (NumberFormatException e) {
                editTextInputAge.setError("Введите корректное число для возраста");
                isValid = false;
            }
        }

        return isValid;
    }

    private void addTask(List<String> elementId, List<String> texts, int correctAnswerIndex) {
        View taskView = LayoutInflater.from(this).inflate(R.layout.card_task, taskContainer, false);
        TextView taskTitleTextView = taskView.findViewById(R.id.task_text);
        taskTitleTextView.setText(texts.get(0));

        for (int i = 0; i < elementId.size(); i++) {
            String myId = elementId.get(i);
            String radioText = texts.get(i + 1);
            int resId = getResources().getIdentifier(myId, "id", getPackageName());
            RadioButton radioButton = taskView.findViewById(resId);
            radioButton.setText(radioText);

            radioButton.setTag(i);
        }

        tasks.add(new MyTask(taskView, correctAnswerIndex));
        taskContainer.addView(taskView);
    }

    private void calculateScore() {
        int score = 0;

        for (MyTask task : tasks) {
            LinearLayout radioGroup = task.taskView.findViewById(R.id.radio_group);
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if (radioButton.isChecked() && (int) radioButton.getTag() == task.correctAnswerIndex) {
                    score += 2;
                    break;
                }
            }
        }

        if (checkBoxExperience.isChecked()) score += 2;
        if (checkBoxTeamSkills.isChecked()) score += 1;
        if (checkBoxTravel.isChecked()) score += 1;

        resultTextView.setText(score >= 10
                ? "Тест пройдено! Набрано балів: " + score
                : "Тест не пройдено. Набрано балів: " + score);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private void lockAnswers() {
        for (MyTask task : tasks) {
            LinearLayout radioGroup = task.taskView.findViewById(R.id.radio_group);
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                radioGroup.getChildAt(i).setEnabled(false);
            }
        }

        checkBoxExperience.setEnabled(false);
        checkBoxTeamSkills.setEnabled(false);
        checkBoxTravel.setEnabled(false);

        submitButton.setEnabled(false);
    }
}
