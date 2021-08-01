package com.e.cocinfo.townhall

class ThStorage {
    private var level = ""
    private var gold = ""
    private var elixir = ""
    private var dark = ""

    fun getLevel(): String {
        return level
    }

    fun setLevel(level: String) {
        this.level = level
    }

    fun getGold(): String {
        return gold
    }

    fun setGold(gold: String) {
        this.gold = gold
    }

    fun getElixir(): String {
        return elixir
    }

    fun setElixir(elixir: String) {
        this.elixir = elixir
    }

    fun getDark(): String {
        return dark
    }

    fun setDark(dark: String) {
        this.dark = dark
    }
}