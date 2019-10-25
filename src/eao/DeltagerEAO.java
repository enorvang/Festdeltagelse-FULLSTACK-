package eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import entities.Deltager;

@Stateless
public class DeltagerEAO {

	@PersistenceContext(name = "festdeltagelsePU")
	private EntityManager em;

	public void leggTilDeltager(Deltager d) {
		em.persist(d);
	}

	public List<Deltager> hentDeltagerliste() {
		return em.createQuery("SELECT d from Deltager d", Deltager.class).getResultList();
	}

	public boolean erMobilBrukt(String mobil) {
		return em.find(Deltager.class, mobil) != null;
	}

	public Deltager finnDeltagerMedMobil(String mobil) {
		return em.find(Deltager.class, mobil);
	}
}
