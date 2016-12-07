package users.admin;
import users.User;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by piotrek on 06.12.16.
 */
class Admin extends User{

    /*Konstruktor klasy Admin - tworzy obiekt użytkownika
     *na podstawie danych z pliku. Znajduje odpowiednie dane
     *na podstawie loginu, pozyskanego przy logowaniu*/
    Admin(String _login){

        this.login = _login;
        String adminData = "";
        String fileName = Main.adminDataFilename;
        File file = new File(fileName);
        Scanner input;
        boolean found = false;

        try {
            input = new Scanner(file);
        }catch(IOException ex){
            Main.closeOnError("FATAL ERROR - database disappeared");
            return;
        }

        while(input.hasNextLine()){
            String temp = input.findInLine(login);
            if(temp != null){
                found = true;
                adminData = input.nextLine();
                break;
            }
            input.nextLine();
        }
        if(found)
            input = new Scanner(adminData);
        else{
            Main.closeOnError("FATAL ERROR - Admin not found after login");
            return;
        }

        input.useDelimiter(" ");

        password = input.next();
        name = input.next();
        surname = input.next();
        email = input.next();
    }

    @Override
    public ArrayList<String> getProfile(){
        ArrayList<String> profileData = new ArrayList<>();

        profileData.add(login);
        profileData.add(password);
        profileData.add(name);
        profileData.add(surname);
        profileData.add(email);

        return profileData;
    }

    @Override
    public String toString(){
        String result = "";
        result += login + " ";
        result += password + " ";
        result += name + " ";
        result += surname + " ";
        result += email + " ";

       return result;
    }
}
