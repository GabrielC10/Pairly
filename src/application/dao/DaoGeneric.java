package application.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DaoGeneric<T> implements Dao<T> {

	@Override
	public void inserir(T t) {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void alterar(T t) {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
			em.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<T> listar(T t) {
		try {
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("select t from "+t.getClass().getName()+" t");
			@SuppressWarnings("unchecked")
			List<T> lista = q.getResultList();
			return lista;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<T> listarWhere(T t, String where, Map<String, Object> argumentos) {
		try {
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery("select t from "+t.getClass().getName()+" t"+where);
			for(String indice : argumentos.keySet()) {
				q.setParameter(indice, argumentos.get(indice));
			}
			@SuppressWarnings("unchecked")
			List<T> lista = q.getResultList();
			return lista;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void excluir(T t) {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Object merge = em.merge(t);
			em.remove(merge);
			em.flush();
			em.getTransaction().commit();
			em.close();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
