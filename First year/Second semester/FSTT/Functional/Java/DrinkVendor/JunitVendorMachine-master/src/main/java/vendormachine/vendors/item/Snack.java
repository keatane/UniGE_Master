package vendormachine.vendors.item;

public class Snack implements Item{

    public String name;
    private float price;

    public Snack(float creditCost){
        this.price = creditCost;
    }

    public Snack(String name, float creditCost){
        this.name = name;
        this.price = creditCost;
    }
    
	//==================
	//===	Methods
	//==================

    public float cost(){
        return this.price;
    }

    public void setValue(float cost){
        this.price = cost;
    }

    // getter

    public String getName(){
        return this.name;
    }

    public float getPrice(){
        return this.price;
    }

    // setter

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(float price){
        this.price = price;
    }

}
