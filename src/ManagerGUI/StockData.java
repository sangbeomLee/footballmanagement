package ManagerGUI;

public class StockData {
	
	 private String stockname;
	 private String Quantity ;
	 private String possible_Quantity;
	 
	 
	 public StockData(){
		 
	 }
	 
	 public StockData(String stockname,String Quantity,String possible_Quantity){
		 this.stockname = stockname;
		 this.Quantity = Quantity;
		 this.possible_Quantity = possible_Quantity;
	 }

	 public void Setstockname(String stockname) {
		 this.stockname = stockname;
	 }
	 
	 public String Getstockname() {
		 return stockname;
	 }
	 
	 
	 public void SetQuantity(String Quantity) {
		 this.Quantity = Quantity;
	 }
	 
	 public String GetQuantity() {
		 return Quantity;
	 }
	 
	 public void Setpossible_Quantity(String possible_Quantity) {
		 this.possible_Quantity = possible_Quantity;
	 }
	 
	 public String Getpossible_Quantity() {
		 return possible_Quantity;
	 }
	 
	 
	 

}
