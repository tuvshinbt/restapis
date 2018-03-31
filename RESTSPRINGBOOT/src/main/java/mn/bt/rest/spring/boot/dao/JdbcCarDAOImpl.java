/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mn.bt.rest.spring.boot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import mn.bt.rest.spring.boot.entity.Car;
import mn.bt.rest.spring.boot.entity.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tuvshuu
 */
@Repository
public class JdbcCarDAOImpl implements CarDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = this.jdbcTemplate.query("SELECT c.id, c.Make, c.Miles, c.Model, c.ManufacturingYear, c.color FROM CarTbl c",
                new CarMapper()
        //                new RowMapper<Car>() {
        //                    @Override
        //                    public Car mapRow(ResultSet rs, int i) throws SQLException {
        //                        Car car = new Car(rs.getString("Make"),
        //                                rs.getString("Model"),
        //                                rs.getInt("ManufacturingYear"),
        //                                rs.getInt("Miles"),
        //                                Color.valueOf(rs.getString("color")));
        //                        return car;
        //                    }
        //                }
        );
        return cars;
    }

    @Override
    public Car findById(int id) {
        try {
            Car car = this.jdbcTemplate.queryForObject("SELECT c.id, c.Make, c.Miles, c.Model, c.ManufacturingYear, c.color FROM CarTbl c WHERE c.id = ?",
                    new Object[]{id},
                    new CarMapper()
            );
            return car;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Integer create(final Car car) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into CarTbl(Make, Miles, Model, ManufacturingYear, color) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, car.getMake());
                ps.setInt(2, car.getMiles());
                ps.setString(3, car.getModel());
                ps.setInt(4, car.getYear());
                ps.setString(5, car.getColor().toString());
                return ps;
            }
        }, holder);

        return holder.getKey().intValue();
//        return this.jdbcTemplate.update("insert into CarTbl(Make, Miles, Model, ManufacturingYear, color) values (?, ?, ?, ?, ?)",
//                car.getMake(), car.getMiles(), car.getModel(), car.getYear(), car.getColor().toString());

    }

    @Override
    public void update(int id, Car car) {
        this.jdbcTemplate.update("update carTbl set Make = ?, Miles = ?, Model = ?, ManufacturingYear = ?, color = ? where id = ?",
                car.getMake(),
                car.getMiles(),
                car.getModel(),
                car.getYear(),
                car.getColor().toString(),
                id);
    }

    @Override
    public void delete(int id) {
        this.jdbcTemplate.update("delete from CarTbl where id = ?",
                id);
    }

    private static final class CarMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car(rs.getString("Make"),
                    rs.getString("Model"),
                    rs.getInt("ManufacturingYear"),
                    rs.getInt("Miles"),
                    Color.valueOf(rs.getString("color")));
            car.setId(rs.getInt("id"));
            return car;
        }
    }
}
