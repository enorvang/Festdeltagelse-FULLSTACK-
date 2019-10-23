package eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Deltager;

@Stateless
public class DeltagerEAO {
	
	@PersistenceContext(name="festdeltagelsePU")
	private EntityManager em;
 	
	public void leggTilDeltager(Deltager d) {
		em.persist(d);
	}
	
	@SuppressWarnings("unchecked")
	public List<Deltager> hentDeltagerliste() {
		Query query = em.createQuery("SELECT d from Deltager d", Deltager.class);
		return query.getResultList();
	}
	
	public boolean erMobilBrukt(String mobil) {
		return em.find(Deltager.class, mobil) != null;
	}
	
	public Deltager finnDeltagerMedMobil(String mobil) {
		return em.find(Deltager.class, mobil);
	}
}
