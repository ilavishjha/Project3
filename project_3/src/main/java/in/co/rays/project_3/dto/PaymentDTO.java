package in.co.rays.project_3.dto;

public class PaymentDTO extends BaseDTO {
private long id;
private String type;
private int amount;
public Long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
@Override
public String getKey() {
	return null;
	
}
@Override
public String getValue() {
	return null;
	
}

}
