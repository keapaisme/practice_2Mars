package com.company;
import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args)throws Exception {
	// write your code here
       Simulation ph1 = new Simulation();
       ph1.runSimulation();
       ph1.loadItems("phase-1.txt");
    }
}

class Item{

    int weight;
    String name;
//構造函數
    public Item(int weight,String name){
        this.name=name;
        this.weight=weight;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

interface SpaceShip {
    boolean launch=true;
    boolean land=true;
    boolean canCarry(int weight);
    boolean carry(String name);
}

class Rocket implements SpaceShip{
    @Override
    public boolean canCarry(int weight) {
        return false;
    }

    @Override
    public boolean carry(String name) {
        return false;
    }
}

class Simulation {
    String missCode;// mission code : ph1,ph2
    public ArrayList load=null;
    //構造函數
    public Simulation(){
        load = new ArrayList();
    }
    //讀取運送物品清單,並返回Item ArrayList:
    public void loadItems(String missCode)throws Exception{

            File file = new File(missCode);
            Scanner scan = new Scanner(file);
            int i = 0;String txt;
            while (scan.hasNextLine()){
                txt=scan.nextLine();
                System.out.println(txt);
                //name=txt.split("=");
                //weight=txt.nextLine();
                //load.add(i,txt);
                i++;
            }
            //System.out.println(load.get(8));
    }



    public void loadU1(){}
    public void loadU2(){}
    public void runSimulation(){}
}

class U1 extends Rocket{}

class U2 extends Rocket{}
