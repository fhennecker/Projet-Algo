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
			if (newAmount != 0){
				getDebtAtIndex(i).setAmount(newAmount);
			}
			else{
				deleteDebt(creditor);
			}
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
	
	public void deleteDebt(int index){
		_debtList.remove(index);
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
	
	public void payBack(){
		Vector<Debt> debtList = new Vector<Debt>(_debtList.size());
		for(int i = 0;i<_debtList.size();i++){//sort debts by amount in debtList variable
			int j = 0;
			while (j<debtList.size() && _debtList.get(i).getAmount()>debtList.get(j).getAmount()){
				j++;
			}
			debtList.add(j,_debtList.get(i));
		}
		for(int i = 0;i<debtList.size();i++){//payBack maximum of debt starting with cheapest one
			Debt tmp = debtList.get(i);				//then recall payback on creditor to check if he can't
			if (_budget>=tmp.getAmount()){    //payback debts with money he was just given
				setBudget(_budget-tmp.getAmount());
				tmp.getCreditor().setBudget(tmp.getCreditor().getBudget()+tmp.getAmount());
				_debtList.remove(tmp);
			}else{
				tmp.getCreditor().setBudget(tmp.getCreditor().getBudget()+getBudget());
				changeDebt(tmp.getCreditor(),-getBudget());
				setBudget(0);
			}
			tmp.getCreditor().payBack();
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