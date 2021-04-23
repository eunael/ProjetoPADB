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
import model.DonoCarro;

/**
 *
 * @author natan
 */
public class DonoCarroController {
    
    public void create(DonoCarro dc){
        // iniciando conexão o banco
        Connection con = ConexaoDB.conectar();
        // onde o código sql será guardo
        PreparedStatement codsql = null;
        
        try {
            // preparando o código sql
            codsql = con.prepareStatement("INSERT INTO dono_carro (cpf, nome, endereco) VALUES (?, ?, ?)");
            // informando os valores a serem enviados
            codsql.setLong(1, dc.getCpf());
            codsql.setString(2, dc.getNome());
            codsql.setString(3, dc.getEndereco());
            // executando o sql
            codsql.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro no cadastro: " + ex);
            //JOptionPane.showMessageDialog(null, "Cadastre um motorista");
        } finally {
            // desconectando o banco
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public List<DonoCarro> read(){
        // retornar pesquisa na tabela dono_carro
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        // onde todos os registros da tabela dono_carro vai serão guardados
        List<DonoCarro> donos = new ArrayList<DonoCarro>();
        try {
            codsql = con.prepareStatement("SELECT * FROM dono_carro");
            rs = codsql.executeQuery();
            
            while(rs.next()){
                DonoCarro dono = new DonoCarro();
                
                dono.setCpf(rs.getInt("cpf"));
                dono.setNome(rs.getString("nome"));
                dono.setEndereco(rs.getString("endereco"));
                
                donos.add(dono);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao consultar");
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return donos;
    }
    public DonoCarro readDono(Long cpf){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        DonoCarro dono  = new DonoCarro();
        try {
            codsql = con.prepareStatement("SELECT * FROM dono_carro WHERE cpf = ?");
            codsql.setLong(1, cpf);
            rs = codsql.executeQuery();
        
            while (rs.next()){
                dono.setCpf(rs.getInt("cpf"));
                dono.setNome(rs.getString("nome"));
                dono.setEndereco(rs.getString("endereco"));;
            }
        } catch (SQLException ex){
            throw new RuntimeException("Erro no cadastro: " + ex);
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        
        return dono;
    }
    public List<Carro> readDonoCarros(Long cpf){
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        ResultSet rs = null;
        
        List<Carro> donoCarros = new ArrayList<Carro>();
        try {
            codsql = con.prepareStatement("SELECT * FROM carros WHERE cpf_dono = ?");
            codsql.setLong(1, cpf);
            rs = codsql.executeQuery();
            
            while(rs.next()){
                Carro car = new Carro();
                
                car.setPlaca(rs.getString("placa"));
                car.setCor(rs.getString("cor"));
                car.setMarca(rs.getString("marca"));
                car.setTipo(rs.getString("tipo"));
                car.setCpf_dono(rs.getLong("cpf_dono"));
                
                donoCarros.add(car);
            }
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao consultar: "+ex);
        } finally {
            ConexaoDB.desconectar(con, codsql, rs);
        }
        return donoCarros;
    }
    
    public void update(DonoCarro dc){
        // fazer update de um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("UPDATE dono_carro SET nome = ?, endereco = ? WHERE cpf = ?");
            codsql.setString(1, dc.getNome());
            codsql.setString(2, dc.getEndereco());
            codsql.setLong(3, dc.getCpf());
            
            codsql.executeUpdate();
        }  catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
    public void delete(Long cpf){
        // deletar um registro da tabela
        Connection con = ConexaoDB.conectar();
        PreparedStatement codsql = null;
        
        try {
            codsql = con.prepareStatement("DELETE FROM dono_carro WHERE cpf = ?");
            codsql.setLong(1, cpf);
            
            codsql.executeUpdate();
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar");
        } finally {
            ConexaoDB.desconectar(con, codsql);
        }
    }
}
