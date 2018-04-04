package jp.TsudaJun.spring.mysample.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import jp.TsudaJun.spring.mysample.model.Sell;

@Repository
@Transactional
public class SellDao {
	
	@PersistenceContext
	EntityManager manager;
	
	@Transactional
	public void persist(Sell sell) {
		manager.persist(sell);
	}
	
	public void close() {
		if (manager.isOpen()) {
            manager.close();
        }
	}
	
	public List<Sell> getSellsByUserid(String userid){
		
		List<Sell> ret = null;
		try {
			ret = manager.createNativeQuery("select * from sell where userid = :userid order by boughtymd desc"
					,Sell.class)
					.setParameter("userid", userid)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}

}
