package com.example.hwwidget.utils;

import android.view.View;

public class MyTask {
    public View taskView;
    public int correctAnswerIndex;

    public MyTask(View taskView, int correctAnswerIndex) {
        this.taskView = taskView;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
