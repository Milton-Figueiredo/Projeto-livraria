package br.com.curso.model;

public class Livro {
    private int idLivro;
    private String tituloLivro;
    private Autor autor;
    private Editora editora;
    private Genero genero;

    public Livro() {
        this.idLivro = 0;
        this.tituloLivro = "";
        this.autor = new Autor();
        this.editora = new Editora();
        this.genero = new Genero();
    }

    public Livro(int idLivro, String tituloLivro, Autor autor, Editora editora, Genero genero) {
        this.idLivro = idLivro;
        this.tituloLivro = tituloLivro;
        this.autor = autor;
        this.editora = editora;
        this.genero = genero;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

}
