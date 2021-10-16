package springBootJdbc.sbjdbc.model;

public class BankAccountInfo {
	private Long id;
	private String fullName;
	private double balance;
	
	public BankAccountInfo(Long _id, String _fullName, double _balance) {
		super();
		this.id = _id;
		this.fullName = _fullName;
		this.balance = _balance;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long _id) {
		this.id =_id;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String _fullName) {
		this.fullName = _fullName;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double _balance) {
		this.balance = _balance;
	}
}
