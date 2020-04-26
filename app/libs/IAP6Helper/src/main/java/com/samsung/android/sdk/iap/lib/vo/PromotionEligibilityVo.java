package com.samsung.android.sdk.iap.lib.vo;

import org.json.JSONObject;

public class PromotionEligibilityVo {
    private static final String TAG = PromotionEligibilityVo.class.getSimpleName();

    private String itemId;
    private String pricing;

    private String jsonString;

    public PromotionEligibilityVo(JSONObject _jsonObject) {
        setJsonString(_jsonObject.toString());

        setItemId(_jsonObject.optString("itemID"));
        setPricing(_jsonObject.optString("pricing"));
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String dump() {
        String dump =
                "itemID                              : " + getItemId() + "\n" +
                        "pricing                             : " + getPricing();

        return dump;
    }
}