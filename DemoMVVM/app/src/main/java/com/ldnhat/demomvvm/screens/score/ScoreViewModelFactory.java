package com.ldnhat.demomvvm.screens.score;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ScoreViewModelFactory implements ViewModelProvider.Factory {

    private int finalScore;

    public ScoreViewModelFactory(int finalScore) {
        this.finalScore = finalScore;
        System.out.println(finalScore);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ScoreViewModel.class)){
            return (T) new ScoreViewModel(finalScore);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
