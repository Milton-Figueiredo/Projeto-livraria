package br.com.curso.model;

public class Editora {
    private int idEditora;
    private String descricaoEditora;

    public Editora() {
        this.idEditora = 0;
        this.descricaoEditora = "";
    }

    public Editora(int idEditora, String descricaoEditora) {
        this.idEditora = idEditora;
        this.descricaoEditora = descricaoEditora;
    }

    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public String getDescricaoEditora() {
        return descricaoEditora;
    }

    public void setDescricaoEditora(String descricaoEditora) {
        this.descricaoEditora = descricaoEditora;
    }
    
    
    
    
}
