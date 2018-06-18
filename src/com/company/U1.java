package com.company;

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
        return expRate + randomExpRate > 0.8;
    }
    public boolean launch (int wt){
        double randomExpRate = Math.random();
        double expRate = launchExpRate * wt / limitLoadAge;
        return expRate + randomExpRate > 0.8;
    }

}
