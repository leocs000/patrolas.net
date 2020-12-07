package net.patrolas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.patrolas.application.Util;
import net.patrolas.model.Produto;

public class ProdutoDAO implements DAO<Produto> {
	@Override
	public void inserir(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("produto ");
		sql.append("  (codigo, categoria, titulo, descricao, fabricante, modelo, preco, estoque) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCodigo());
			stat.setInt(2, obj.getCategoria().getId());
			stat.setString(3, obj.getTitulo());
			stat.setString(4, obj.getDescricao());
			stat.setString(5, obj.getFabricante());
			stat.setString(6, obj.getModelo());
			stat.setDouble(8, obj.getPreco());
			stat.setInt(9, obj.getEstoque());
			
			
		

			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public void alterar(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE produto SET ");
		sql.append("  codigo = ?, ");
		sql.append("  categoria = ?, ");
		sql.append("  titulo = ?, ");
		sql.append("  descricao = ?, ");
		sql.append("  fabricante = ?, ");
		sql.append("  modelo = ?, ");
		sql.append("  preco = ?, ");
		sql.append("  estoque = ? ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCodigo());
			stat.setInt(2, obj.getCategoria().getId());
			stat.setString(3, obj.getTitulo());
			stat.setString(4, obj.getDescricao());
			stat.setString(5, obj.getFabricante());
			stat.setString(6, obj.getModelo());
			stat.setDouble(8, obj.getPreco());
			stat.setInt(9, obj.getEstoque());
			stat.setInt(10, obj.getId());

			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de Update.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public void excluir(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM produto WHERE id = ?");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de Delete.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

	}

	@Override
	public List<Produto> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Produto> listaProduto = new ArrayList<Produto>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id, ");
		sql.append("  p.codigo, ");
		sql.append("  p.categoria, ");
		sql.append("  p.titulo, ");
		sql.append("  p.descricao, ");
		sql.append("  p.fabricante, ");
		sql.append("  p.modelo, ");
		sql.append("  p.preco, ");
		sql.append("  p.estoque ");
		sql.append("FROM  ");
		sql.append("  produto p ");
		sql.append("ORDER BY p.titulo ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.getCategoria().setId(rs.getInt("categoria"));
				produto.setTitulo(rs.getString("titulo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setModelo(rs.getString("modelo"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setEstoque(rs.getInt("estoque"));

				listaProduto.add(produto);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em ProdutoDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaProduto;
	}

	@Override
	public Produto obterUm(Produto obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		Produto produto = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id, ");
		sql.append("  p.codigo, ");
		sql.append("  p.categoria, ");
		sql.append("  p.titulo, ");
		sql.append("  p.descricao, ");
		sql.append("  p.fabricante, ");
		sql.append("  p.modelo, ");
		sql.append("  p.preco, ");
		sql.append("  p.estoque ");
		sql.append("FROM  ");
		sql.append("  produto p ");
		sql.append("WHERE p.id = ? ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.getCategoria().setId(rs.getInt("categoria"));
				produto.setTitulo(rs.getString("titulo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setModelo(rs.getString("modelo"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setEstoque(rs.getInt("estoque"));
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em ProdutoDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return produto;
	}

	public List<Produto> obterListaProduto(Integer tipo, String filtro) throws Exception { // tipo - 1 Nome; 2 Descricao
		Exception exception = null;																					// null;
		Connection conn = DAO.getConnection();
		List<Produto> listaProduto = new ArrayList<Produto>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id, ");
		sql.append("  p.codigo, ");
		sql.append("  p.categoria categoriaProduto, ");
		sql.append("  p.titulo, ");
		sql.append("  p.descricao, ");
		sql.append("  p.fabricante, ");
		sql.append("  p.modelo, ");
		sql.append("  p.preco, ");
		sql.append("  p.estoque, ");
		sql.append("  c.categoria categoriaCategoria ");
		sql.append("FROM  ");
		sql.append("  produto p, categoria c ");
		sql.append("WHERE ");
		sql.append("  upper(p.codigo) LIKE upper( ? ) ");
		sql.append("  AND upper(p.fabricante) LIKE upper( ? ) ");
		sql.append("  AND p.categoria = c.id ");
		sql.append("ORDER BY p.titulo ");

		PreparedStatement stat = null;
		try {
			
			stat = conn.prepareStatement(sql.toString());
			
			stat.setString(1, tipo == 1 ? "%" + filtro + "%" : "%");
			stat.setString(2, tipo == 2 ? "%" + filtro + "%" : "%");
			
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.getCategoria().setId(rs.getInt("categoriaProduto"));
				produto.getCategoria().setCategoria(rs.getString("categoriaCategoria"));
				produto.setTitulo(rs.getString("titulo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setModelo(rs.getString("modelo"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setEstoque(rs.getInt("estoque"));

				listaProduto.add(produto);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em ProdutoDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaProduto;
	}

	public List<Produto> obterListaProdutoComEstoque(Integer tipo, String filtro) throws Exception { // tipo - 1 Nome; 2
		Exception exception = null;																								// null;
		Connection conn = DAO.getConnection();
		List<Produto> listaProduto = new ArrayList<Produto>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id, ");
		sql.append("  p.codigo, ");
		sql.append("  p.categoria categoriaProduto, ");
		sql.append("  p.titulo, ");
		sql.append("  p.descricao, ");
		sql.append("  p.fabricante, ");
		sql.append("  p.modelo, ");
		sql.append("  p.preco, ");
		sql.append("  p.estoque, ");
		sql.append("  c.categoria categoriaCategoria ");
		sql.append("FROM  ");
		sql.append("  produto p, categoria c ");
		sql.append("WHERE ");
		sql.append("  upper(p.codigo) LIKE upper( ? ) ");
		sql.append("  AND upper(p.fabricante) LIKE upper( ? ) ");
		sql.append("  AND p.categoria = c.id ");
		sql.append("ORDER BY p.titulo ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, tipo == 1 ? "%" + filtro + "%" : "%");
			stat.setString(2, tipo == 2 ? "%" + filtro + "%" : "%");

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setCodigo(rs.getString("codigo"));
				produto.getCategoria().setId(rs.getInt("categoriaProduto"));
				produto.getCategoria().setCategoria(rs.getString("categoriaCategoria"));
				produto.setTitulo(rs.getString("titulo"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setModelo(rs.getString("modelo"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setEstoque(rs.getInt("estoque"));

				listaProduto.add(produto);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do produto");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em ProdutoDAO.");
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
		}

		if (exception != null)
			throw exception;

		return listaProduto;
	}
	
}
