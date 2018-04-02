package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/***
 * 
 * @author 81612334 Leonardo Santiago Gonçalves SIN3AN-MCA1
 *  */

@Repository
public class ChamadoDAO {
	
	private Connection conn;
	
	@Autowired 
	public ChamadoDAO(DataSource dataSource) throws IOException{
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
	
	public int criarChamado(Chamado chamado) throws IOException {
		int id = -1;
		String sql = "INSERT INTO chamado (descricao, status, dt_abertura, id_fila) VALUES (?,?,?,?)";
		String sql1 = "SELECT LAST_INSERT_ID()";
		try(PreparedStatement stmt = conn.prepareStatement(sql);){
			stmt.setString(1, chamado.getDescricao());
			stmt.setString(2, chamado.getStatus());			
			stmt.setDate(3, new Date(chamado.getDT_ABERTURA().getTime()));
			stmt.setInt(4, chamado.getFila().getId());
			stmt.execute();
			
			try(PreparedStatement stm = conn.prepareStatement(sql1);
					ResultSet rs = stm.executeQuery();){
				rs.next();
				id = rs.getInt(1);
			}catch(SQLException e1){
				throw new IOException(e1);				
			}
			
		}catch(SQLException e){
			throw new IOException(e);
		}
		
		return id;
	}
	
		
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		
		String sqlSelect = "select * from chamado WHERE chamado.ID_FILA = ?";		
		ArrayList<Chamado> lista = new ArrayList<>();
		
		try (   PreparedStatement pst = conn.prepareStatement(sqlSelect);) {
				pst.setInt(1, fila.getId());
			
			try (ResultSet rs = pst.executeQuery();) {
				
				while(rs.next()) {
									
				Chamado chamado = new Chamado();
				chamado.setId(1);
				chamado.setStatus(rs.getString("STATUS"));
				chamado.setDescricao(rs.getString("DESCRICAO"));
				chamado.setDT_ABERTURA(rs.getDate("DT_ABERTURA"));
				chamado.setDT_FECHAMENTO(rs.getDate("DT_FECHAMENTO"));
				
				int id_fila = rs.getInt("ID_FILA");
				fila.setId(id_fila);
				chamado.setFila(fila);
								
				lista.add(chamado);
									
				}

			} catch (SQLException e) {
				throw new IOException(e);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return lista;
		
	}
		

}
