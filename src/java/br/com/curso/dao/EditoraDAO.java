package br.com.curso.dao;

import br.com.curso.model.Editora;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO implements GenericDAO {
    
    private Connection conexao;
    
    public EditoraDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
         Editora oEditora = (Editora) objeto;
       Boolean retorno = false;
       if(oEditora.getIdEditora()==0){
           retorno = this.inserir(oEditora);
       }else{
           retorno = this.alterar(oEditora);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
         Editora oEditora = (Editora) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into editora (descricaoeditora) values (?)";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oEditora.getDescricaoEditora());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Probleamas ao cadastrar Editora! Erro: " +  ex.getMessage());
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
       Editora oEditora = (Editora) objeto;
       PreparedStatement stmt = null;
       String sql = "update editora set descricaoeditora = ? where ideditora = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1, oEditora.getDescricaoEditora());
           stmt.setInt(2, oEditora.getIdEditora());
           stmt.execute();
           conexao.commit();
           return true;
       }catch(Exception ex){
           try{
               System.out.println("Problemas ao alterar Editora! Erro: " + ex.getMessage());
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
       int idEditora = numero;
       PreparedStatement stmt = null;
       String sql = "delete from editora where ideditora = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idEditora);
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
       int idEditora = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Editora oEditora = null;
       String sql = "Select * from Editora where idEditora = ?";
       
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idEditora);
           rs = stmt.executeQuery();
           while(rs.next()){
               oEditora = new Editora();
               oEditora.setIdEditora(rs.getInt("idEditora"));
               oEditora.setDescricaoEditora(rs.getString("descricaoeditora"));
           }
           return oEditora;
       }catch(Exception ex){
           System.out.println("Problemas ao carregar Editora! Erro: " + ex.getMessage());
           return false;
       }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select  * from editora order by ideditora";
        
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Editora oEditora = new Editora();
                oEditora.setIdEditora(rs.getInt("idEditora"));
                oEditora.setDescricaoEditora(rs.getString("descricaoeditora"));
                resultado.add(oEditora);
            }
        }catch(Exception ex){
            System.out.println("Problemas ao listar Editora! Erro: " + ex.getMessage());
            
        }
        return resultado;
    }
    
}
