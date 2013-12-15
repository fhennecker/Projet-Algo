import java.util.Vector;

public class Frat {

	private String _name;
	private int _budget;
	private Vector<Debt> _debtList;

	////////// GETTERS + SETTERS //////////
	public String getName() { return _name; }
	public void setName(String name) { _name = name; }
	public boolean isFrat(String fratName) { return (fratName.equals(getName())); }
	public boolean isFrat(Frat otherFrat) { return (this == otherFrat); }

	public int getBudget() { return _budget; }
	public void setBudget(int budget) { _budget = budget; }

	public Vector<Debt> getDebtList() { return _debtList; }
	private Debt getDebtAtIndex(int i){ 
		return getDebtList().get(i);
	}

	public int getDebt(Frat creditor){
		try {
			int i = 0;
			while (getDebtAtIndex(i).getCreditor() != creditor){
				++i;
			}
			return (getDebtAtIndex(i).getAmount());
		} 
		catch (ArrayIndexOutOfBoundsException e){
			// No debt has been found
			return 0;
		} 
	}

	public void setDebt(Frat creditor, int newAmount){
		try {
			int i = 0;
			while (getDebtAtIndex(i).getCreditor() != creditor){
				++i;
			}
			getDebtAtIndex(i).setAmount(newAmount);
		} 
		catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Error while changing debt : creditor could not be found.");
		}
	}

	public boolean hasDebt(Frat otherFrat){
		/** Returns true if this frat has debts for other frat, false otherwise.*/
		return (getDebt(otherFrat) != 0);
	}

	////////// CONSTRUCTOR //////////
	public Frat(String name, int budget){
		setName(name);
		setBudget(budget);
		_debtList = new Vector<Debt>();	
	}

	public String toString() {
		return getName();
	}

	////////// WORK METHODS //////////
	public void addDebt(Frat creditor, int amount){
		_debtList.add(new Debt(creditor, amount));
	}

	public void deleteDebt(Frat creditor){
		/** deleteDebt deletes debt of a creditor. */
		try {

			int i = 0;
			while (getDebtAtIndex(i).getCreditor() != creditor){
				++i;
			}
			getDebtList().remove(i);

		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Error while deleting debt : creditor could not be found.");
		}
	}

	public void changeDebt(Frat creditor, int amount){
		/** changeDebt allows to add an amount to creditor's debt. Positive and
			negative amounts are accepted. */
		try{
			setDebt(creditor, getDebt(creditor) + amount);
		}
		catch ( ArrayIndexOutOfBoundsException e ) {
			System.out.println("Error while changing debt : creditor could not be found.");
		}
	}
	
	/*
	public static void main(String[] args) {
								///////////////////////////////////////////////////////////////////// REMOVE MAIN
	
		Frat f = new Frat("CI", 100);
		Frat g = new Frat("CP", 200);
		System.out.println(f.isFrat("CI "));
		//System.out.println(f.hasDebt(g));
		//f.addDebt(g, 10);
		//System.out.println(f.getDebt(g));
		//f.changeDebt(g, 5);
		//System.out.println(f.getDebt(g));
		//System.out.println(f.hasDebt(g));



	}
	*/
	
}

class Debt {

	private int _amount;
	private Frat _creditor;

	////////// GETTERS + SETTERS //////////
	public int getAmount() { return _amount; }
	public void setAmount(int amount) { _amount = amount; }

	public Frat getCreditor() { return _creditor; }
	public void setCreditor(Frat creditor) { _creditor = creditor; }

	////////// CONSTRUCTOR //////////
	public Debt(Frat creditor, int amount){
		setCreditor(creditor);
		setAmount(amount);
	}
}