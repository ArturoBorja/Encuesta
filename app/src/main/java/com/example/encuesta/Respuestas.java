package com.example.encuesta;

public class Respuestas {
    String texto;
    int id;
    int votos;

    public Respuestas() {
    }

    public Respuestas(String texto, int id, int votos) {
        this.texto = texto;
        this.id = id;
        this.votos = votos;
    }
    @Override
    public String toString(){
        return texto+" : "+votos;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }

    public int getVotos() {
        return votos;
    }
}
