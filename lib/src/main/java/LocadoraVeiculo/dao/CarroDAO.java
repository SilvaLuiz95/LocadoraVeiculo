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

    public List<Carro> select() {
        List<Carro> listaCarro = new ArrayList<>();

        String query = """
                       SELECT * FROM carro;
                       """;

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                Integer modeloId = rs.getInt("id_modelo");
                Modelo modelo = new Modelo(modeloId);
                Integer fabricanteId = rs.getInt("id_fabricante");
                Fabricante fabricante = new Fabricante(fabricanteId);
                carro.setPlaca(rs.getString("placa_carro"));
                carro.setCor(rs.getString("cor_carro"));
                carro.setDisponivel(rs.getBoolean("disponivel_carro"));
                carro.setAno(rs.getInt("ano_carro"));
                carro.setValorLocacao(rs.getDouble("valorlocacao_carro"));

                carro.setModelo(modelo);
                carro.setFabricante(fabricante);

                listaCarro.add(carro);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaCarro;
    }

    public Carro select(int id) {
        String query = String.format("""
                       SELECT c.id AS carro_id,
                              m.id as id_modelo,
                              m.nome AS modelo_nome,
                              f.id as id_fabricante,
                              f.nome AS fabricante_nome,
                              c.placa AS placa_carro,
                              c.cor AS cor_carro,
                              c.disponivel AS disponivel_carro,
                              c.ano AS ano_carro,
                              c.valorlocacao AS valorlocacao_carro
                       FROM carro AS c
                       INNER JOIN fabricante AS f ON c.id_fabricante = f.id
                       INNER JOIN modelo AS m ON c.id_modelo = m.id
                       WHERE c.id = %d;
                       """, id);

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                Carro carro = new Carro();

                carro.setId(rs.getInt("carro_id"));
                carro.setPlaca(rs.getString("placa_carro"));
                carro.setCor(rs.getString("cor_carro"));
                carro.setDisponivel(rs.getBoolean("disponivel_carro"));
                carro.setAno(rs.getInt("ano_carro"));
                carro.setValorLocacao(rs.getDouble("valorlocacao_carro"));
                carro.setFabricante(new Fabricante(rs.getInt("id_fabricante"), rs.getString("fabricante_nome")));
                carro.setModelo(new Modelo(rs.getInt("id_modelo"), rs.getString("modelo_nome")));

                return carro;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     *
     * @return Lista dos carros com nome do modelo e do fabricante
     */
    public List<Carro> selectModeloFabricante() {
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
                       INNER JOIN modelo AS m ON c.id_modelo = m.id;
                       """;

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
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

                carro.setModelo(modelo);
                carro.setFabricante(fabricante);

                listaCarro.add(carro);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaCarro;
    }

    public int insert(int idFabricante, int idModelo, String placa, String cor, String disponivel, int ano, Double valorlocacao) {
        String query = String.format("""
                                INSERT INTO carro (id_fabricante, id_modelo, placa, cor, disponivel, ano, valorlocacao)
                                VALUES (%d, %d, '%s', '%s', '%s', %d, %.0f)
                                 """, idFabricante, idModelo, placa, cor, disponivel, ano, valorlocacao);

        try (Statement stmt = Conexao.getConn().createStatement()) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public int update(Carro c) throws Exception {
//        return update(c.getId(), c.getFabricante().getId(), c.getModelo().getId(), c.getPlaca(), c.getCor(), String.valueOf(c.getDisponivel()), c.getAno(), c.getValorLocacao())
//    }
    public int update(int id, int idFabricante, int idModelo, String placa, String cor, String disponivel, int ano, Double valorlocacao) {
        String query = String.format("""
                                UPDATE carro 
                                SET id_fabricante = %d,
                                    id_modelo = %d,
                                    placa = '%s',
                                    cor = '%s',
                                    disponivel = '%s',
                                    ano = %d,
                                    valorlocacao = %.0f
                                WHERE id = %d
                                 """, idFabricante, idModelo, placa, cor, disponivel, ano, valorlocacao, id);
        try (Statement stmt = Conexao.getConn().createStatement()) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
