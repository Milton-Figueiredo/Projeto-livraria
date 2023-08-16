
package br.com.curso.dao;

import br.com.curso.model.Autor;
import br.com.curso.model.Editora;
import br.com.curso.model.Genero;
import br.com.curso.model.Livro;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements GenericDAO {
    
    private Connection conexao;
    
    public LivroDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Livro oLivro = (Livro) objeto;
        Boolean retorno = false;
        if(oLivro.getIdLivro()==0){
            retorno = this.inserir(oLivro);
        }else{
            retorno = this.alterar(oLivro);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into livro (tituloLivro, idautor, ideditora, idgenero) values (?, ?, ?, ?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getTituloLivro());
            stmt.setInt(2, oLivro.getAutor().getIdAutor());
            stmt.setInt(3, oLivro.getEditora().getIdEditora());
            stmt.setInt(4, oLivro.getGenero().getIdGenero());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao cadastrar a Livro! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
        }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "update livro set tituloLivro = ?, idautor = ?, ideditora = ?, idgenero = ? where idLivro = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getTituloLivro());
            stmt.setInt(2, oLivro.getAutor().getIdAutor());
            stmt.setInt(3, oLivro.getEditora().getIdEditora());
            stmt.setInt(4, oLivro.getGenero().getIdGenero());
            stmt.setInt(5, oLivro.getIdLivro());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas ao alterar Livro! Erro: " + ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
        }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idLivro = numero;
        PreparedStatement stmt = null;
        String sql = "delete from livro where idLivro = ?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idLivro);
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           System.out.println("Problemas ao excluir Editora! Erro: " + ex.getMessage());
           try{
               conexao.rollback();
           }catch(Exception e){
               System.out.println("Erro rollback: " + e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Object carregar(int numero) {
        int idLivro = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Livro oLivro = null;
        String sql = "select * from livro where idlivro = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            rs = stmt.executeQuery();
            while(rs.next()){
                oLivro = new Livro();
                oLivro.setIdLivro(rs.getInt("idlivro"));
                oLivro.setTituloLivro(rs.getString("titulolivro"));
               
                AutorDAO oAutorDAO = new AutorDAO();
                oLivro.setAutor((Autor) oAutorDAO.carregar(rs.getInt("idautor")));
                
                EditoraDAO oEditoraDAO = new EditoraDAO();
                oLivro.setEditora((Editora) oEditoraDAO.carregar(rs.getInt("ideditora")));
                
                GeneroDAO oGeneroDAO = new GeneroDAO();
                oLivro.setGenero((Genero) oGeneroDAO.carregar(rs.getInt("idgenero")));
            }
            return oLivro;
        }catch (Exception ex){
            System.out.println("Problemas ao carregar Livro! Erro: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from livro order by titulolivro";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Livro oLivro = new Livro();
                oLivro.setIdLivro(rs.getInt("idlivro"));
                oLivro.setTituloLivro(rs.getString("titulolivro"));
                
                AutorDAO oAutorDAO = null;
                try{
                    oAutorDAO = new AutorDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar autor " + ex.getMessage());
                    ex.printStackTrace();
                }
                oLivro.setAutor((Autor) oAutorDAO.carregar(rs.getInt("idautor")));
                
                EditoraDAO oEditoraDAO = null;
                try{
                    oEditoraDAO = new EditoraDAO();
                }catch(Exception ex){
                    System.out.println("Erro ao buscar Editora! " + ex.getMessage());
                    ex.printStackTrace();
                }
                oLivro.setEditora((Editora) oEditoraDAO.carregar(rs.getInt("ideditora")));
                
                GeneroDAO oGeneroDAO = null;
                try{
                    oGeneroDAO = new GeneroDAO();
                }catch (Exception ex){
                    System.out.println("Erro ao buscar Genero! " + ex.getMessage());
                    ex.printStackTrace();
                }
                oLivro.setGenero((Genero) oGeneroDAO.carregar(rs.getInt("idgenero")));
                
                resultado.add(oLivro);
            }
        }catch (Exception ex){
            System.out.println("Problemas ao listar Livro! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resultado;
    }
    
}
