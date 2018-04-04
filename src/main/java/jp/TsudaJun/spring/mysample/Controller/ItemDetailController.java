package jp.TsudaJun.spring.mysample.Controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.DAO.ItemDao;
import jp.TsudaJun.spring.mysample.model.Cart;
import jp.TsudaJun.spring.mysample.model.CartItem;
import jp.TsudaJun.spring.mysample.model.Item;

@Controller
public class ItemDetailController {
	
	@Autowired
	ItemDao dao;
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/itemdetail/{itemid}", method=RequestMethod.GET)
	public ModelAndView show(
			@PathVariable("itemid") int itemid,
			ModelAndView mav) {
		
		mav.setViewName("itemdetail");
		mav.addObject("msg","商品詳細");
		
		Item item = dao.getItemById(itemid);
		if(item != null) {
			mav.addObject("item",item);
			mav.addObject("imgPath",imgPath);
			mav.addObject("check",true);
			if(item.getStock() == 0) {
				mav.addObject("disabled", true);
			}else {
				mav.addObject("submitCheck", true);
			}
		}else {
			mav = new ModelAndView("redirect:/error");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value ="/itemdetail", method=RequestMethod.POST)
	public ModelAndView intoCart(
			ModelAndView mav,
			HttpServletRequest request,
			@RequestParam("itemid") int itemid,
			@RequestParam("quantity") int quantity) {
		Item item = dao.getItemById(itemid);
		if(item.getStock() < quantity) {
			mav.addObject("item",item);
			mav.addObject("imgPath",imgPath);
			mav.addObject("check",true);
			mav.addObject("stockCheck", true);
			if(item.getStock() == 0) {
				mav.addObject("disabled", true);
			}else {
				mav.addObject("submitCheck", true);
			}
			return mav;
		}
		
		
		HttpSession session = request.getSession(false);
		if (session == null){
			session = request.getSession(true);
		}
		
		Cart cart = (Cart)session.getAttribute("cart");
		CartItem cartItem = new CartItem(item, quantity);
		
		if(cart == null) {
			cart = new Cart();
			cart.putItem(cartItem);
			session.setAttribute("cart", cart);
		}else {
			cart.putItem(cartItem);
			session.setAttribute("cart", cart);
		}
	
		mav = new ModelAndView("redirect:/kanryo/?msg=itemdetail");
		
		return mav;
	}

}
