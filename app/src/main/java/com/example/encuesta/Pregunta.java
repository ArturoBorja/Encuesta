package com.example.encuesta;

public class Pregunta {
    String texto;
    String img;
    int id;

    public Pregunta() {
    }

    public Pregunta(String texto, String img, int id) {
        this.texto = texto;
        this.img = img;
        this.id = id;
    }

    @Override
    public String toString(){
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }
}
