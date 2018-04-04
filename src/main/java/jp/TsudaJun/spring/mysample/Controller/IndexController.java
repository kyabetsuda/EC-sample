package jp.TsudaJun.spring.mysample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.mysample.DAO.ItemDao;
import jp.TsudaJun.spring.mysample.model.Item;
import jp.TsudaJun.spring.mysample.model.ItemAttribute;

@Controller
public class IndexController {
	
	@Autowired
	ItemDao iDao;
	
	@Autowired
	ItemAttributeDao iaDao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ={"/", "/index"}, method=RequestMethod.GET)
	public ModelAndView show(ModelAndView mav){
		System.out.println("hello logs!");
		List<Item> items = iDao.getAllItemsByDesc();
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		mav.addObject("items", items);
		mav.addObject("attributes", attributes);
		mav.setViewName("index");
		return mav;
		
	}

}
