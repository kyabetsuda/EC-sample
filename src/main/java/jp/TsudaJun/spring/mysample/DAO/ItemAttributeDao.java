package jp.TsudaJun.spring.mysample.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.TsudaJun.spring.mysample.model.ItemAttribute;

@Repository
@Transactional
public class ItemAttributeDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public List<ItemAttribute> getAllAttributes(){
			List<ItemAttribute> ret = manager.createNativeQuery("select * from itemattribute", ItemAttribute.class).getResultList();
			return ret;
	}
	
	public ItemAttribute getAttributeByName(String name) {
		
			ItemAttribute ret = (ItemAttribute) manager.createNativeQuery("select * from itemattribute where itemattributenm = :name", ItemAttribute.class)
					.setParameter("name", name)
					.getSingleResult();
			return ret;
	}
	
	

}
