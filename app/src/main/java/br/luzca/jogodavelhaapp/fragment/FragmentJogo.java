package br.luzca.jogodavelhaapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.luzca.jogodavelhaapp.R;
import br.luzca.jogodavelhaapp.databinding.FragmentJogoBinding;


public class FragmentJogo extends Fragment {
    private FragmentJogoBinding binding;
    private Button[] botooes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentJogoBinding.inflate(inflater,container, false);

//       istancia p vetor
        botooes = new Button[9];
//       agrupa os botões no  vetor
        botooes[0] = binding.bt00;
        botooes[1] = binding.bt01;
        botooes[2] = binding.bt02;
        botooes[3] = binding.bt10;
        botooes[4] = binding.bt11;
        botooes[5] = binding.bt12;
        botooes[6] = binding.bt20;
        botooes[7] = binding.bt21;
        botooes[8] = binding.bt22;
//        associa o listener aos botões
        for (Button bt : botooes){
            bt.setOnClickListener(listenerBotoes);
        }
//       returnar a view do Fragment
        return binding.getRoot();

    }
    private View.OnClickListener listenerBotoes = btPress -> {
        //pega o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());
        //extrai os 2 últimos caracteres do nomeBotao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai a posição em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));
        
    };

}