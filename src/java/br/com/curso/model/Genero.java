package br.com.curso.model;

public class Genero {
    private int idGenero;
    private String descricaoGenero;

    public Genero() {
        this.idGenero = 0;
        this.descricaoGenero = "";
    }

    public Genero(int idGenero, String descricaoGenero) {
        this.idGenero = idGenero;
        this.descricaoGenero = descricaoGenero;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricaoGenero() {
        return descricaoGenero;
    }

    public void setDescricaoGenero(String descricaoGenero) {
        this.descricaoGenero = descricaoGenero;
    }
 
}
