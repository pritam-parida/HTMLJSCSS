
public class HasA {

	public static void main(String[] args) {	
		Address addr = new Address("CGU Line", "Bhubaneswar", "Odisha");
		Employee emp = new Employee(101, "Amar", 45000, addr);
		System.out.println("Details : " + emp);
	}

}

class Employee
{
	int id;
	String name;
	double salary;
	Address addr;
	
	Employee(int id, String name, double salary, Address addr)
	{
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.addr = addr;
	}
	public String toString()
	{
		return this.id + ", " + this.name + ", " + this.salary + ", " + this.addr;
	}
}

class Address
{
	String street, city, state;
	Address(String street, String city, String state)
	{
		this.street = street;
		this.city = city;
		this.state = state;
	}
	public String toString()
	{
		return this.street + ", " + this.city + ", " + this.state;
	}
}