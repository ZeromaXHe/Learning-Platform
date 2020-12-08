package com.zerox.entity;

import java.util.Objects;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:23
 * @Description:
 * @Modified By: zhuxiaohe
 */
public class Syllable {
    /**
     * 音节对应字形
     */
    private String character;
    /**
     * 音节
     */
    private String syllable;

    public Syllable() {
    }

    public Syllable(String character, String syllable) {
        this.character = character;
        this.syllable = syllable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Syllable syllable1 = (Syllable) o;
        return Objects.equals(character, syllable1.character) &&
                Objects.equals(syllable, syllable1.syllable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, syllable);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getSyllable() {
        return syllable;
    }

    public void setSyllable(String syllable) {
        this.syllable = syllable;
    }

    @Override
    public String toString() {
        return "Syllable{" +
                "character='" + character + '\'' +
                ", syllable='" + syllable + '\'' +
                '}';
    }
}
