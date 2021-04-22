/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import conexaodb.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Carro;
import model.Multa;

/**
 *
 * @author natan
 */
public class MultaController {
    public void create(Multa mu){
        // iniciando conexão o banco
        Connection con = ConexaoDB.conectar();
        // onde o código sql será guardo
        PreparedStatement codsql = null;
        
        try {
            // preparando o código sql
            codsql = con.prepareStatement("INSERT INTO multa (valor, placa_carro, cpf_agente, descricao) VALUES (?, ?, ?, ?)");
            // informando os valores a serem enviados
            codsql.setDouble(1, mu.getValor());
            codsql.setString(2, mu.getPlaca_carro());
            codsql.setLong(3, mu.getCpf_agente());
            codsql.setString(4, mu.getDescricao());
            // executando o sql
            codsql.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException("Erro no cadastro: " + ex);
        } finally {
            // desconectando o banco
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public List<Multa> read(){
        // retornar pesquisa na tabela multa
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        // onde todos os registros da tabela multa vai serão guardados
        List<Multa> multas = new ArrayList<Multa>();
        try {
            codsql = con.prepareStatement("SELECT * FROM multa");
            rs = codsql.executeQuery();
            
            while(rs.next()){
                Multa multa = new Multa();
                
                multa.setCodigo(rs.getInt("codigo"));
                multa.setValor(rs.getDouble("valor"));
                multa.setPlaca_carro(rs.getString("placa_carro"));
                multa.setCpf_agente(rs.getInt("cpf_agente"));
                multa.setDescricao(rs.getString("descricao"));
                
                multas.add(multa);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao consultar");
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return multas;
    }
    
    public void update(Multa mu){
        // fazer update de um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("UPDATE multa SET valor = ?, placa_carro = ?, cpf_agente = ?, descricao = ? WHERE  codigo = ?");
            codsql.setDouble(1, mu.getValor());
            codsql.setString(2, mu.getPlaca_carro());
            codsql.setLong(3, mu.getCpf_agente());
            codsql.setString(4, mu.getDescricao());
            codsql.setLong(5, mu.getCodigo());
            
            codsql.executeUpdate();
        }  catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public void delete(Long mu){
        // deletar um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("DELETE FROM multa WHERE codigo = ?");
            codsql.setLong(1, mu);
            
            codsql.executeUpdate();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar");
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
}
