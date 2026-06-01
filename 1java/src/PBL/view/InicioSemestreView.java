package PBL.view;

import javafx.scene.control.Label;

public class InicioSemestreView {
    public void atualizarTextSemestre(Label labelSemestre, int numero) {
        String num = obterNum(numero);
        labelSemestre.setText(num + " semestre do curso de Engenharia de Computação");
    }

    private String obterNum(int numero) {
        return switch (numero) {
            case 1  -> "1°";
            case 2  -> "2°";
            case 3  -> "3°";
            case 4  -> "4°";
            case 5  -> "5°";
            case 6  -> "6°";
            case 7  -> "7°";
            case 8  -> "8°";
            case 9  -> "9°";
            case 10 -> "10°";
            default -> numero + "°";
        };
    }
}