package LocadoraVeiculo.dao;

import LocadoraVeiculo.Conexao;
import LocadoraVeiculo.entity.Carro;
import LocadoraVeiculo.entity.Fabricante;
import LocadoraVeiculo.entity.Modelo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    
    public List<Carro> selectNomeFabricanteMOdelo(){
        List<Carro> listaCarro = new ArrayList<>();
        
        String query = """
                       SELECT c.id AS carro_id,
                              m.nome AS modelo_nome,
                              f.nome AS fabricante_nome,
                              c.placa AS placa_carro,
                              c.cor AS cor_carro,
                              c.disponivel AS disponivel_carro,
                              c.ano AS ano_carro,
                              c.valorlocacao AS valorlocacao_carro
                       FROM carro AS c
                       INNER JOIN fabricante AS f ON c.id_fabricante = f.id
                       INNER JOIN modelo AS m ON c.id_modelo = m.id
                       """;
        
        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)){
            
            while(rs.next()){
                Carro carro = new Carro();
                
                carro.setId(rs.getInt("carro_id"));
                
                String modeloNome = rs.getString("modelo_nome");
                Modelo modelo = new Modelo(modeloNome);
                
                String fabricanteNome = rs.getString("fabricante_nome");
                Fabricante fabricante = new Fabricante(fabricanteNome);
                
                carro.setPlaca(rs.getString("placa_carro"));
                carro.setCor(rs.getString("cor_carro"));
                carro.setDisponivel(rs.getBoolean("disponivel_carro"));
                carro.setAno(rs.getInt("ano_carro"));
                carro.setValorLocacao(rs.getDouble("valorlocacao_carro"));
                
                carro.setFabricante(fabricante);
                carro.setModelo(modelo);
                
                listaCarro.add(carro);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return listaCarro;
    }
    
}
