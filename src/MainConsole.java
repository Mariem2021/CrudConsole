import java.sql.*;
import java.util.*;
import java.util.Scanner;

public class MainConsole {
   public static void main(String []args) 
      {
	   		
	   		Scanner scan = new Scanner(System.in);
	   		
	   		String dbURL = "jdbc:mysql://localhost:3306/gestionusers";
	   		String username = "root";
	   		String password = "";
	   		String sql;
	   		
	   		String nomUser;
	   		String prenomUser; 
	   		String login;
	   		String mdp;
	   		//int id;
	   		
	        System.out.println("Bienvenu chers utilisateurs!!!");
	        System.out.println("----Menu----");
	        System.out.println("1: Ajout d'un utilisateur");
	        System.out.println("2: Modification des utilisateur");
	        System.out.println("3: Supression d'un utilisateur");
	        System.out.println("4: Liste des utilisateurs");
	        System.out.println("Veuillez effectuez votre choix");
           
           int choice = scan.nextInt();
           
           try {
        	   Connection connect = DriverManager.getConnection(dbURL, username, password);
        	   PreparedStatement statement;
        	   
        	   switch(choice) {
        	   
        	   		case 1 : // ajoutons des utilisateurs
        	   			
        	   			System.out.println("Veuillez renseigner le nom de l'utilisateur");
        	   			nomUser = scan.nextLine();
        	   			System.out.println();
        	   			System.out.println("Veuillez renseigner le prenom de l'utilisateur");
        	   			prenomUser = scan.nextLine();
        	   			System.out.println();
        	   			System.out.println("Veuillez renseigner le login de l'utilisateur");
        	   			login = scan.nextLine();
        	   			System.out.println();
        	   			System.out.println("Veuillez renseigner le mot de passe de l'utilisateur");
        	   			mdp = scan.nextLine();
        	   			
        	   			sql = "insert into users (nomUser, prenomUser, login, mdp) values (?, ?, ?, ?)";
        	   			
        	   			statement = connect.prepareStatement(sql);
        	   			statement.setString(1, nomUser);
        	   			statement.setString(2, prenomUser);
        	   			statement.setString(3, login);
        	   			statement.setString(4, mdp);
        	   			
        	   			int rowsInsered = statement.executeUpdate();
        	   			if(rowsInsered > 0) {
        	   				System.out.println("Ajout d'un nouveau utilisateur");
        	   			}
        	   			break;
        	   			
        	   		case 2 : //Modifier un utilisateur
        	   			
        	   			System.out.println("Veuillez renseigner le nom de l'utilisateur");
        	   			nomUser = scan.nextLine();
        	   			System.out.println("Veuillez renseigner le prenom de l'utilisateur");
        	   			prenomUser = scan.nextLine();
        	   			//System.out.println("Veuillez renseigner le login de l'utilisateur");
        	   			//login = scan.nextLine();
        	   			System.out.println("Veuillez renseigner le mot de passe de l'utilisateur");
        	   			mdp = scan.nextLine();
        	   			
        	   			sql = "UPDATE users SET nomUser=?, prenomUser=?, login=?, mdp=?  WHERE id=?";
        	   		 
        	   			statement = connect.prepareStatement(sql);
        	   			statement.setString(1, nomUser);
        	   			statement.setString(2, prenomUser);
        	   			//statement.setString(3, login);
        	   			statement.setString(4, mdp);
        	   			 
        	   			int rowsUpdated = statement.executeUpdate();
        	   			if (rowsUpdated > 0) {
        	   			    System.out.println("Utilisateur modifie");
        	   			}
        	   			break;
        	   			
        	   		case 3 : //Supression d'utilisateur
        	   			
        	   			sql = "DELETE FROM users WHERE id=?";
        	   		 
        	   			statement = connect.prepareStatement(sql);
        	   			statement.setString(1, "marieme");
        	   			 
        	   			int rowsDeleted = statement.executeUpdate();
        	   			if (rowsDeleted > 0) {
        	   			    System.out.println("Utilisateur supprime");
        	   			}
        	   			
        	   		case 4 : // Liste des utilisateurs
        	   			
        	   			sql = "SELECT * FROM user";
        	   		 
        	   			Statement create = connect.createStatement();
        	   			ResultSet result = create.executeQuery(sql);
        	   			 
        	   			int count = 0;
        	   			 
        	   			while (result.next()){
        	   			    nomUser = result.getString(2);
        	   			    prenomUser = result.getString(3);
        	   			    login = result.getString(4);
        	   			    mdp= result.getString(5);
        	   			 
        	   			    String output = "User #%d: %s - %s - %s - %s";
        	   			    System.out.println(String.format(output, ++count, nomUser, prenomUser, login, mdp));
        	   			}
        	   			break;
        	   			
        	   }
        	   
        	   connect.close();
        	   
           }
           
           catch(SQLException e) {
        	   e.printStackTrace();
           }
              
      }
}
