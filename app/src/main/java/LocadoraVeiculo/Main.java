package LocadoraVeiculo;

import LocadoraVeiculo.GUI.TelaPrincipal;

public class Main {

    public static void main(String[] args) {
        Conexao.iniciarBanco("localhost", "5432", "LocadoraVeiculo", "postgres", "postgres");
        
        TelaPrincipal form = new TelaPrincipal();
        form.setVisible(true);
    }
}
