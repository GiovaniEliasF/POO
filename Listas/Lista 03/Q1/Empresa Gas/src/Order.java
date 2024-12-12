public class Order {
	private int id = 1000; 
	private String shippingAdress;
	private String orderTime;
	private String deliveryTime;
	private float totalOrder;
	private int qtyItens;
	private String creditCard;
	private String status = "Cancelado";

	public Order (){

	}

	public Order( String shippingAdress, String orderTime, String deliveryTime, float totalOrder, int qtyItens) {
		this.shippingAdress = shippingAdress;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.totalOrder = totalOrder;
		this.qtyItens = qtyItens;
	}

	public void confirmOrder(int i, String creditCard){
		this.creditCard = creditCard;
		this.id = id+i;
		this.status = "Confirmado";
		System.out.println("Pedido Confirmado!");
		System.out.println("ID do Pedido: "+ this.id);
		System.out.println("Horario do Pedido: " + this.orderTime);
	}

	public void confirmShipping(int i){
		System.out.println("Confirmado a entrega do Pedido: "+ i);
		this.status = "Entregue";
	}

	public void showOrder(int i){
		System.out.println("-------------------------");
		System.out.println(++i + "o Pedido");
		System.out.println("Id: "+ getId());
		System.out.println("Hora do Pedido: " + getOrderTime());
		System.out.println("Estimativa de Hora de Entrega: "+ getDeliveryTime());
		System.out.println("Quantidade de Botijoes: " + getQtyItens() );
		System.out.println("Preco total Pedido: " + getTotalOrder());
		System.out.println("Cartao de Credito Utilizado: " + getCreditCard());
		System.out.println("Status do Pedido: "+ getStatus());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShippingAdress() {
		return shippingAdress;
	}

	public void setShippingAdress(String shippingAdress) {
		this.shippingAdress = shippingAdress;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public float getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(float totalOrder) {
		this.totalOrder = totalOrder;
	}

	public int getQtyItens() {
		return qtyItens;
	}

	public void setQtyItens(int qtyItens) {
		this.qtyItens = qtyItens;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
