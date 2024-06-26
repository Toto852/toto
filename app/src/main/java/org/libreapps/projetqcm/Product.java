package org.libreapps.projetqcm;

import org.json.JSONObject;

public class Product {
    private final int id;
    private final String name;
    private final String type;
    private final double price;

    public Product(JSONObject jObject) {
        this.id =jObject.optInt("id");
        this.name = jObject.optString("name");
        this.type = jObject.optString("type");
        this.price = jObject.optDouble("price");
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getPrice() { return price; }
}