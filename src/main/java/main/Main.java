package main;

import view.AppView;

/**
 * Classe principal do sistema Pata Amiga.
 * Responsável por inicializar a interface da aplicação.
 */
public class Main {
    /**
     * Método principal que inicializa a aplicação.
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        AppView appView = new AppView();
        appView.iniciar();
    }
}
