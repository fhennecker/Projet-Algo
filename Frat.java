import java.util.Vector;

public class Frat {

	private String _name;
	private int _budget;
	private Vector<Debt> _debtList;

	////////// GETTERS + SETTERS //////////
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }

	public int getBudget() { return _budget; }
	public void setBudget(int budget) { _budget = budget; }

	public Debt[] getDebtList() { return _debtList; }
	public void setDebtList(Vector<Debt> debtList) { _debtList = debtList; }

	////////// CONSTRUCTOR //////////
	public Frat(String name, int budget){
		setName(name);
		setBudget(budget);
		_d
	}
	public Frat(String name, int budget, Debt[] debtList){
		setName(name);
		setBudget(budget);
		setDebtList(debtList);
	}

	////////// WORK METHODS //////////
	public void addDebt(Frat creditor, int amount){

	}
}

class Debt {

	private int _amount;
	private Frat _creditor;

	////////// GETTERS + SETTERS //////////
	public int getAmount() { return _amount; }
	public void setAmount(int amount) { _amount = amount; }

	public Frat getCreditor() { return _creditor; }
	public void setCreditor(Frat creditor) { _creditor = creditor; }
}