package br.com.opet.launchdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekDados;
    private TextView quantidadeDados;
    private SeekBar seekFaces;
    private TextView quantidadeFaces;
    private TextView resultDice;
    private ArrayList<Dado> listDados;
    private Random random;
    private Button fazerJogada;
    private TextView dados;
    public int finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekDados = findViewById(R.id.seekDados);
        quantidadeDados = findViewById(R.id.quantidadeDados);

        seekFaces = findViewById(R.id.seekFaces);
        quantidadeFaces = findViewById(R.id.quantidadeFaces);

        resultDice = findViewById(R.id.resultDice);

        fazerJogada = findViewById(R.id.fazerJogada);

        dados = findViewById(R.id.dados);

        listDados = new ArrayList<Dado>();

        random = new Random();

        seekDados.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantidadeDados.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /*for (int i = 0;i < seekBar.getProgress();i++){
                    listDados.add(new Dado(seekFaces.getProgress()));
                }

                for (Dado d: listDados) {
                    Random random = new Random();
                    d.setFaces(random.nextInt(d.getFaces()));
                    finalResult += d.getFaces();
                }*/
            }
        });

        seekFaces.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantidadeFaces.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fazerJogada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dados.setText("");

                for (int i = 0;i < seekDados.getProgress();i++){
                    listDados.add(new Dado(seekFaces.getProgress()));
                }

                for (final Dado d: listDados) {
                    int rdm = random.nextInt(d.getFaces());
                    if (rdm == 0) rdm += 1;
                    d.setFaces(rdm);
                    finalResult += d.getFaces();

                    dados.append(String.format("Dado " + (listDados.indexOf(d) + 1) + ": " + d.getFaces()) + "\n");
                }

                String string = String.valueOf("Resultado para a sua jogada: " + finalResult);
                resultDice.setText(string);
                listDados.clear();
                finalResult = 0;
            }
        });
    }
}
