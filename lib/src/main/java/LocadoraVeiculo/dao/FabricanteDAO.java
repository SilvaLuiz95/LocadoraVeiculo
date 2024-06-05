package LocadoraVeiculo.dao;

import LocadoraVeiculo.Conexao;
import LocadoraVeiculo.entity.Fabricante;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FabricanteDAO {

    public List<Fabricante> select() {
        List<Fabricante> listaFabricante = new ArrayList<>();

        String query = """
                       SELECT * FROM fabricante
                       ORDER BY id ASC;
                       """;

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Fabricante fabricante = new Fabricante();
                fabricante.setId(rs.getInt("id"));
                fabricante.setNome(rs.getString("nome"));

                listaFabricante.add(fabricante);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaFabricante;
    }

    /**
     * @return retorna apenas um fabricante
     * @param id
     */
    public Fabricante select(int id) {
        String query = String.format("""
                       SELECT * FROM fabricante
                       WHERE id = %d;
                       """, id);

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                Fabricante fabricante = new Fabricante();
                fabricante.setId(rs.getInt("id"));
                fabricante.setNome(rs.getString("nome"));

                return fabricante;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int selectIdFabricante(String nomeFabricante) {
        String query = String.format("""
                       SELECT * FROM fabricante
                       WHERE nome = '%s';
                       """, nomeFabricante);

        try (Statement stmt = Conexao.getConn().createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                             
                return rs.getInt("id");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public int insert(String nome) {
        String query = String.format("""
                                         INSERT INTO fabricante(nome)
                                         VALUES ('%s')
                                         """, nome);
        try (Statement stmt = Conexao.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int update(int id, String nome) {
        String query = String.format("""
                                         UPDATE fabricante
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
                                         DELETE FROM fabricante
                                         WHERE id = %d;
                                         """, id);
        try (Statement stmt = Conexao.getConn().createStatement();) {
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
