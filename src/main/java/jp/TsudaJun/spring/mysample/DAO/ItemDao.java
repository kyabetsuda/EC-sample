package jp.TsudaJun.spring.mysample.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.TsudaJun.spring.mysample.model.Item;

@Repository
@Transactional
public class ItemDao {
	
	@PersistenceContext
	EntityManager manager;
	
	public void close() {
		manager.clear();
	}
	
	@Transactional
	public void persist(Item item) {
		manager.persist(item);
	}
	
	@Transactional
	public void merge(Item item) {
		manager.merge(item);
	}
	
	public List<Item> getAllItems(){
		List<Item> ret = manager.createNativeQuery("select * from item", Item.class).getResultList();
		return ret;
	}
	
	public List<Item> getAllItemsByDesc(){
		List<Item> ret = manager.createNativeQuery("select * from item order by itemid desc", Item.class).getResultList();
		return ret;
	}
	
	public Item getItemById(int itemid) {
		
		Item ret = null;
		try {
			ret = (Item) manager.createNativeQuery("select * from item where itemid = :id", Item.class)
					.setParameter("id", itemid)
					.getSingleResult();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemByUsreid(String userid) {
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where userid = :id", Item.class)
					.setParameter("id", userid)
					.getResultList();
		}catch(Exception e) {
			return null;
		}
		
		return ret;
	}
	
	
	public List<Item> getItemsByAttributeno(int attributeno){
		List<Item> ret = manager.createNativeQuery("select * from item where itemattribute = :no", Item.class)
									.setParameter("no", attributeno)
									.getResultList();
		
		return ret;

	}
	
	public List<Item> getItemsByWordAndAttribute(String word, int attributeno){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where itemname LIKE :word AND itemattribute = :no"
					,Item.class)
					.setParameter("word", "%"+word+"%")
					.setParameter("no", attributeno)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemsByWordAndAttributeAndUserid(String word, int attributeno, String userid){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where itemname LIKE :word AND itemattribute = :no AND userid = :userid"
					,Item.class)
					.setParameter("word", "%"+word+"%")
					.setParameter("no", attributeno)
					.setParameter("userid", userid)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemsByWord(String word){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where itemname LIKE :word"
					,Item.class)
					.setParameter("word", "%"+word+"%")
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemsByWordAndUserid(String word, String userid){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where itemname LIKE :word AND userid = :userid"
					,Item.class)
					.setParameter("word", "%"+word+"%")
					.setParameter("userid", userid)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}
	
	public List<Item> getItemsByUseridAndItemAttribute(String userid, int itemattribute){
		
		List<Item> ret = null;
		try {
			ret = manager.createNativeQuery("select * from item where userid = :userid AND itemattribute = :itemattribute"
					,Item.class)
					.setParameter("userid", userid)
					.setParameter("itemattribute", itemattribute)
					.getResultList();
		}catch(Exception e) {
			
		}
		
		return ret;
	}

}
