package br.com.automacao.repositorio.dao;

import br.com.automacao.repositorio.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class TesteDAO {

    private static final String SELECT = "SELECT * FROM nome_base WHERE paramentro = ?";
    private static final String DELETE = "";
    private static final String UPDATE = "";

    private JdbcTemplate jdbcTemplate;
    private JdbcTemplate jdbcTemplateAlternativo;

    @Autowired
    public TesteDAO(JdbcTemplate jdbcTemplate, JdbcTemplate jdbcTemplateAlternativo) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplateAlternativo = jdbcTemplateAlternativo;
    }

    public ClienteEntity selectTeste(String algumParamentro) {
        try {
            return jdbcTemplate.queryForObject(SELECT, new Object[]{algumParamentro}, BeanPropertyRowMapper.newInstance(ClienteEntity.class));

        } catch (Exception e) {
            System.out.println("Erro ao selecionar na base: " + e.getMessage());
        }
        return null;
    }

    public void updateTeste(String algumParamentro) {
        try {
            jdbcTemplate.update(UPDATE, algumParamentro);

        } catch (Exception e) {
            System.out.println("Erro ao alterar na base: " + e.getMessage());
        }
    }

    public void deleteTeste(String algumParamentro) {
        try {
            jdbcTemplateAlternativo.update(DELETE, algumParamentro);

        } catch (Exception e) {
            System.out.println("Erro ao alterar na base: " + e.getMessage());
        }
    }

}