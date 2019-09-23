package com.newproject.doencasoperacionais;

import java.util.ArrayList;

/**
 * Created by Lopes on 06/05/2018.
 */

public class Object extends ArrayList<Object> {
    private String doenca;
    private String descricao;
    private String tratamento;
    private String sintomas;
    private String funcao;

    public Object(String Doe, String Des, String Trat, String Sint, String Func){
        this.doenca         = Doe;
        this.descricao      = Des;
        this.tratamento     = Trat;
        this.sintomas       = Sint;

        this.funcao         = Func;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
