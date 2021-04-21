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
import model.Agente;
import model.Multa;

/**
 *
 * @author natan
 */
public class AgenteController {
    public void create(Agente ag){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("INSERT INTO agente (cpf, nome, endereco) VALUES (?, ?, ?)");
            
            codsql.setLong(1, ag.getCpf());
            codsql.setString(2, ag.getNome());
            codsql.setString(3, ag.getEndereco());
            
            codsql.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException("Erro no cadastro: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public List<Agente> read(){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        List<Agente> agentes = new ArrayList<Agente>();
        try {
            codsql = con.prepareStatement("SELECT * FROM agente");
            rs = codsql.executeQuery();
            
            while(rs.next()){
                Agente agente = new Agente();
                
                agente.setCpf(rs.getInt("cpf"));
                agente.setNome(rs.getString("nome"));
                agente.setEndereco(rs.getString("endereco"));
                
                agentes.add(agente);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao consultar");
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return agentes;
    }
    
    public List<Multa> readMultaAgente(Agente agn){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        List<Multa> agenteMulta = new ArrayList<Multa>();
        
        try {
            codsql = con.prepareStatement("SELECT * FROM multa WHERE cpf_agente = ?");
            codsql.setLong(1, agn.getCpf());
            rs = codsql.executeQuery();
            
            while(rs.next()){
                Multa multa = new Multa();
                
                multa.setCodigo(rs.getInt("codigo"));
                multa.setValor(rs.getDouble("valor"));
                multa.setPlaca_carro(rs.getString("placa_carro"));
                multa.setCpf_agente(rs.getLong("cpf_agente"));
                multa.setDescricao(rs.getString("descricao"));
                
                agenteMulta.add(multa);
            }
        } catch (SQLException ex){
            throw new RuntimeException("Erro no cadastro: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return agenteMulta;
    }
    
    public void update(Agente ag){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("UPDATE agente SET nome = ?, endereco = ? WHERE cpf = ?");
            codsql.setString(1, ag.getNome());
            codsql.setString(2, ag.getEndereco());
            codsql.setLong(3, ag.getCpf());
            
            codsql.executeUpdate();
        }  catch (SQLException ex){
            throw new RuntimeException("Erro: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public void delete(Long cpf){
        // deletar um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("DELETE FROM agente WHERE cpf = ?");
            codsql.setLong(1, cpf);
            
            codsql.executeUpdate();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar");
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
}
