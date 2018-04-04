package jp.TsudaJun.spring.mysample.Controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.mysample.DAO.ItemDao;
import jp.TsudaJun.spring.mysample.model.Item;
import jp.TsudaJun.spring.mysample.model.ItemAttribute;

@Controller
public class ItemContoller {
	
	@Autowired
	ItemDao dao;
	
	@Autowired
	ItemAttributeDao iaDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/item", method=RequestMethod.GET)
	public ModelAndView show(
			ModelAndView mav) {
		
		mav.setViewName("item");
		List<Item> items = dao.getAllItemsByDesc();
		mav.addObject("items", items);
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		mav.addObject("attributes", attributes);
		mav.addObject("imgPath", imgPath);
		
		
		return mav;
	}
	
	@RequestMapping(value ="/item", method=RequestMethod.POST)
	public ModelAndView search(
			ModelAndView mav,
			@RequestParam("word") String word,
			@RequestParam("itemattribute") String attributeno) {
		List<Item> items = null;
		if(attributeno.equals("カテゴリーを選択してください")) {
			items = dao.getItemsByWord(word);
		}else{
			items = dao.getItemsByWordAndAttribute(word, Integer.parseInt(attributeno));
		}
		mav.addObject("items", items);
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		mav.addObject("attributes", attributes);
		mav.addObject("imgPath", imgPath);
		
		return mav;
	}
	
	@RequestMapping(value = "/itemlist/{attributeno}", method=RequestMethod.POST,
			produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String roomList(
			@PathVariable("attributeno") int attributeno)
			throws JSONException{
		
		List<Item> items = dao.getItemsByAttributeno(attributeno);
		JSONArray status = new JSONArray();
		
		for(Item item : items) {
			JSONObject data = new JSONObject();
			data.put("itemid",item.getItemid());
			data.put("itemname", item.getItemname());
			data.put("imgPath", imgPath);
			status.put(data);
		}

	    return status.toString();
		
	} 

}
