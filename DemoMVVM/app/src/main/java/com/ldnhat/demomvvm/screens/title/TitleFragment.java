package com.ldnhat.demomvvm.screens.title;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ldnhat.demomvvm.R;
import com.ldnhat.demomvvm.databinding.TitleFragmentBinding;


public class TitleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TitleFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.title_fragment, container, false);
        binding.playGameButton.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(TitleFragmentDirections.actionTitleToGame()));
        return binding.getRoot();
    }
}
