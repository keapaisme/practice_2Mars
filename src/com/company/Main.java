package com.company;

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
        mission1.loadRocket(new U1());
        mission2.loadRocket(new U1());
        long totalU1 = mission1.runSimulation(new U1())+mission2.runSimulation(new U1());
    //計算U2火箭執行2次任務所需預算
        System.out.println("\n計算U2火箭執行任務");
        mission1.loadRocket(new U2());
        mission2.loadRocket(new U2());
        long totalU2 = mission1.runSimulation(new U2())+mission2.runSimulation(new U2());

    //顯示U1，U2所需的總預算
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("U1 執行任務總預算為 $"+totalU1+" 美元。");
        System.out.println("U2 執行任務總預算為 $"+totalU2+" 美元。");
    }
}


