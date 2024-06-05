package LocadoraVeiculo.dao;

import LocadoraVeiculo.Conexao;
import LocadoraVeiculo.entity.Fabricante;
import LocadoraVeiculo.entity.Modelo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModeloDAO {

    public List<Modelo> select() {
        List<Modelo> listaModelo = new ArrayList<>();

        String query = """
                       SELECT * from modelo;
                       """;

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));

                Integer fabricanteId = rs.getInt("id_fabricante");
                Fabricante fabricante = new Fabricante(fabricanteId);

                modelo.setFabricante(fabricante);

                listaModelo.add(modelo);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaModelo;
    }

    public Modelo select(int id) {
        String query = String.format("""
                       SELECT m.id AS modelo_id,
                              m.nome AS modelo_nome,
                              f.nome AS fabricante_nome
                       FROM modelo AS m
                       INNER JOIN fabricante AS f ON m.id_fabricante = f.id
                       WHERE m.id = %d;
                       """, id);

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("modelo_id"));
                modelo.setNome(rs.getString("modelo_nome"));

                String fabricanteNome = rs.getString("fabricante_nome");
                Fabricante fabricante = new Fabricante(fabricanteNome);

                modelo.setFabricante(fabricante);

                return modelo;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Modelo> selectNomeFabricante() {
        List<Modelo> listaModelo = new ArrayList<>();

        String query = """
                       SELECT m.id AS modelo_id,
                              m.nome AS modelo_nome,
                              f.nome AS fabricante_nome
                       FROM modelo AS m
                       INNER JOIN fabricante AS f ON m.id_fabricante = f.id
                       ORDER BY modelo_id ASC;
                       """;

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Modelo modelo = new Modelo();

                modelo.setId(rs.getInt("modelo_id"));
                modelo.setNome(rs.getString("modelo_nome"));

                String fabricanteNome = rs.getString("fabricante_nome");
                Fabricante fabricante = new Fabricante(fabricanteNome);

                modelo.setFabricante(fabricante);

                listaModelo.add(modelo);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaModelo;
    }

    public int insert(String nome, int id) {
        String query = String.format("""
                                  INSERT INTO modelo (nome, id_fabricante)
                                  VALUES ('%s',%d)
                                  """, nome, id);
        try (Statement stmt = Conexao.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int update(int id, String nome) {
        String query = String.format("""
                                  UPDATE modelo
                                  SET nome = '%s'
                                  WHERE id = %d;
                                  """, nome, id);
        try (Statement stmt = Conexao.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
        public int delete(int id) {
        String query = String.format("""
                                  DELETE FROM modelo
                                  WHERE id = %d;
                                  """,id);
        try (Statement stmt = Conexao.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
