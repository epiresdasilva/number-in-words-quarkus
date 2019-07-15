package br.com.evandropires.numberinwords.function;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Response {

    private String inWords;

    public String getInWords() {
        return inWords;
    }

    public void setInWords(String inWords) {
        this.inWords = inWords;
    }
}
