package application.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface Dao<T> {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pairly");
	EntityManager em = emf.createEntityManager();

	public void inserir(T t);
	public void alterar(T t);
	public void excluir(T t);
	public List<T> listar(T t);
	public List<T> listarWhere(T t, String where, Map<String,Object>argumentos);
	
}
