package com.ldnhat.demomvvm.screens.game;

import android.os.CountDownTimer;
import android.text.format.DateUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.ldnhat.demomvvm.TimeUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameViewModel extends ViewModel {

     MutableLiveData<String> word = new MutableLiveData<>();
     MutableLiveData<Integer> _score = new MutableLiveData<>();
     List<String> wordList;

     MutableLiveData<Long> currentTime = new MutableLiveData<>();
     CountDownTimer timer = new CountDownTimer(TimeUtil.COUNTDOWN_TIME, TimeUtil.ONE_SECOND) {
         @Override
         public void onTick(long millisUntilFinished) {
            currentTime.setValue(millisUntilFinished/TimeUtil.ONE_SECOND);
         }

         @Override
         public void onFinish() {
            currentTime.setValue(TimeUtil.DONE);
            onGameFinish();
         }
     }.start();

     public LiveData<String> currentTimeString = Transformations.map(currentTime, DateUtils::formatElapsedTime);

    public LiveData<Integer> getScore() {
        return _score;
    }

    MutableLiveData<Boolean> eventGameFinish = new MutableLiveData<>();

    {
        Log.i("GameViewModel", "GameViewModel created!");
        word.setValue("");
        _score.setValue(0);
        resetList();
        nextWord();
    }

    public void onGameFinish(){
        eventGameFinish.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("GameViewModel", "GameViewModel destroyed!");
        timer.cancel();
    }

    public void onSkip() {
        _score.setValue(_score.getValue()!=null?_score.getValue()-1:0);
        nextWord();
    }

    public void onCorrect() {
        _score.setValue(_score.getValue()!=null?_score.getValue()+1:0);
        nextWord();
    }

    public void nextWord() {
        if (wordList.isEmpty()) {
            resetList();
            //Select and remove a word from the list
        }else{
            word.setValue(wordList.remove(0));
        }
    }

    public void resetList() {
        wordList = new LinkedList<>(Arrays.asList(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        ));
        Collections.shuffle(wordList);
    }

    void onGameFinishComplete(){
        eventGameFinish.setValue(false);
    }

    public MutableLiveData<String> getWord() {
        return word;
    }
}
