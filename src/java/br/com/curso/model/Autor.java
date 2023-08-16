package br.com.curso.model;

public class Autor {
    private int idAutor;
    private String descricaoAutor;

    public Autor() {
        this.idAutor = 0;
        this.descricaoAutor = "";
    }

    public Autor(int idAutor, String descricaoAutor) {
        this.idAutor = idAutor;
        this.descricaoAutor = descricaoAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }

    public void setDescricaoAutor(String descricaoAutor) {
        this.descricaoAutor = descricaoAutor;
    }
 
}
