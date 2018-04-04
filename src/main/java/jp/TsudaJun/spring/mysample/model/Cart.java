package jp.TsudaJun.spring.mysample.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	
	
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	public void putItem(CartItem cartItem) {
		int current_quantity = this.contains(cartItem);
		if(current_quantity > 0) {
			this.removeItem(cartItem);
			cartItem.setQuantity(cartItem.getQuantity() + current_quantity);
			this.cartItems.add(cartItem);
		}else {
			this.cartItems.add(cartItem);
		}
	}
	
	public CartItem getItemById(int itemid) {
		CartItem cartItem = null;
		for(int i = 0; i < this.cartItems.size(); i++) {
			if(this.cartItems.get(i).getItem().getItemid() == itemid) {
				cartItem = cartItems.get(i);
			}
		}
		
		return cartItem;
	}
	
	public int calcSum() {
		int sum = 0;
		for(CartItem cartItem : cartItems) {
			sum += cartItem.getItem().getIncludingtax() * cartItem.getQuantity();
		}
		
		return sum;
	}
	
	public List<CartItem> getCartItems(){
		return this.cartItems;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public void removeItem(CartItem cartItem) {
		for(int i = 0; i < this.cartItems.size(); i++) {
			if(this.cartItems.get(i).getItem().getItemid() == cartItem.getItem().getItemid()) {
				this.cartItems.remove(i);
			}
		}
	}
	
	public int contains(CartItem cartItem) {
		int quantity = 0;
		
		for(int i = 0; i < this.cartItems.size(); i++) {
			if(this.cartItems.get(i).getItem().getItemid() == cartItem.getItem().getItemid()) {
				quantity = this.cartItems.get(i).getQuantity();
			}
		}
		
		return quantity;
		
	}

}
