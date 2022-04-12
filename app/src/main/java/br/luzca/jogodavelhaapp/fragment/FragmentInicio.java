package br.luzca.jogodavelhaapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import br.luzca.jogodavelhaapp.R;
import br.luzca.jogodavelhaapp.databinding.FragmentInicioBinding;

public class FragmentInicio extends Fragment {

    private FragmentInicioBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentInicioBinding.inflate(inflater, container, false);
        binding.btIniciar.setOnClickListener(view -> {
            NavHostFragment.findNavController(FragmentInicio.this).
                    navigate(R.id.action_fragmentInicio_to_fragmentJogo);
        });


        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
        // para sumir a toolbar
        // pegar uma referencia do tipo AppCompatActivity
        AppCompatActivity minhaActivity = (AppCompatActivity) getActivity();
        minhaActivity.getSupportActionBar().hide();
    }
}