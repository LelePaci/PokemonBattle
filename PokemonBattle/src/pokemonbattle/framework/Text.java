/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemonbattle.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pacie
 */
public class Text extends GameObject {

    public String text;
    public Font font = null;
    public Color color;
    public List<Text> allTexts = new ArrayList<>();

    public Text() {
        super(0, 0);
    }

    public Text(String text, float x, float y, Font font, Color color) {
        super(x, y);
        this.text = text;
        this.font = font;
        this.color = color;
    }

    public void setData(Text... data) {
        allTexts.clear();
        allTexts.addAll(Arrays.asList(data));
    }
    public void addData(Text data){
        allTexts.add(data);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < allTexts.size(); i++) {
            Text temp = allTexts.get(i);
            g.setColor(temp.color);
            g.setFont(temp.font);
            g.drawString(temp.text, (int) temp.x, (int) temp.y);
        }
    }
}
