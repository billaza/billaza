package com.semester4.uasmcsnabilla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class WordDefinitions {

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("definition")
    @Expose
    private String definition;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}