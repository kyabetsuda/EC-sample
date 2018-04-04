package jp.TsudaJun.spring.mysample.Controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.DAO.ItemAttributeDao;
import jp.TsudaJun.spring.mysample.DAO.ItemDao;
import jp.TsudaJun.spring.mysample.model.Item;
import jp.TsudaJun.spring.mysample.model.ItemAttribute;
import jp.TsudaJun.spring.mysample.model.User;

@Controller
//@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
//maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class ItemRegisterController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	ItemDao dao;
	
	@Autowired
	ItemAttributeDao iaDao;
	
	@Value("${img.path}")
	private String imgPath;
	
	@RequestMapping(value ="/itemregister", method=RequestMethod.GET)
	public ModelAndView show(
			@ModelAttribute Item item,
			ModelAndView mav){
		mav.setViewName("itemregister");
		mav.addObject("msg", "商品登録");
		List<ItemAttribute> attributes = iaDao.getAllAttributes();
		mav.addObject("attributes", attributes);
		mav.addObject("check", true);
		return mav;
	}
	
	@RequestMapping(value ="/itemregister", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ModelAndView register(
			@Validated @ModelAttribute("item") Item item,
			BindingResult result,
//			@RequestParam MultipartFile file,
			Principal principal,
			HttpServletRequest request,
			ModelAndView mav) throws IOException, ServletException{
		
		if(!result.hasErrors()) {
			item.setUserid(principal.getName());
			BigDecimal price_bd = new BigDecimal(String.valueOf(item.getPrice()));
			BigDecimal tax_rate = new BigDecimal(0.08);
			BigDecimal tax = price_bd.multiply(tax_rate).setScale(0, BigDecimal.ROUND_HALF_UP);
			BigDecimal includingtax_bd = price_bd.add(tax);
			item.setTax(tax.intValue());
			item.setIncludingtax(includingtax_bd.intValue());
			dao.persist(item);
//			if (file.getSize() > 0 && file.getContentType().equals("image/jpeg") ) {
//				File imageFile = new File(imgPath + item.getItemid() +".jpg");
//				file.transferTo(imageFile);
//			}else {
//				BufferedImage img = ImageIO.read(new File(imgPath + "noimage.png"));
//				ImageIO.write(img, "jpg", new File(imgPath + item.getItemid() +".jpg"));
//			}
			mav = new ModelAndView("redirect:/kanryo/?msg=itemregister");
		}else{
			mav.addObject("msg","エラーが発生しました");
			List<ItemAttribute> attributes = iaDao.getAllAttributes();
			mav.addObject("attributes", attributes);
			mav.addObject("check", true);
		}
		return mav;
	}

}
