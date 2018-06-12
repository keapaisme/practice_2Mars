package com.company;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)throws Exception {
	// write your code here

       //ph1.loadItems("phase-1.txt");
       Simulation ph2 = new Simulation();
       ph2.loadItems("phase-2.txt");
       //ph2.loadU1(I);
       //System.out.println(Math.random());//-(17000/18000*0.05));
       //System.out.println(8000.0/18000.0*0.01*10);

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
    boolean launch();
    boolean land();
    boolean canCarry();
    boolean carry();
}

//Rocket:
class Rocket implements SpaceShip {
    //filed
    int rocketCost;//火箭成本
    int rocketWeight;//火箭重量
    double maxLoadage;//能携带的最高货物重量（不含自重）
    double cargoesWeight;//貨物載重量
    double expRate;//任務爆炸概率
    double missonExpRate = expRate * (cargoesWeight/maxLoadage);// 執行發射及著陸爆炸概率
    boolean land = true;
    boolean launch = true;

    @Override
    public boolean launch() {
        return false;
    }

    @Override
    public boolean land() {
        return false;
    }

    @Override
    public boolean carry() {

        return true;
    }

    @Override
    public boolean canCarry() {
        // 如果 cargoesWeight > maxLoadage - uniWeight >> return false:
        return true;
    }

}


//負責讀取物品數據並裝載火箭。
class Simulation{
    //String missCode;// mission code : ph1,ph2
        ArrayList itemArrayList = new ArrayList();
        ArrayList rockArray = new ArrayList();

    //構造函數
    public Simulation(){
        itemArrayList=itemArrayList;
        rockArray=rockArray;
    }

    //loadItems:讀取運送物品清單,並返回Item ArrayList
    public ArrayList loadItems(String missCode)throws Exception{

            File file = new File(missCode);
            Scanner scan = new Scanner(file);
            int i = 0;
        while (scan.hasNextLine()){
            String[] txt = (scan.nextLine()).split("=");
            System.out.println(txt[0]+txt[1]);// XXX
            Item temp = new Item("", 0);
            temp.setName(txt[0]);
            temp.setWeight(Integer.parseInt(txt[1]));
            itemArrayList.add(temp);

            i++;
        }
        itemArrayList.get(0);
        return itemArrayList;
    }

    //創建U1火箭:
    public ArrayList loadU1(Item item){
       ArrayList loadList = new ArrayList();//裝載清單

       for (int i = 0; i<=itemArrayList.size();i++){
          // Item tmp = new Item(itemArrayList.get(0));

           //u01.canCarry(itemArrayList.get(i));

       }
     //執行任務(返回任務總成本totalCost)
        //任務裝載
        //任務清單
        //任務火箭數


        return rockArray;
    }



    //創建U2火箭:
    public ArrayList loadU2() {


        return itemArrayList;//改
    }

    //simulation:
    void runSimulation(){//傳入U1ArrayList,U2ArrayList
//for u1.launch{
        //加總成本,
    }
}

/*
(傳出是否爆炸)launch(傳入總貨重)
    隨機計算是否爆炸
    將T/F 覆蓋存入 ROCKET
 */
class U1 extends Rocket {
    double u1LaunchExpRate = 0.05;
    double u1LandExpRate = 0.01;

    boolean launchExp (int totalWeight ){
        if((0.05 *((float) cargoesWeight/(float) maxLoadage) >= Math.random())){
            //隨機數字小於 爆炸概率 ＝ true
            return false;
        }else{
            return true;
        }
    }

    //       发射时爆炸的概率 = 5% *（携带的货物重量 / 货物重量上限）
     //着陆时爆炸的概率 = 1% *（携带的货物重量 / 货物重量上限
}

class U2 extends Rocket{}

/*================================
 ArrayList <U1> loadU1 (ArrayList <Item> items) {
        ArrayList <U1> fleetU1 = new ArrayList<>();      // create ArrayList object fleetU1


java.lang.ClassCastException >> if( a instanceof b)


==Main
Master kas = new Master();
Cat tom = new Cat();
kas.feed(tom);

=Master
public void feed(Cat c){
 c.eat();
 }

=Cat
public void Eat(){
 print("貓在吃魚"）；
 }
 */