package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
       Simulation ph1 = new Simulation();
       ph1.runSimulation();
    }
}

class Item{
    int weight;
    String name;

    public Item(){
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

interface SpaceShip {
    boolean launch=true;
    boolean land=true;
    boolean canCarry(int weight);
    boolean carry(String name);
}

class Rocket implements SpaceShip{
    @Override
    public boolean canCarry(int weight) {
        return false;
    }

    @Override
    public boolean carry(String name) {
        return false;
    }
}

class Simulation {
    public void loadItems(Item item){
        item.setName("food");
        item.setWeight(5000);
    }

    public void loadU1(){}
    public void loadU2(){}
    public void runSimulation(){}
}

class U1 extends Rocket{}

class U2 extends Rocket{}
