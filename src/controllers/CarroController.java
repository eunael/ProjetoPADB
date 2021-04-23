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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Carro;
import model.Multa;

/**
 *
 * @author natan
 */
public class CarroController {
    public void create(Carro car){
        // iniciando conexão o banco
        Connection con = ConexaoDB.conectar();
        // onde o código sql será guardo
        PreparedStatement codsql = null;
        
        try {
            // preparando o código sql
            codsql = con.prepareStatement("INSERT INTO carros (placa, cor, tipo, marca, cpf_dono) VALUES (?, ?, ?, ?, ?)");
            // informando os valores a serem enviados
            codsql.setString(1, car.getPlaca());
            codsql.setString(2, car.getCor());
            codsql.setString(3, car.getTipo());
            codsql.setString(4, car.getMarca());
            codsql.setLong(5, car.getCpf_dono());
            // executando o sql
            codsql.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no cadastro: " + ex);

            //JOptionPane.showMessageDialog(null, "Cadastre um carro");

        } finally {
            // desconectando o banco
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public List<Carro> read(){
        // retornar pesquisa na tabela carro
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        // onde todos os registros da tabela carro vai serão guardados
        List<Carro> carros = new ArrayList<Carro>();
        try {
            codsql = con.prepareStatement("SELECT * FROM carros");
            rs = codsql.executeQuery();
            
            while(rs.next()){
                Carro carro = new Carro();
                
                carro.setPlaca(rs.getString("placa"));
                carro.setCor(rs.getString("cor"));
                carro.setTipo(rs.getString("tipo"));
                carro.setMarca(rs.getString("marca"));
                carro.setCpf_dono(rs.getInt("cpf_dono"));
                
                carros.add(carro);
            }
        } catch (SQLException ex){
            //JOptionPane.showMessageDialog(null, "Erro ao consultar");
            throw new RuntimeException("Erro na conexão: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return carros;
    }
    public Carro readCarro(String placa){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        Carro car = new Carro();
        try {
            codsql = con.prepareStatement("SELECT * FROM carros WHERE placa = ? ORDER BY placa");
            codsql.setString(1, placa);
            rs = codsql.executeQuery();
        
            while (rs.next()){
                car.setPlaca(rs.getString("placa"));
                car.setCor(rs.getString("cor"));
                car.setTipo(rs.getString("tipo"));
                car.setMarca(rs.getString("marca"));
                car.setCpf_dono(rs.getLong("cpf_dono"));
            }
        } catch (SQLException ex){
            throw new RuntimeException("Erro no cadastro: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return car;
    }
    public List<Multa> readMultaCarro(String placa){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        List<Multa> carroMultas = new ArrayList<Multa>();
        try {
            codsql = con.prepareStatement("SELECT * FROM multa WHERE placa_carro = ?");
            codsql.setString(1, placa);
            rs = codsql.executeQuery();
            
            while(rs.next()){
                Multa multa = new Multa();
                
                multa.setCodigo(rs.getInt("codigo"));
                multa.setValor(rs.getDouble("valor"));
                multa.setPlaca_carro(rs.getString("placa_carro"));
                multa.setCpf_agente(rs.getLong("cpf_agente"));
                multa.setDescricao(rs.getString("descricao"));
                
                carroMultas.add(multa);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao consultar");
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        return carroMultas;
    }
    
    public void update(Carro car, String placa){
        // fazer update de um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("UPDATE carros SET placa = ?, cor = ?, tipo = ?, marca = ?, cpf_dono = ? WHERE placa = ?");
            codsql.setString(1, car.getPlaca());
            codsql.setString(2, car.getCor());
            codsql.setString(3, car.getTipo());
            codsql.setString(4, car.getMarca());
            codsql.setLong(5, car.getCpf_dono());
            codsql.setString(6, placa);
            
            codsql.executeUpdate();
        }  catch (SQLException ex){
            throw new RuntimeException("Erro no cadastro: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public void delete(String placa){
        // deletar um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("DELETE FROM carros WHERE placa = ?");
            codsql.setString(1, placa);
            
            codsql.executeUpdate();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar");
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
}
