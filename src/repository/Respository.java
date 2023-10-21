 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import common.ScannerFactory;
import dataAccess.DaoInputString;

/**
 *
 * @author ASUS
 */
public class Respository implements IRespository{
    
    ScannerFactory sc;

    public Respository() {
        sc = new ScannerFactory();
    }
    
    @Override
    public void getNumber(String input) {
        DaoInputString.Instance().printNumber(DaoInputString.Instance().getNumber(input));
    }

    @Override
    public void getCharacter(String input) {
        DaoInputString.Instance().printCharacter(DaoInputString.Instance().getCharacter(input));
    }
    
    public void doTwo(){
        System.out.println("Do thing");
        String input = sc.getScanner().nextLine();
        getNumber(input);
        getCharacter(input);
    }

    
}
