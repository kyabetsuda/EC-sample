package jp.TsudaJun.spring.mysample.model;

public class CartItem{
	
	private Item item;
	private int quantity;
	
	public CartItem(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
}
