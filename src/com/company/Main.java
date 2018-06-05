package com.company;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)throws Exception {
	// write your code here
       //
       //ph1.loadItems("phase-1.txt");
       Simulation ph2 = new Simulation();
       ph2.loadItems("phase-2.txt");
       ph2.loadU1();

    }
}





//Item類含火箭要携帶的物品及其重量
class Item{
    private int weight ;
    private String name;

//構造函數
    public Item(String name, int weight){
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



//SpaceShip
interface SpaceShip {
    boolean launch=true;
    boolean land=true;
    boolean canCarry(int weight);
    boolean carry(String name);
}

class camare {
    public void useShip(SpaceShip ship){
        //ship.canCarry();
        //ship.carry();
    }
}


//Rocket:
class Rocket implements SpaceShip {
    //filed
    int rocketCost;//火箭成本
    int rocketWeight;//火箭重量
    int maxLoadage;//能携带的最高货物重量（包括自重）
    int cargoesWeight;//貨物載重量
    int expRate;//任務爆炸概率
    //int missonExpRate = expRate * (cargoesWeight/maxLoadage);// 執行發射及著陸爆炸概率

    boolean land () {return true;}
    boolean launce() {return true;}

    @Override
    public boolean canCarry(int Item ) {
        // 如果 cargoesWeight > maxLoadage - uniWeight >> return false:


        return true;
    }
    @Override
    public boolean carry(String Item) {
        return false;
    }
}




//負責讀取物品數據並裝載火箭。
class Simulation{
    //String missCode;// mission code : ph1,ph2
        ArrayList phArray = new ArrayList();
        ArrayList rockArray = new ArrayList();

    //構造函數
    public Simulation(){

    }

    //loadItems:讀取運送物品清單,並返回Item ArrayList
    public ArrayList loadItems(String missCode)throws Exception{
            //ArrayList phArray = new ArrayList();
            File file = new File(missCode);
            Scanner scan = new Scanner(file);
            int i = 0;
        while (scan.hasNextLine()){
            String[] txt = (scan.nextLine()).split("=");
            Item temp = new Item("", 0);
            temp.setName(txt[0]);
            temp.setWeight(Integer.parseInt(txt[1]));
            phArray.add(temp);
            i++;
        }
        return phArray;
    }


    //創建U1火箭:
    public ArrayList loadU1(){

     //執行任務(返回任務總成本totalCost)
        //任務裝載
        //任務清單
        //任務火箭數


        return rockArray;
    }



    //創建U2火箭:
    public ArrayList loadU2(Simulation phArray ) {

        ArrayList u2Array = new ArrayList();
        return u2Array;
    }

    //simulation:
    void runSimulation(){//傳入U1ArrayList,U2ArrayList
//for u1.launch{
        //加總成本,
    }
}

class U1 extends Rocket{}

class U2 extends Rocket{}

/*
    while (scan.hasNextLine())throws Exception{
        String [] txt = (scan.nextLine()).split("=");
        Item temp = new Item ("",0);
        temp.setName(txt[0]);
        temp.setWeight(Integer.parseInt(txt[1]));
        phArray.add(temp);
        i++;
    }
*/