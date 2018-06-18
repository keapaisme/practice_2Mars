package com.company;

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
        return expRate+randomExpRate > 0.8;
    }
    public boolean launch (int wt){
        double randomExpRate = Math.random();
        double expRate = launchExpRate * wt / limitLoadAge;
        //隨機數字+爆炸概率 >= 80% : 爆炸
        return expRate+randomExpRate > 0.8;
    }
}
