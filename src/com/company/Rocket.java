package com.company;

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
        return cargoesWeight <= limitLoadAge; // 老師指導可以這樣寫！的確簡潔太多
    }
}
