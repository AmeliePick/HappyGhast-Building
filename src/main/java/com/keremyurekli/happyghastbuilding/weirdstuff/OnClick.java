package com.keremyurekli.happyghastbuilding.weirdstuff;

public enum OnClick {

    CRAFTING_TABLE("CRAFTING_TABLE"),
    CHEST("CHEST"),
    FURNACE("FURNACE"),
    BED("BED"),

    //3 dispensers because why the fuck not?
    DISPENSER1("DISPENSER1"),
    DISPENSER2("DISPENSER2"),
    DISPENSER3("DISPENSER3"),
    DISPENSER4("DISPENSER4"),
    DI1("DI1"),
    DI2("DI2"),
    DI3("DI3"),
    DI4("DI4");




    public final String label;
    private OnClick(String label) {
        this.label = label;
    }
}
