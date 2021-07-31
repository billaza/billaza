package com.semester4.uasmcsnabilla;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// NABILLA DRIESANDIA AZARINE
// 2301846383

public class WordResult {

    @SerializedName("word")
    @Expose
    private String word;

    @SerializedName("definitions")
    @Expose
    private List<WordDefinitions> definitions = null;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<WordDefinitions> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<WordDefinitions> definitions) {
        this.definitions = definitions;
    }
}


