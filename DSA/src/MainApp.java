import java.util.Scanner;
public class MainApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enetr initial capacity : ");
		int n = sc.nextInt();
		Stack.create(n);
		while(true)
		{
			System.out.println("1. Push");
			System.out.println("2. Pop");
			System.out.println("3. Peek");
			System.out.println("4. Traverse");
			System.out.println("5. Exit");
			
			System.out.print("Enter your choice:");
			int ch = sc.nextInt();
			
			if (ch == 1)
			{
				System.out.println("Enter the elemnt : ");
				int ele = sc.nextInt();
				Stack.push(ele);
			}
			else if (ch == 2)
			{
				int ele = Stack.pop();
				if(ele == -1)
					System.out.println("Stack is underflow.");
				else
					System.out.println("Popped : " + ele);
			}
			else if (ch == 3)
			{
				int ele = Stack.peek();
				if(ele == -1)
					System.out.println("Stack is underflow.");
				else
				System.out.println("Element at the top : " + ele);
			}
			else if (ch == 4)
			{
				Stack.traverse();
			}
			else if (ch == 5)
			{
				System.out.println("End of the Program");
				break;
			}
			else
			{
				System.out.println("Invalid Choice");
			}
		}
		sc.close();
	}
}

class Stack
{
	static int[] stk;
	static int top = -1, size;
	static void create(int n)
	{
		size = n;
		stk = new int[size];
		System.out.println("Stack is ready.");
	}
	static boolean isFull()
	{
		if (top == size -1)
			return true;
		else
			return false;
	}
	static void push(int ele)
	{
		if (isFull())
		{
			System.out.println("Stack is Overflow.");
		}
		else
		{
			top++;
			stk[top] = ele;
			System.out.println("Element pushed.");
		}
	}
	static boolean isEmpty()
	{
		if (top == -1)
			return true;
		else
			return false;
	}
	static int pop()
	{
		if(isEmpty())
			return -1;
		else
			return stk[top--];
	}
	static int peek()
	{
		if(isEmpty())
			return -1;
		else
			return stk[top];
	}
	static void traverse()
	{
		System.out.println("Traverse : ");
		for(int i = 0; i<=top; i++)
			System.out.println(stk[top-i]);
	}
}