package com.ldnhat.demomvvm.screens.game;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.ldnhat.demomvvm.R;
import com.ldnhat.demomvvm.databinding.GameFragmentBinding;

import java.util.List;

public class GameFragment extends Fragment {

    private String word = "";
    private int score = 0;
    private List<String> wordList;
    private GameFragmentBinding binding;
    private GameViewModel gameViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false);

        Log.i("GameFragment", "Called ViewModelProviders.of");
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        binding.setGameViewModel(gameViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        gameViewModel.eventGameFinish.observe(getViewLifecycleOwner(), hasFinished ->{
            if (hasFinished){
                gameFinish();
            }
        }
        );

        return binding.getRoot();
    }

    private void gameFinish(){
        Toast.makeText(getActivity(), "Game has just finished", Toast.LENGTH_SHORT).show();
        GameFragmentDirections.ActionGameToScore action = GameFragmentDirections.actionGameToScore();
        action.setScore(gameViewModel.getScore().getValue()!=null?gameViewModel.getScore().getValue():0);
        NavHostFragment.findNavController(this).navigate(action);
        gameViewModel.onGameFinishComplete();
    }


}
