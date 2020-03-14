import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

public class userInputs{
    private Font big = new Font ("Sans-Serif", Font.PLAIN, 45);
    private Color black = new Color(0,0,0);
    private Color deepBlue = new Color(14,19,136);
    private Color darkRed = new Color(138, 30, 22);
    private Color deepGreen = new Color(50,150,80);
    private Color deepYellow = new Color(255,20,180);

    public void drawMe(Graphics g, int[] v, int[] p, int selected){
        //takes index and changes it to rows and columns
        g.setFont(big);
        for(int i = 0;i<v.length;i++){
            int row = (int)(i/9);
            int column = i-row*9;
            if(v[i]!=0){
                String value = v[i]+"";
                if(p[i]==1)
                    g.setColor(black);
                else if(p[i]==0)
                    g.setColor(deepBlue);
                else if(p[i]==3)
                    g.setColor(deepGreen);
                else{
                    g.setColor(deepYellow);
                }
                if(v[i]==v[selected]){
                    g.setColor(darkRed);
                }
                g.drawString(value.charAt(0)+"", (int)(column*66.6+45),(int)(row*66.6+78));
            }
        }
    }
}