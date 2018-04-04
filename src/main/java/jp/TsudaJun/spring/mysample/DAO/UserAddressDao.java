package jp.TsudaJun.spring.mysample.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import jp.TsudaJun.spring.mysample.model.UserAddress;

@Repository
@Transactional
public class UserAddressDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public List<UserAddress> getAddressByUserid(String userid){
			List<UserAddress> ret = manager.createNativeQuery("select * from useraddress where userid = :userid", UserAddress.class)
					.setParameter("userid", userid)
					.getResultList();
			return ret;
	}
	
	public UserAddress getAddressByAddressid(int addressid){
		UserAddress ret = null;
		try {
			ret = (UserAddress) manager.createNativeQuery("select * from useraddress where addressid = :addressid", UserAddress.class)
					.setParameter("addressid", addressid)
					.getSingleResult();
		}catch(Exception e) {
			
		}
		return ret;
}
	
	@Transactional
	public void persist(UserAddress address) {
		manager.persist(address);
	}
	
	

}
