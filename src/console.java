import java.util.Scanner;

public class console {
	
	static String message = "Welcome to your password manager!\nEnter [a] to enter a new password \nEnter [s] to search for a password \nEnter [c] to change a password\n"
			+ "Enter [d] to delete a password \nEnter [q] to quit";

	public static void main(String[] args) {
		String website;
		String username;
		String password;
		Scanner scanner = new Scanner(System.in);
		hashTable table = new hashTable();
		boolean keepGoing = true;
		while (keepGoing) {
			System.out.println(message);
			String input = scanner.nextLine();
			input = input.toLowerCase();
			switch (input) {
			case "a":
				System.out.println("Enter the website");
				website = scanner.nextLine();
				System.out.println("Enter your username");
				username = scanner.nextLine();
				System.out.println("Enter your password");
				password = scanner.nextLine();
				table.insert(website, username, password);
				System.out.println("Password was added");
				break;
			case "s":
				System.out.println("Enter the website");
				website = scanner.nextLine();
				Account a = table.get(website);
				username = a.getUsername();
				password = a.getPassword();
				System.out.println("Website: " + website + "\nUserName: " + username + "\nPassword:  " + password );
				break;
			case "c":
				System.out.println("Enter the website: ");
				website = scanner.nextLine();
				System.out.println("Enter your new username: ");
				username = scanner.nextLine();
				System.out.println("Enter your new password: ");
				password = scanner.nextLine();
				table.get(website).setUsername(username);
				table.get(website).setPassword(password);
				System.out.println("Password was changed");
				break;
			case "d":
				System.out.println("Enter the website: ");
				website = scanner.nextLine();
				table.remove(website);
				System.out.println("Password was removed");
				break;
			case "q":
				keepGoing = false;
				break;
			}

		}
		scanner.close();
	}
}
