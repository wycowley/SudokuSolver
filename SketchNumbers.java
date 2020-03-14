

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class SketchNumbers{
    private int row;
    private int column;
    private int pos;
    private int[] nums;
    private int[] dontAdd = new int[9];
    private Font small = new Font ("Sans-Serif", Font.PLAIN, 20);
    private Color darkGrey = new Color(30,30,30);
    private Color darkRed = new Color(192,6,6);
    public SketchNumbers(int row, int column, int pos){
        this.row = row;
        this.column = column;
        this.pos = pos;
        nums = new int[9];
        for(int i = 0;i<nums.length;i++){
            nums[i] = 0;
        }
    }
    public void drawMe(Graphics g,int[] values, int selected){
        g.setFont(small);
        if(values[pos] == 0){
            for(int i = 0;i<nums.length;i++){
                if(nums[i] != 0){
                    if(values[selected]==nums[i])
                        g.setColor(darkRed);
                    else
                        g.setColor(darkGrey);

                    g.drawString(nums[i]+"",(int)(column*66.6+30+i%3*20),(int)(row*66.6+45+(int)(i/3)*20));
                }
            }
        }
    }
    public void addNumber(int num){
        boolean go = true;
        for(int i = 0;i<dontAdd.length;i++){
            if(dontAdd[i]==num){
                go = false;
            }
        }
        if(go){
            nums[num-1] = num;
        }
    }
    public int[] getNumber(){
        return nums;
    }
    public void removeNum(int num){
        nums[num-1] = 0;
        dontAdd[num-1] = num;
    }
    public void deleteAll(){
        for(int i = 0;i<nums.length;i++){
            nums[i] = 0;
        }
    }
    public boolean numberIn(int num){
        for(int each: nums){
            if(each==num){
                return true;
            }
        }
        return false;
    }
    public int length(){
        return nums.length;
    }
    public void clearDonts(){
        for(int i = 0;i<dontAdd.length;i++){
            dontAdd[i]=0;
        }
    }
}