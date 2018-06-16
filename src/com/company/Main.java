package com.company;
import java.awt.*;
import java.io.File;
import java.util.*;
//import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)throws Exception {
	// write your code here

       //ph1.loadItems("phase-1.txt");
       Simulation ph1 = new Simulation();
       ph1.loadItems("phase-1.txt");
       ph1.loadU1(new U1());
       ph1.runSimulation();
    }
}

//OK------------------Item類含火箭要携帶的物品及其重量
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

//OK------------------SpaceShip
interface SpaceShip {
    boolean launch(int temp);
    boolean land(int temp);
    boolean canCarry(Item item);
    int carry(Item item);
}

//Rocket:
class Rocket implements SpaceShip {
    //filed
    int cargoesWeight;
    int rocketCurrentWeight;
    int limitLoadAge;
    int rocketNetWeight;
    int maxLoadAge;
    int rocketCost;

    boolean launch;
    boolean land;

    //const
    public Rocket(){
        land=true;
        launch=true;
    }

    @Override
    public boolean launch(int temp) {
        return true;
    }
    @Override
    public boolean land(int temp){
        return true;
    }
    @Override//傳入Item.getWeight 更新火箭目前重量
    public int carry(Item item) {//物品重量
        this.rocketCurrentWeight = this.cargoesWeight + this.rocketNetWeight;
        return rocketCurrentWeight;
    }
    @Override//回傳火箭能否搭載此物品
    public boolean canCarry(Item item) {
        cargoesWeight = cargoesWeight + item.getWeight();
        if( cargoesWeight <= limitLoadAge ){//物品總種 ＜= 火箭最大載重
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
        ArrayList weightPerRocket;//火箭隊列每個火箭重量參數 携带的货物重量 / 货物重量上限
        long totalCost ;
        // +++ String rocketUn; //使用火箭類型

    //構造函數
    public Simulation(){
        this.itemArrayList = new ArrayList();
        this.rockArray = new ArrayList();
        this.weightPerRocket = new ArrayList();
        this.totalCost = 0 ;
        // +++ this.rocketUn = getUn();
    }

    //設定使用火箭
    // +++ public void getUn(String){return ; }
    //執行任務(返回任務總成本totalCost)

    //OK------------------oadItems:讀取運送物品清單,並返回Item ArrayList
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

    //OK------------------創建U1火箭隊列:(傳出U1發射隊列)load(傳入貨物清單)
    public ArrayList loadU1(U1 u1Temp){
        //火箭成本 = 1 亿美元
        //火箭重量 = 10 公吨
        //能携带的最高货物重量（包括自重） = 18 公吨

        ArrayList loadList = new ArrayList();//每個火箭裝載的貨品內容

        int x = itemArrayList.size();//待運件數
        int itemIndex = 0;// 要裝入火箭的物品標號
        while (itemIndex < x) {

            System.out.println();
            System.out.println("U1_"+ rockArray.size()+"裝運...");

            for (itemIndex = 0 ; itemIndex < x ; itemIndex ++) {//裝貨
                Item itemTmp = (Item) itemArrayList.get(itemIndex);//從運送清單itemArrayList取出要裝的貨物itemTmp
                u1Temp.carry(itemTmp);//update the rocketCurrentWeight
                //如果未超過可載運量⋯⋯
                if (u1Temp.canCarry(itemTmp)) {
                    loadList.add(itemTmp);
                    System.out.println(" "+itemTmp.getName());
                    if( itemIndex == x-1 ) {
                        u1Temp.carry(itemTmp);//update the rocketCurrentWeight
                        rockArray.add(loadList);
                        weightPerRocket.add(u1Temp.rocketCurrentWeight-u1Temp.rocketNetWeight);//將每個火箭的載重加入發射序列
                    }//己經裝運最後一個了，此時無論有否載滿均需將火箭加入發射陣列
                } else {
                    itemIndex = itemIndex-1;
                    rockArray.add(loadList);
                    // print current weight
                    System.out.println(u1Temp.rocketCurrentWeight);
                    weightPerRocket.add(u1Temp.rocketCurrentWeight-u1Temp.rocketNetWeight);//將每個火箭的載重加入發射序列
                    System.out.println("\n"+"U1-"+ rockArray.size()+":"+"裝運...");
                    //這裡就要結束上一個u1Temp 另建一個 u1Temp
                    U1 u1Temp2 = new U1();
                    u1Temp = u1Temp2;
                    ArrayList loadList2 = new ArrayList();
                    loadList = loadList2;
                }
            }
        }
        System.out.println(u1Temp.rocketCurrentWeight);
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
    public long runSimulation() {//待發射火箭的序列,火箭類別
        //rocket.
        int i = weightPerRocket.size();//待發射火箭總數
        int xn = 0 ;//發射序號
        int y = 0 ;//最後一次發射序號
        int success = 0;
        int failed = 0;

        while( xn < i){//火箭未發射完
            U1 u1 = new U1();
            int wt = (int) weightPerRocket.get(xn);
            System.out.println();
            if(u1.launch(wt) && u1.land(wt)) {
                y = xn;
                success += 1;
                totalCost += u1.rocketCost;
                xn += 1;
                if(xn == i){

                }
            }else {
                xn = y;
                totalCost += u1.rocketCost;
                failed += 1;

            }
           // u1.land(wt);
        }
        System.out.println("\n總費用＝"+totalCost);
        System.out.println("總共發射_"+(success+failed)+"_次。");
        System.out.println("成功發射_"+success+"_次。");
        return totalCost;
    }
}

/*
(傳出是否爆炸)launch(傳入總貨重)
    隨機計算是否爆炸//執行任務(返回任務總成本totalCost)
    將T/F 覆蓋存入 ROCKET
*/

//創建U1
class U1 extends Rocket {
    //火箭成本 = 1 亿美元
    //火箭重量 = 10 公吨
    //能携带的最高货物重量（包括自重） = 18 公吨

    public U1() {
        //super();
        rocketCost = 100000000;
        maxLoadAge = 18000;
        rocketNetWeight = 10000;
        rocketCurrentWeight = rocketNetWeight;
        limitLoadAge = maxLoadAge - rocketNetWeight;
    }
    double launchExpRate = 0.05;
    double landExpRate = 0.01;

//着陆时爆炸的概率 = 1% *（携带的货物重量 / 货物重量上限
    @Override
    public boolean land(int wt) {
        double randomExpRate = Math.random();
        double xxx = landExpRate * wt / limitLoadAge;
        if( xxx + randomExpRate > 0.8 ){
            //隨機數字+爆炸概率 >= 50% : 爆炸
            System.out.println((randomExpRate+xxx)*100);
            System.out.println("著陸失敗");
            return false;
        }else{
            System.out.println((randomExpRate+xxx)*100);
            System.out.println("成功著陸");
            return true;
        }
    }
    //发射时爆炸的概率 = 5% *（携带的货物重量 / 货物重量上限）
    public boolean launch (int wt){
        double randomExpRate = Math.random();
        double xxx = launchExpRate * wt / limitLoadAge;
        if( xxx + randomExpRate > 0.8 ){
            //隨機數字+爆炸概率 >= 50% : 爆炸
            System.out.println((randomExpRate+xxx)*100);
            System.out.println("發射失敗");
            return false;
        }else{
            System.out.println((randomExpRate+xxx)*100);
            System.out.println("成功發射");
            return true;
        }
    }
}

//創建U2
class U2 extends U1{}

