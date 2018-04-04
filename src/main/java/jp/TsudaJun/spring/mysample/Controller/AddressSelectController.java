package jp.TsudaJun.spring.mysample.Controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.DAO.UserAddressDao;
import jp.TsudaJun.spring.mysample.model.UserAddress;

@Controller
public class AddressSelectController {
	
	@Autowired
	UserAddressDao uaDao;
	
	
	@RequestMapping(value ="/addressselect", method=RequestMethod.GET)
	public ModelAndView show(
			HttpServletRequest request,
			Principal principal,
			@ModelAttribute("address") UserAddress address,
			ModelAndView mav) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}
		
		System.out.println(session.getAttribute("addressid"));
		
		List<UserAddress> addresses = uaDao.getAddressByUserid(principal.getName());
		mav.addObject("addresses", addresses);
		mav.addObject("userid", principal.getName());
		mav.setViewName("addressselect");
		
		return mav;
	}
	
	@RequestMapping(value ="/addressselect", method=RequestMethod.POST)
	public ModelAndView register(
			ModelAndView mav,
			Principal principal,
			HttpServletRequest request,
			@Validated @ModelAttribute("address") UserAddress address,
			BindingResult result) {
		
		
		if(!result.hasErrors()) {
			address.setUserid(principal.getName());
			uaDao.persist(address);
			//addressidセッション登録
			HttpSession session = request.getSession(false);
			if(session == null) {
				session = request.getSession(true);
			}
			session.setAttribute("addressid", String.valueOf(address.getAddressid()));
			//redirect
			mav = new ModelAndView("redirect:/payselect");
		}else {
			List<UserAddress> addresses = uaDao.getAddressByUserid(principal.getName());
			mav.addObject("addresses", addresses);
			mav.addObject("userid", principal.getName());
		}
		return mav;
	}
	
	@RequestMapping(value ="/addressselect/{addressid}", method=RequestMethod.POST)
	public ModelAndView choose(
			@PathVariable("addressid") int addressid,
			HttpServletRequest request,
			ModelAndView mav) {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
		}
		session.setAttribute("addressid", String.valueOf(addressid));
		mav = new ModelAndView("redirect:/payselect");
		return mav;
	}

}
