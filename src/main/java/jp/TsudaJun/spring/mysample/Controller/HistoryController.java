package jp.TsudaJun.spring.mysample.Controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.TsudaJun.spring.mysample.DAO.ItemDao;
import jp.TsudaJun.spring.mysample.DAO.SellDao;
import jp.TsudaJun.spring.mysample.DAO.UserAddressDao;
import jp.TsudaJun.spring.mysample.model.Item;
import jp.TsudaJun.spring.mysample.model.Sell;
import jp.TsudaJun.spring.mysample.model.UserAddress;

@Controller
public class HistoryController {
	
	@Autowired
	SellDao sDao;
	
	@Autowired
	ItemDao iDao;
	
	@Autowired
	UserAddressDao uaDao;
	
	@RequestMapping(value ="/history", method=RequestMethod.GET)
	public ModelAndView show(
			Principal principal,
			ModelAndView mav) {
		
		List<Sell> sells = sDao.getSellsByUserid(principal.getName());
		List<HyojiSell> hyojiSells = new ArrayList<HyojiSell>();
		for(Sell sell : sells) {
			HyojiSell hyojiSell = new HyojiSell();
			//sell
			hyojiSell.setSell(sell);
			//itemname
			Item item = iDao.getItemById(sell.getItemid());
			hyojiSell.setItemname(item.getItemname());
			//date
			Timestamp date_timestamp = sell.getBoughtymd();
			String TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
			String date_string = new SimpleDateFormat(TIME_FORMAT).format(date_timestamp);
			hyojiSell.setDate(date_string);
			//address
			UserAddress useraddress = uaDao.getAddressByAddressid(sell.getAddressid());
			hyojiSell.setAddress(useraddress.getAddress());
			hyojiSells.add(hyojiSell);
		}
		mav.addObject("sells", hyojiSells);
		mav.setViewName("history");
		return mav;
	}
	
	private class HyojiSell {
		
		private Sell sell;
		private String itemname;
		private String date;
		private String address;
		
		public void setSell(Sell sell) {
			this.sell = sell;
		}
		
		public Sell getSell() {
			return this.sell;
		}
		
		public void setItemname(String itemname) {
			this.itemname = itemname;
		}
		
		public String getItemname() {
			return this.itemname;
		}
		
		public void setDate(String date) {
			this.date = date;
		}
		
		public String getDate() {
			return this.date;
		}
		
		public void setAddress(String address) {
			this.address = address;
		}
		
		public String getAddress() {
			return this.address;
		}
		
	}

}
