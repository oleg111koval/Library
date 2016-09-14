package LibraryManaging;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookCRUD {
	private	static  String dbURL = "jdbc:mysql://localhost:3306/librarydb";
	private static	String username = "root";
	private static 	String password = "123456";
	
	public static void insertBook (Book book)throws SQLException{
		String query = "INSERT INTO books (Name,Author) VALUES (?,?)";
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, book.getName());
		statement.setString(2, book.getAuthor());
		int rowsInserted;
		try{
			rowsInserted= statement.executeUpdate();
			System.out.println(book+"was added");
		}catch (SQLException e) {
		e.printStackTrace();	
		}finally {
			conn.close();	
		}	
	}
	
	public static List<Book> selectBookByName(String name)throws SQLException{
		
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM books WHERE name="+"'"+name+"'";
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		
		PreparedStatement statement = conn.prepareStatement(query);
		//statement.setString(1, name);
		//System.out.println(statement);
		
		ResultSet result =  statement.executeQuery(query);
		
		while (result.next()){
			books.add(new Book(result.getString("Name"),result.getString("Author")));	   
		}
		
		//System.out.println(books);
		return books;
		
		
	}
	
	
	public static List<Book> selectAllBooks()throws SQLException{
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM books ORDER BY Name";
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		Statement statement = conn.createStatement();
		ResultSet result =  statement.executeQuery(query);
		
		while (result.next()){
			books.add(new Book(result.getString("Name"),result.getString("Author")));	   
		}
		return books;
		
	}
		
	public static void deleteBook (String name) throws SQLException{
		List<Book> booksToDelete = selectBookByName(name);
		if  (booksToDelete.size()<=1){
			deleteBookByName(name);
		}else
			deleteBookByNameAndAuthor(booksToDelete);
	}
private static void deleteBookByName(String name)throws SQLException{
	String query = "DELETE FROM books WHERE Name="+name+";";
	Connection conn = DriverManager.getConnection(dbURL, username, password);
	PreparedStatement statement = conn.prepareStatement(query);
	//statement.setString(1, name);

	//System.out.println("\n===========================\n"+query);
	try{
	statement.executeUpdate();
	System.out.println("Book:  "+name + "was succesfully deleted");
	}catch (SQLException e) {
		System.out.println("book"+name+" doesn't exist");
	}finally {
		conn.close();
	}
	}

private static void deleteBookByNameAndAuthor(List<Book> books)throws SQLException{
	int i=0;
	System.out.println("we have few books with such name please choose one by typing a number of book:");
	for(Book book:books){
	System.out.println((++i)+"  "+book);
	}
	
	Scanner in = new Scanner(System.in);
	int index = in.nextInt();
	index--;
	String query = "DELETE FROM books WHERE Name=? AND Author =?";
	Connection conn = DriverManager.getConnection(dbURL, username, password);
	PreparedStatement statement = conn.prepareStatement(query);
	statement.setString(1, books.get(index).getName());
	statement.setString(2, books.get(index).getAuthor());
	try{
	statement.executeUpdate();
	System.out.println("Book"+books.get(index).getName() + "was succesfully deleted");
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		conn.close();
	}
	}

public static void editBook(String name) throws SQLException{
	List<Book> booksToEdit = selectBookByName(name);
	if  (booksToEdit.size()<=1){
		editBookByName(name);
	}else
		editBookByNameAndAuthor(booksToEdit);
}

private static void editBookByNameAndAuthor(List<Book> books) throws SQLException {
	int i=0;
	System.out.println(" we have few books with such name please choose one by typing a number of book:");
	for(Book book:books){
	System.out.println((++i)+"  "+book);
	}
	
	Scanner in = new Scanner(System.in);
	int index = Integer.parseInt(in.nextLine());
	
	index--;
	
	
	System.out.println("\n Input new name of Book: ");
	String newName = in.nextLine();
	
	
	
	String query = "UPDATE  books SET Name='"+newName+"' WHERE Name='"+books.get(index).getName()+"' AND Author='"+books.get(index).getAuthor()+"'";
	Connection conn = DriverManager.getConnection(dbURL, username, password);
	PreparedStatement statement = conn.prepareStatement(query);
//	statement.setString(1, books.get(index).getName());
	//statement.setString(2, books.get(index).getAuthor());
	System.out.println(query);
	try{
	statement.executeUpdate();
	System.out.println("Book"+books.get(index).getName() + "was succesfully updated");
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		conn.close();
	}
	}
	


private static void editBookByName(String name) throws SQLException {
	Scanner in = new Scanner(System.in);
	System.out.println("Input new name of Book: ");
	String newName = in.nextLine();
	
	
	String query = "UPDATE books SET Name='"+newName+"' WHERE Name='"+name+"'";
	Connection conn = DriverManager.getConnection(dbURL, username, password);
	PreparedStatement statement = conn.prepareStatement(query);
	//statement.setString(1, newName);
	//statement.setString(1, name);
System.out.println(query);
	try{
	statement.executeUpdate();
	System.out.println("Book"+name + "was succesfully updated");
	}catch (SQLException e) {
		System.out.println("book"+name+" doesn't exist");
	}finally {
		conn.close();
	}
	
	
	
}






}



