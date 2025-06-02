package com.brunodifante.projetoarduino.model;

import com.brunodifante.projetoarduino.repository.ControlePorta;

public class Led {

    private boolean ligado;
    private final ControlePorta controlePorta;

    public Led(String portaCOM, int taxa) {
        this.controlePorta = new ControlePorta(portaCOM, taxa);
        this.ligado = false;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void ligar() {
        controlePorta.enviaDados('1');
        ligado = true;
    }

    public void desligar() {
        controlePorta.enviaDados('2');
        ligado = false;
    }

    public void alternar() {
        if (ligado) {
            desligar();
        } else {
            ligar();
        }
    }

    public void fechar() {
        controlePorta.close();
    }
}
