package com.example.board;

import org.mariadb.jdbc.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertBoard(BoardVO vo) {

        String sql = "insert into BOARD (title, writer, content, category) values ("
                + "'" + vo.getTitle()  + "',"
                + "'" + vo.getWriter() + "',"
                + "'" + vo.getContent()+ "',"
                + "'" + vo.getCategory()+ "')";

        return jdbcTemplate.update(sql);
    }

    public int deleteBoard(int seq) {

        String sql = "delete from BOARD where seq = " + seq;

        return jdbcTemplate.update(sql);
    }

    public int updateBoard(BoardVO vo) {

        String sql = "update BOARD set title='" + vo.getTitle() + "',"
                + "writer='" + vo.getWriter() + "',"
                + "content='" + vo.getContent() + "',"
                + "category='" + vo.getCategory() + "' where seq=" + vo.getSeq();

        return jdbcTemplate.update(sql);
    }

    public BoardVO getBoard (int seq) {

        String sql = "select * from BOARD where seq = " + seq;
        return jdbcTemplate.queryForObject(sql, new RowMapper<BoardVO>() {
            @Override
            public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardVO two = new BoardVO();
                two.setSeq(rs.getInt("seq"));
                two.setTitle(rs.getString("title"));
                two.setWriter(rs.getString("writer"));
                two.setContent(rs.getString("content"));
                two.setRegdate(rs.getTimestamp("regdate"));

                return two;
            }
        });
    }

    public List<BoardVO> getBoardList() {

        String sql = "select * from BOARD order by seq desc";

        List<BoardVO> list = jdbcTemplate.query(sql, new RowMapper<BoardVO>() {
            @Override
            public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardVO one = new BoardVO();
                one.setSeq(rs.getInt("seq"));
                one.setTitle(rs.getString("title"));
                one.setWriter(rs.getString("writer"));
                one.setContent(rs.getString("content"));
                one.setRegdate(rs.getTimestamp("regdate"));
                return one;
            }
        });

        return list;
    }
}