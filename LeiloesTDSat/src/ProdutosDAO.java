/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
              
        conn = new conectaDAO().connectDB();
        int status;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }              
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        try {
            
            conn = new conectaDAO().connectDB();
                        
            prep = conn.prepareStatement("SELECT * from produtos");
                                                                   
            resultset = prep.executeQuery();
                                   
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listagem.add(produto);
                
            }
                                    
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
            return listagem;
    }
    
    public int venderProduto (int id){
        
        conn = new conectaDAO().connectDB();
        int status;
        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");
            prep.setString(1, "Vendido");
            prep.setInt(2, id);
            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }  
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        
        try {
            
            conn = new conectaDAO().connectDB();
                        
            prep = conn.prepareStatement("SELECT * from produtos WHERE status = ?");
            prep.setString(1, "Vendido");                                                       
            resultset = prep.executeQuery();
                                   
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                listaVendidos.add(produto);
                
            }
                                    
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
            return listaVendidos;
    }
    
}

