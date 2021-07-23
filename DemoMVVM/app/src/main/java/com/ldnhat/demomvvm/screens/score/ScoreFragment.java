package com.ldnhat.demomvvm.screens.score;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ldnhat.demomvvm.R;
import com.ldnhat.demomvvm.databinding.ScoreFragmentBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ScoreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ScoreFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.score_fragment, container, false);

        ScoreViewModelFactory scoreViewModelFactory = new ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(getArguments()).getScore());
        ScoreViewModel scoreViewModel = new ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel.class);

        binding.setScoreViewModel(scoreViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        scoreViewModel.eventPlayAgain.observe(getViewLifecycleOwner(), playAgain -> {
            if (playAgain){
                Navigation.findNavController(requireView()).navigate(ScoreFragmentDirections.actionRestart());
                scoreViewModel.onPlayAgainComplete();
            }
        });


        return binding.getRoot();
    }
}
