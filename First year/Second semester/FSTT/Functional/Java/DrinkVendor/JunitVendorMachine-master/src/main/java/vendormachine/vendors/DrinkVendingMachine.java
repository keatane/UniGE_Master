package vendormachine.vendors;

import vendormachine.users.Person;
import vendormachine.vendors.enums.BRANDS;
import vendormachine.vendors.item.Snack;

import java.util.ArrayList;
import java.util.Arrays;

public class DrinkVendingMachine {

    private BRANDS brandName;
    private float availableCredit;
    private ArrayList<Snack> drinkList = new ArrayList<Snack>();

    public DrinkVendingMachine(){

        //Select generic brand
        this.brandName = BRANDS.Generic;
        this.drinkList = new ArrayList<Snack>();

        populateDrinkList();
    }

    public DrinkVendingMachine(float startCredit, BRANDS brand) {
        this.availableCredit = startCredit;
        this.brandName = brand;

        populateDrinkList();
    }
    
    //===============================
    //===  Methods
    //===============================

    private void populateDrinkList(){
        this.drinkList.addAll(Arrays.asList(DefaultSnacks.snackList));
    }

    // This is how we'll select our "snack" from the array
    public Snack selectDrink(int arrayPosition){
        if(arrayPosition >=  drinkList.size() || arrayPosition < 0) {
            System.out.println("WARNING: Item does not exist.");
            return null;
        }
        
        Snack selection = drinkList.get(arrayPosition);

        if(this.availableCredit < selection.cost()){
            System.out.println("WARNING: you do not have enough credit!");
            System.out.println("WARNING: machine credit - £" + this.availableCredit);
            return null;
        }
        else {
            // let's deduct machine credit
            availableCredit -= selection.cost();
            return selection;
        }
    }

    public void giveCredit(Person user, float credit){
        System.out.println("INFO: You have entered "+credit);
        this.availableCredit += user.getCredit(credit);
    }

    // create addrink method
    public void addDrink(Snack drink){
        this.drinkList.add(drink);
    }

    // create removeDrink method
    public void removeDrink(Snack drink){
        this.drinkList.remove(drink);
    }

    // create hasDrink method
    public boolean hasDrink(Snack drink){
        return this.drinkList.contains(drink);
    }

    //===============================
    //===  'get'ors
    //===============================

    public BRANDS getBrandName(){
        return this.brandName;
    }

    public ArrayList<Snack> getDrinkList(){
        return this.drinkList;
    }

    public float getAvailableCredit(){
        return this.availableCredit;
    }


    //===============================
    //===  'set'ors
    //===============================

    public void setBrandName(BRANDS brand){
        this.brandName = brand;
    }

    public void setDrinkList(ArrayList<Snack> drinkList){
        this.drinkList = drinkList;
    }

    public void setAvailableCredit(float credit){
        this.availableCredit = credit;
    }

}
