package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Login;

/***
 * 
 * @author 81612334 Leonardo Santiago Gonçalves SIN3AN-MCA1
 *  */

@Repository
public class LoginDAO {
	private Connection conn;
	
	@Autowired
	public LoginDAO(DataSource dataSource) throws IOException{
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	
	public boolean validarLogin(Login login) throws IOException{
		
		boolean validacao = false;
		
		String sql = "select * from usuario where login = ? and senha = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, login.getUsername());
			stmt.setString(2, login.getPassword());
			
			try (ResultSet rs = stmt.executeQuery();) {
							
				if(rs.next()) {
					validacao = true;
				}
				
			} catch (SQLException e) {
				throw new IOException(e);
			}
		
		} catch (SQLException e) {
			throw new IOException(e);
		}
		
		return validacao;
		
	}
		
}