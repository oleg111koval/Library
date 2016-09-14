package LibraryManaging;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LibraryManager {
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String command;
		/*List<Book> books = BookCRUD.selectAllBooks();
		System.out.println(books);
		BookCRUD.insertBook(new Book("harry","Koval"));*/
		System.out.println("What do you want to do?:"
				+ "\n add book"
				+ "\n remove {book_name}"
				+ "\n edit {book_name}"
				+ "\n all books"
				+ "\n exit");
		command=in.nextLine();
		
		while (!command.equals("exit")){
			switch (command){
			case  "add book":addBook();break;
			case  "remove":removeBook();break;
			case  "edit":editBook();break;
			case "all books":getAllBooks();break;
			
			
			}
			
			System.out.println("What do you want to do :"
					+ "\n add book"
					+ "\n remove {book_name}"
					+ "\n edit {book_name}"
					+ "\n all books"
					+ "\n exit");
			
			command=in.nextLine();
			
			
		}
		
		

	}

	private static void getAllBooks() throws SQLException {
		List<Book> allBooks = BookCRUD.selectAllBooks();
		
		int i=0;
		if(allBooks.size()!=0)
		for (Book book: allBooks){
		System.out.println((++i)+". "+book);	
		}else 
			System.out.println("NO BOOKS");
		
	}

	private static void editBook() throws SQLException {
		Scanner in = new Scanner(System.in);
		System.out.println("Input Name:");
		BookCRUD.editBook(in.nextLine());
		
	}

	private static void removeBook() throws SQLException {
		Scanner in = new Scanner(System.in);
		System.out.println("Input Name:");
		String name = in.nextLine();
		BookCRUD.deleteBook(name);
		
		
	}

	private static void addBook() throws SQLException {
		Scanner in = new Scanner(System.in);
		System.out.println("Input name and author:");
		String nameAndAuthor= in.nextLine();
		Book book = new Book(Pattern.compile("\" ").split(nameAndAuthor)[0].substring(1), Pattern.compile("\" ").split(nameAndAuthor)[1]);
		BookCRUD.insertBook(book);
		
	}

}
