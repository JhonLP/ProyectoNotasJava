package mx.itson.nota.persistencia;


import mx.itson.nota.entidades.NewHibernateUtil;
import mx.itson.nota.entidades.Nota;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class NotaPersistencia {

	public Nota guardar(Nota nota){
		Session session = NewHibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
	    transaction = session.beginTransaction();
	    session.save(nota);
	    transaction.commit();
	    session.close();
		return nota;
}
	public Nota consultar(int id) {
		Nota nota = new Nota();
	    Session session = NewHibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;
        transaction = session.beginTransaction();
        nota = (Nota) session.get(Nota.class.getName(), id);
        transaction.commit();
        session.close();
        return nota;
    }
	
	 public Nota editar(Nota nota) {
	        Session session = NewHibernateUtil.getSessionFactory().openSession();
		    Transaction transaction = null;
	        transaction = session.beginTransaction();
	        session.update(nota);
	        transaction.commit();
	        session.close();
	        return nota;
	    }
	 public boolean eliminar(Nota nota) {
	        boolean notificacion;
	        try {
	            Session session = NewHibernateUtil.getSessionFactory().openSession();
	            Transaction transaction = null;
	            transaction = session.beginTransaction();
	            session.delete(nota);
	            transaction.commit();
	            session.close();
	            notificacion = true;
	        } catch (HibernateException e) {
	            System.out.println(e.getMessage());
	            notificacion = false;
	        }
	        return notificacion;
	 }
	 @SuppressWarnings("unchecked")
		public List<Nota> consultarTodos() {
		        List<Nota> nota = new ArrayList<Nota>();
			    Session session = NewHibernateUtil.getSessionFactory().openSession();
		        Transaction transaction = null;
		        transaction = session.beginTransaction();
		        Query query = session.createQuery("from " + Nota.class.getName()+" order by id");        
		        nota = (List<Nota>) query.list();
		        transaction.commit();
		        session.close();
		        return nota;
		    }
}