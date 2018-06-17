package com.company;
import java.io.File;
import java.util.*;

public class Main {
    /**
     * 创建一个 Simulation 对象
     * 为第一阶段和第二阶段装载 Item
     * 为第一阶段和第二阶段装载 U1 火箭舰队
     * 使用 U1 火箭舰队运行模拟，并显示所需的总预算。
     * 为 U2 火箭重复相同的流程，并显示所需的总预算。
     * @param totalU1 U1,2階段任務總預算
     * @param totalU2 U2,2階段任務總預算
     */
    public static void main(String[] args)throws Exception {
	// 讀入2次任務待運清單
        Simulation mission1 = new Simulation();
        Simulation mission2 = new Simulation();
        mission1.loadItems("phase-1.txt");
        mission2.loadItems("phase-2.txt");
    //計算U1火箭執行2次任務所需預算
        System.out.println("\n計算U1火箭執行任務");
        mission1.loadU1(new U1());
        mission2.loadU1(new U1());
        long totalU1 = mission1.runSimulation(new U1())+mission2.runSimulation(new U1());
    //計算U2火箭執行2次任務所需預算
        System.out.println("\n計算U2火箭執行任務");
        mission1.loadU2(new U2());
        mission2.loadU2(new U2());
        long totalU2 = mission1.runSimulation(new U2())+mission2.runSimulation(new U2());
    //顯示U1，U2所需的總預算
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("U1 執行任務總預算為 $"+totalU1+" 美元。");
        System.out.println("U2 執行任務總預算為 $"+totalU2+" 美元。");
    }
}

/**
 * item類含火箭要携帶的物品及其重量
 * @param weight 要携帶的物品其重量
 * @param name 要携帶的物品名
 */
class Item{
    //field
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

/**
 * SpaceShip 接口
 */
interface SpaceShip {
    boolean launch(int temp);
    boolean land(int temp);
    boolean canCarry(Item item);
    int carry(Item item);
}

/**
 * Rocket類
 * @param cargoesWeight 目前載運物品的總重量
 * @param rocketCurrentWeight 目前火箭的總重量
 * @param limitLoadAge 火箭的參數：限載貨物總重量
 * @param rocketNetWeight 火箭的參數：火箭淨重
 */
class Rocket implements SpaceShip {
    //field
    int cargoesWeight;
    int rocketCurrentWeight;
    int limitLoadAge;
    int rocketNetWeight;
    int maxLoadAge;
    int rocketCost;
    boolean launch;
    boolean land;
    //construct
    public Rocket(){
        land=true;
        launch=true;
        int rocketCost = 0;
    }

    @Override
    public boolean launch(int temp) {
        return true;
    }
    @Override
    public boolean land(int temp){
        return true;
    }

    /**
     * 傳入Item
     * 1 更新火箭目前重量
     * 2 判斷火箭能否搭載此物品
     * @param item
     * @return rocketCurrentWeihgt
     * @return 可否搭載此物品
     */
    @Override
    public int carry(Item item) {
        this.rocketCurrentWeight = this.cargoesWeight + this.rocketNetWeight;
        return rocketCurrentWeight;
    }
    @Override
    public boolean canCarry(Item item) {
        cargoesWeight = cargoesWeight + item.getWeight();
        if( cargoesWeight <= limitLoadAge ){ //物品總種 ＜= 火箭最大載重
            return true;
        }else{
            return false;
        }
    }
}

/**
 * Simulation 类功用：负责读取物品数据并装载火箭。
 * simulation: 依Rocket ArrayList 中每個火箭調用 launch 和 land方法。
 * 每次火箭爆炸或撞毁，它将重新发射该火箭，同时记录安全地向火星发射每个火箭所需的总预算。
 * runSimulation： 返回发射所有火箭所需的总预算（包括撞毁的火箭带来的成本）
 */
class Simulation{
    //field
    ArrayList itemArrayList ;//物品待運清單
    ArrayList rockArray ;//載運上列清單所有物品所需裝載的火箭隊列
    ArrayList weightPerRocket;//火箭隊列每個火箭所載貨品的總重量
    long totalCost ;//完成任務所需的總成本
    //construct
    public Simulation(){
        this.itemArrayList = new ArrayList();
        this.rockArray = new ArrayList();
        this.weightPerRocket = new ArrayList();
        this.totalCost = 0 ;
    }

    /**
     * 讀取運送物品清單,並返回Item ArrayList
     * @param missCode:任務物品清單檔名
     * @return 運送物品的 ArrayList
     * @throws Exception
     */
    public ArrayList loadItems(String missCode)throws Exception{

            File file = new File(missCode);
            Scanner scan = new Scanner(file);
            int i = 0;
        while (scan.hasNextLine()){
            String[] txt = (scan.nextLine()).split("=");
            Item temp = new Item("", 0);
            temp.setName(txt[0]);
            temp.setWeight(Integer.parseInt(txt[1]));
            itemArrayList.add(temp);
            i++;
        }
        return itemArrayList;
    }

    /**
     * 創建U1火箭隊列:(傳出U1發射隊列)load(傳入貨物清單)
     * @param u1Temp
     * @return
     */
    public ArrayList loadU1(Rocket u1Temp){
        ArrayList loadList = new ArrayList();//每個火箭裝載的貨品內容
        int x = itemArrayList.size();//待運件數
        int itemIndex = 0;// 要裝入火箭的物品標號
        while (itemIndex < x) {
            for (itemIndex = 0 ; itemIndex < x ; itemIndex ++) {//裝貨
                Item itemTmp = (Item) itemArrayList.get(itemIndex);//從運送清單itemArrayList取出要裝的貨物itemTmp
                u1Temp.carry(itemTmp);//update the rocketCurrentWeight
                //如果未超過可載運量⋯⋯
                if (u1Temp.canCarry(itemTmp)) {
                    loadList.add(itemTmp);
                    if( itemIndex == x-1 ) {
                        u1Temp.carry(itemTmp);//update the rocketCurrentWeight
                        rockArray.add(loadList);
                        weightPerRocket.add(u1Temp.rocketCurrentWeight-u1Temp.rocketNetWeight);//將每個火箭的載重加入發射序列
                    }//己經裝運最後一個了，此時無論有否載滿均需將火箭加入發射陣列
                } else {
                    itemIndex = itemIndex-1;
                    rockArray.add(loadList);
                    weightPerRocket.add(u1Temp.rocketCurrentWeight-u1Temp.rocketNetWeight);//將每個火箭的載重加入發射序列
                    //這裡結束上一個u1Temp 另建一個 u1Temp
                    U1 u1Temp2 = new U1();
                    u1Temp = u1Temp2;
                    ArrayList loadList2 = new ArrayList();
                    loadList = loadList2;
                }
            }
        }
        return rockArray;
    }

    /**
     * 創建U2火箭隊列:(傳出U2發射隊列)load(傳入貨物清單)
     * @param temp
     * @return
     */
    public ArrayList loadU2(Rocket temp) {
        ArrayList loadList = new ArrayList();//每個火箭裝載的貨品內容
        int x = itemArrayList.size();//待運件數
        int itemIndex = 0;// 要裝入火箭的物品標號
        while (itemIndex < x) {
            for (itemIndex = 0 ; itemIndex < x ; itemIndex ++) {//裝貨
                Item itemTmp = (Item) itemArrayList.get(itemIndex);//從運送清單itemArrayList取出要裝的貨物itemTmp
                temp.carry(itemTmp);//update the rocketCurrentWeight
                //如果未超過可載運量⋯⋯
                if (temp.canCarry(itemTmp)) {
                    loadList.add(itemTmp);
                    if( itemIndex == x-1 ) {
                        temp.carry(itemTmp);//update the rocketCurrentWeight
                        rockArray.add(loadList);
                        weightPerRocket.add(temp.rocketCurrentWeight-temp.rocketNetWeight);//將每個火箭的載重加入發射序列
                    }//己經裝運最後一個了，此時無論有否載滿均需將火箭加入發射陣列
                } else {
                    itemIndex = itemIndex-1;
                    rockArray.add(loadList);
                    weightPerRocket.add(temp.rocketCurrentWeight-temp.rocketNetWeight);//將每個火箭的載重加入發射序列
                    //這裡就要結束上一個u1Temp 另建一個 u1Temp
                    U2 temp2 = new U2();
                    temp = temp2;
                    ArrayList loadList2 = new ArrayList();
                    loadList = loadList2;
                }
            }
        }
        return rockArray;
    }

    /**
     * 執行發射任務並計算運送所有物品所需總預算
     * @param tmp:待發射火箭
     * @return totalCost
     */
    public long runSimulation(Rocket tmp){
        //field
        int no;//火箭的序號,用於螢幕輸出用，執行時取消輸出各火箭裝載狀況，所以未使用到
        int success = 0;//每次完成任務，每趟成功發射且著陸的火箭數
        int failed = 0;//每次完成任務，發射失敗或著陸失敗的火箭數

        while(!weightPerRocket.isEmpty()){//火箭未發射完
            int wt = (int) weightPerRocket.get(0);//將序列裡的載重--＞wt
            no = success+1;
            //System.out.println();
            //System.out.println("\nU-"+no+":發射⋯⋯");
            if(tmp.launch(wt) && tmp.land(wt)) {
                weightPerRocket.remove(0);//成功發射及萶陸的火箭移除隊列
                totalCost += tmp.rocketCost;
                success += 1;
            }else {
                totalCost += tmp.rocketCost;
                failed += 1;
            }
        }
        //執行任務(返回任務總成本totalCost)
        System.out.println("\n總費用＝"+totalCost);
        System.out.println("總共發射_"+(success+failed)+"_次。");
        System.out.println("成功發射_"+success+"_次。");
        return totalCost;
    }
}

/**
 * U1火箭
 * 火箭成本 = 1 亿美元
 * 火箭重量 = 10 公吨
 * 能携带的最高货物重量（包括自重） = 18 公吨
 * 发射时爆炸的概率 = 5% *（携带的货物重量 / 货物重量上限）
 * 着陆时爆炸的概率 = 1% *（携带的货物重量 / 货物重量上限）
 */
class U1 extends Rocket {

    double launchExpRate = 0.05;
    double landExpRate = 0.01;

    public U1() {
        //super();
        rocketCost = 100000000;
        maxLoadAge = 18000;
        rocketNetWeight = 10000;
        rocketCurrentWeight = rocketNetWeight;
        limitLoadAge = maxLoadAge - rocketNetWeight;
    }

    @Override
    public boolean land(int wt) {
        double randomExpRate = Math.random();
        double expRate = landExpRate * wt / limitLoadAge;
        //隨機數字+爆炸概率 >= 80% : 爆炸
        if( expRate + randomExpRate > 0.8 ){
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("著陸失敗");
            return false;
        }else{
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("成功著陸");
            return true;
        }
    }
    public boolean launch (int wt){
        double randomExpRate = Math.random();
        double expRate = launchExpRate * wt / limitLoadAge;
        if( expRate + randomExpRate > 0.8 ){//隨機數字+爆炸概率 >= 80% : 爆炸
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("發射失敗");
            return false;
        }else{
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("成功發射:");
            return true;
        }
    }
}

/**
 * U2火箭
 * 火箭成本 = 1.2 亿美元
 * 火箭重量 = 18 公吨
 * 能携带的最高货物重量（包括自重） = 29 公吨
 * 发射时爆炸的概率 = 4% *（携带的货物重量 / 货物重量上限）
 * 着陆时爆炸的概率 = 8% *（携带的货物重量 / 货物重量上限）
 */
class U2 extends Rocket{

    double launchExpRate = 0.04;
    double landExpRate = 0.08;

    public U2() {
        rocketCost = 120000000;
        maxLoadAge = 29000;
        rocketNetWeight = 18000;
        rocketCurrentWeight = rocketNetWeight;
        limitLoadAge = maxLoadAge - rocketNetWeight;
    }

    @Override
    public boolean land(int wt) {
        double randomExpRate = Math.random();
        double expRate = landExpRate * wt / limitLoadAge;
        //隨機數字+爆炸概率 >= 80% : 爆炸
        if( expRate + randomExpRate > 0.8 ){
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("著陸失敗");
            return false;
        }else{
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("成功著陸");
            return true;
        }
    }
    public boolean launch (int wt){
        double randomExpRate = Math.random();
        double expRate = launchExpRate * wt / limitLoadAge;
        if( expRate + randomExpRate > 0.8 ){//隨機數字+爆炸概率 >= 80% : 爆炸
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("發射失敗");
            return false;
        }else{
            //System.out.println((randomExpRate+xxx)*100);//
            //System.out.print("成功發射:");
            return true;
        }
    }
}
