package br.com.curso.dao;

import br.com.curso.model.Autor;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO implements GenericDAO {
    
    private Connection conexao;
    
    public AutorDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
       Autor oAutor = (Autor) objeto;
       Boolean retorno = false;
       if(oAutor.getIdAutor()==0){
           retorno = this.inserir(oAutor);
       }else{
           retorno = this.alterar(oAutor);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Autor oAutor = (Autor) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into autor (descricaoautor) values (?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oAutor.getDescricaoAutor());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Probleamas ao cadastrar Autor! Erro: " +  ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch(Exception e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
       Autor oAutor = (Autor) objeto;
       PreparedStatement stmt = null;
       String sql = "update autor set descricaoautor = ? where idautor = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oAutor.getDescricaoAutor());
           stmt.setInt(2, oAutor.getIdAutor());
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           try{
               System.out.println("Problemas ao alterar Autor! Erro: " + ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
           }catch(Exception e){
               System.out.println("Erro: "  + e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Boolean excluir(int numero) {
        int idAutor = numero;
       PreparedStatement stmt = null;
       String sql = "delete from autor where idautor = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idAutor);
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           System.out.println("Problemas ao excluir Autor! Erro: " + ex.getMessage());
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
       int idAutor = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Autor oAutor = null;
       String sql = "Select * from autor where idautor = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idAutor);
           rs = stmt.executeQuery();
           while(rs.next()){
               oAutor = new Autor();
               oAutor.setIdAutor(rs.getInt("idautor"));
               oAutor.setDescricaoAutor(rs.getString("descricaoautor"));
           }
           return oAutor;
       }catch(Exception ex){
           System.out.println("Problemas ao carregar Autor! Erro: " + ex.getMessage());
           return false;
       }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select  * from autor order by idautor";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Autor oAutor = new Autor();
                oAutor.setIdAutor(rs.getInt("idautor"));
                oAutor.setDescricaoAutor(rs.getString("descricaoautor"));
                resultado.add(oAutor);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao listar Autor! Erro: " + ex.getMessage());
            
        }
        return resultado;
    }
    
}
