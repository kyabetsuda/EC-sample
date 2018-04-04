package jp.TsudaJun.spring.mysample.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.model.Cart;
import jp.TsudaJun.spring.mysample.model.CartItem;

@Controller
public class CartController {
	
	@Value("${img.accessPath}")
	private String imgPath;
	
	@RequestMapping(value ="/cart", method=RequestMethod.GET)
	public ModelAndView show(
			HttpServletRequest request,
			ModelAndView mav) {
		
		HttpSession session = request.getSession(false);
		if (session != null){
			Cart cart = (Cart)session.getAttribute("cart");
			if(cart != null) {
				List<CartItem> cartItems = cart.getCartItems();
				mav.addObject("items", cartItems);
				mav.addObject("imgPath", imgPath);
				mav.addObject("total", cart.calcSum());
				mav.addObject("check", true);
			}else {
				mav.addObject("check", false);
				mav.addObject("msg", "カートに商品がありません");
			}
		}
		
		return mav;
	}
	
	@RequestMapping(value ="/cart", method=RequestMethod.POST)
	public ModelAndView toAddressSelect(
			ModelAndView mav) {
		
		mav = new ModelAndView("redirect:/addressselect");
		return mav;
	}

}
