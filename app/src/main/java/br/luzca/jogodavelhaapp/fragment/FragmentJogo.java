package br.luzca.jogodavelhaapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

import br.luzca.jogodavelhaapp.R;
import br.luzca.jogodavelhaapp.databinding.FragmentJogoBinding;


public class FragmentJogo extends Fragment {
    private FragmentJogoBinding binding;
    private Button[] botooes;
    private String[][] tabuleiro;
    private String simbJ1, simbJ2, simbolo;
    private Random random;
    private int numJogadas = 0;

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
        tabuleiro = new String[3][3];
        for (String[] vetor : tabuleiro){
            Arrays.fill(vetor,"");
        }

        random = new Random();
        simbJ1 = "X";
        simbJ2 = "O";

        sorteia();
//       returnar a view do Fragment
        return binding.getRoot();

    }
    private  void sorteia(){
        if (random.nextBoolean()){
            simbolo = simbJ1;
        }else {
            simbolo = simbJ2;
        }
    }
    private void atualizaVez(){
        if (simbolo.equals(simbJ1)){
            binding.idpaij2.setBackgroundColor(getResources().getColor(R.color.white));
            binding.idPaiJ1.setBackgroundColor(getResources().getColor(R.color.black));
        }else{
            binding.idPaiJ1.setBackgroundColor(getResources().getColor(R.color.white));
            binding.idpaij2.setBackgroundColor(getResources().getColor(R.color.black));
        }

    }
    private boolean venceu(){
        for (int i = 0;i < 3; i++) {
            if (tabuleiro[i][0].equals(simbolo)
                    && tabuleiro[i][1].equals(simbolo)
                    && tabuleiro[i][2].equals(simbolo)) {
                return true;
            }
        }

        for (int i = 0;i < 3; i++){
            if (tabuleiro[0][i].equals(simbolo)
                    && tabuleiro[1][i].equals(simbolo)
                    && tabuleiro[2][i].equals(simbolo)){
                return true;
            }
        }

            if (tabuleiro[0][0].equals(simbolo)
                    && tabuleiro[1][1].equals(simbolo)
                    && tabuleiro[2][2].equals(simbolo)){
                return true;
            }
            if (tabuleiro[0][2].equals(simbolo)
                    && tabuleiro[1][1].equals(simbolo)
                    && tabuleiro[2][0].equals(simbolo)){
                return true;
            }

        return false;
    }
    private void resetar(){
        for (String[] vetor : tabuleiro){
            Arrays.fill(vetor,"");
        }
        for (Button botao : botooes){
            botao.setBackgroundColor(getResources().getColor(R.color.color200));
            botao.setText("");
            botao.setClickable(true);
        }
        sorteia();
        atualizaVez();
        numJogadas = 0;
    }

    private View.OnClickListener listenerBotoes = btPress -> {
        numJogadas++;
        //pega o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());
        //extrai os 2 últimos caracteres do nomeBotao
        String posicao = nomeBotao.substring(nomeBotao.length()-2);
        //extrai a posição em linha e coluna
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        tabuleiro[linha][coluna] = simbolo;
        Button botao = (Button) btPress;
        botao.setText(simbolo);
        botao.setClickable(false);
        botao.setBackgroundColor(Color.WHITE);
        if(numJogadas >= 5 && venceu()) {
            //exibe um Toast informando que o jogador veneu
            Toast.makeText(getContext(), R.string.parabens, Toast.LENGTH_LONG).show();
            resetar();

        }else if (numJogadas == 9){
            Toast.makeText(getContext(), R.string.parabensV, Toast.LENGTH_LONG).show();
            resetar();
        }else{
            simbolo = simbolo.equals(simbJ1)? simbJ2 : simbJ1;
            atualizaVez();

        }


    };

}