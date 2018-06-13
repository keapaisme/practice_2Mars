package com.company;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)throws Exception {
	// write your code here

       //ph1.loadItems("phase-1.txt");
       Simulation ph1 = new Simulation();
       ph1.loadItems("phase-1.txt");
       ph1.loadU1();
    }
}


//Item類含火箭要携帶的物品及其重量
class Item{
    int weight ;
    String name;

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
    boolean canCarry(int x,int y);
    boolean carry(String name);
}

//Rocket:
class Rocket implements SpaceShip {
    //filed
    int rocketCost;//火箭成本
    int rocketWeight;//火箭重量
    int cargoesWeight;//貨物載重量
    int maxLoadAge ;//火箭總重
    double expRate;//任務爆炸概率

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

    @Override//更新火箭目前重量
    public boolean carry(String Name) {
        return true;
    }

    @Override//回傳火箭能否搭載此物品
    public boolean canCarry(int getWeight,int maxLoadAge) {
        cargoesWeight = cargoesWeight + getWeight;

        if( cargoesWeight < maxLoadAge ){//物品總種 ＜ 火箭最大載重
            return true;
        }else{
            return false;
        }
    }
}

//負責讀取物品數據並裝載火箭。
class Simulation{
    //String missCode;// mission code : ph1,ph2
        ArrayList itemArrayList ;
        ArrayList rockArray ;
        int totalCost ;
        // +++ String rocketUn; //使用火箭類型

    //構造函數
    public Simulation(){
        this.itemArrayList = new ArrayList();
        this.rockArray = new ArrayList();
        this.totalCost = 0 ;
        // +++ this.rocketUn = getUn();
    }

    //設定使用火箭
    // +++ public void getUn(String){return ; }

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

    //創建U1火箭:(傳出U1發射隊列)load(傳入貨物清單)

    public ArrayList loadU1(){
        //
        ArrayList loadList = new ArrayList();//每個火箭裝載的貨品內容

        int x = itemArrayList.size();//待運件數
        int itemIndex = 0;// 要裝入火箭的物品標號
        while (itemIndex < x) {

            U1 u1Temp = new U1();//裝載中的火箭
            System.out.println();
            System.out.println("loading...");

            for (itemIndex = 0 ; itemIndex < x ; itemIndex++) {//裝貨
                Item itemTmp = (Item) itemArrayList.get(itemIndex);//從運送清單itemArrayList取出要裝的貨物itemTmp

                if (u1Temp.canCarry(itemTmp.getWeight(), u1Temp.maxLoadAge)) {
                    System.out.println("可以裝" + itemTmp.getName());
                    loadList.add(itemTmp);
                    //System.out.println(loadList.indexOf(itemIndex));//會報-1？
                    if(itemIndex == itemArrayList.size() ){rockArray.add(loadList);}
                    System.out.print("---"+rockArray.size()+"---");
                } else {
                    itemIndex = itemIndex-1;
                    rockArray.add(loadList);
                    System.out.println("裝不下" + itemTmp.getName());
                    //這裡就要結束上一個u1Temp 另建一個 u1Temp
                    U1 u1Temp2 = new U1();
                    u1Temp = u1Temp2;
                    ArrayList loadlist2 = new ArrayList();
                    loadList = loadlist2;
                }
            }
        }
        //執行任務(返回任務總成本totalCost)
        return rockArray;
    }

    //創建U2火箭:
    public ArrayList loadU2() {


        return itemArrayList;//改
    }

    /**
    *simulation: 依Rocket ArrayList 中每個火箭調用 launch 和 land
    *方法。每次火箭爆炸或撞毁，它将重新发射该火箭，同时记录安全地向
    *火星发射每个火箭所需的总预算。然后，runSimulation 返回发射所有火箭
    *所需的总预算（包括撞毁的火箭带来的成本）
    */
    public int runSimulation (ArrayList rockArray , String rocketUn) {
        ArrayList runList = rockArray; //runList 待發射火箭的序列
        int i = runList.size();//待發射火箭總數
        int x = 1 ;//發射序號

        while( !runList.isEmpty() ){//火箭未發射完
            switch (rocketUn){
                case "U1":
                    U1 temp = new U1();
                    temp = (U1) runList.get(x-1);
                    temp.launch();


                    break;
                case "U2":
                    System.out.print("2");break;
                default:
            }
        }
        return 500;
    }
}

/*
(傳出是否爆炸)launch(傳入總貨重)
    隨機計算是否爆炸//執行任務(返回任務總成本totalCost)
    將T/F 覆蓋存入 ROCKET
 */
class U1 extends Rocket {
    int cargoesWeight = 8000;//携带货物的重量（不含自重）
    int rocketWeight = 10000;
    int maxLoadAge = cargoesWeight;//火箭載重限制
    double u1LaunchExpRate = 0.05;
    double u1LandExpRate = 0.01;
    double missonExpRate = expRate * (cargoesWeight/maxLoadAge);// 執行發射及著陸爆炸概率
    boolean launchExp (int totalWeight ){
        if((0.05 *((float) cargoesWeight/(float) maxLoadAge) >= Math.random())){
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

