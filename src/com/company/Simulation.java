package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
     * 創建火箭隊列:(傳出發射隊列)load(傳入貨物清單)
     * @param temp
     * @return
     */
    public ArrayList loadRocket(Rocket temp) {
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
        totalCost = 0;

        while(!weightPerRocket.isEmpty()){//火箭未發射完
            int wt = (int) weightPerRocket.get(0);//將序列裡的載重--＞wt
            no = success+1;
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
