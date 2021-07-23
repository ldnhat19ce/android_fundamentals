package com.ldnhat.demomvvm.screens.score;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {

    MutableLiveData<Integer> score = new MutableLiveData<>();
    MutableLiveData<Boolean> eventPlayAgain = new MutableLiveData<>();

    public ScoreViewModel(int finalScore) {
        score.setValue(finalScore);
        System.out.println("score view model: "+finalScore);
    }

    public void onPlayAgain(){
        eventPlayAgain.setValue(true);
    }

    public void onPlayAgainComplete(){
        eventPlayAgain.setValue(false);
    }

    public MutableLiveData<Integer> getScore() {
        return score;
    }

}
