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
       //ph2.loadU1(I);
       //System.out.println(Math.random());//-(17000/18000*0.05));
       //System.out.println(8000.0/18000.0*0.01*10);

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

    @Override
    public boolean carry(String Name) {

        return true;
    }

    @Override
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

    //創建U1火箭:(傳出U1發射隊列)load(傳入貨物清單)
    //貨物清單總數
    //火箭R=0
    //while(貨物清單isNull){
    //
    //    建立U1R~U1R+;
    //    初始載貨重量=U1重量;
    //    if若載貨總重<=載重量
    //        依序從清單裝貨;
    //        載貨重量=載貨重量+物品重量;
    //        貨物清單-己裝物
    // arraylist 應為U1(1,2,3,4,5) :其中可以帶該火箭成本,
    //功用為,依序按清單入貨,計算該火箭成本
    public ArrayList loadU1(){
        //


        ArrayList loadList = new ArrayList();//每個火箭裝載的貨品內容

        int x = itemArrayList.size();//待運件數
        int itemIndex = 0;// 要裝入火箭的物品標號
        while (itemIndex <= x) {

            U1 u1Temp = new U1();//裝載中的火箭
            System.out.println();
            System.out.println("loading...");


            //
            for (itemIndex = 0; itemIndex <= x; itemIndex++) {//裝貨
                Item itemTmp = (Item) itemArrayList.get(itemIndex);//從運送清單itemArrayList取出要裝的貨物itemTmp

                if (u1Temp.canCarry(itemTmp.getWeight(), u1Temp.maxLoadAge)) {
                    System.out.println("可以裝" + itemTmp.getName());
                    loadList.add(itemTmp);

                } else {
                    System.out.println("裝不下" + itemTmp.getName());

                }
            }
        }













/*
        while(x>0){//x=清單中未送運的物品數,若還有物品未清繼續
           ArrayList loadListTemp = new ArrayList();

           Item itemTmp =(Item)itemArrayList.get(itemIndex);//第二次應為 X
           u1Temp.cargoesWeight= u1Temp.cargoesWeight + itemTmp.getWeight();

           if(u1Temp.cargoesWeight >= u1Temp.maxLoadAge){
               System.out.println("X="+x);
               System.out.println( "Over loading...next ROCKet");
           }else{
               System.out.println( itemTmp.getName()+" "+ itemTmp.getWeight() +" "+u1Temp.cargoesWeight);
               loadListTemp.add(itemTmp);
               x = x-1;//清單中未送運的物品數
               itemIndex += 1;//清單中從第幾個載起
                }

         loadList.add(loadListTemp);//for 149

        }//while 148
        */


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
    int cargoesWeight = 8000;//能携带的最高货物重量（不含自重）
    int rocketWeight = 10000;
    int maxLoadAge = rocketWeight +cargoesWeight;//火箭總重
    double u1LaunchExpRate = 0.05;
    double u1LandExpRate = 0.01;
//    double missonExpRate = expRate * (cargoesWeight/maxLoadAge);// 執行發射及著陸爆炸概率
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

   while(x>0){//x=清單中未送運的物品數,若還有物品未清繼續
           ArrayList loadListTemp = new ArrayList();

           Item itemTmp =(Item)itemArrayList.get(itemIndex);//第二次應為 X
           u1Temp.cargoesWeight= u1Temp.cargoesWeight + itemTmp.getWeight();

           if(u1Temp.cargoesWeight >= u1Temp.maxLoadage){
               System.out.println("X="+x);
               System.out.println( "Over loading...next ROCKet");
           }else{
               System.out.println( itemTmp.getName()+" "+ itemTmp.getWeight() +" "+u1Temp.cargoesWeight);
               loadListTemp.add(itemTmp);
               x = x-1;//清單中未送運的物品數
               itemIndex += 1;//清單中從第幾個載起
                }

         loadList.add(loadListTemp);//for 149

        }
 */

