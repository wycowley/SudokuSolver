

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Screen extends JPanel implements KeyListener,MouseListener{
    private Boolean solve = false;
    private int change = 10;
    private AddInUsingNotes aiun = new AddInUsingNotes();
    private int numGuessed;
    private int posGuessed;
    //simply 0 through 81, maybe not necessary??
    private int[] positions = new int[81];
    //this one is what the person has already entered
    private int[] values = new int[81];

    private Font big = new Font ("Sans-Serif", Font.PLAIN, 30);

    //which box is selected info
    private int selected;
    private int row;
    private int column;

    //if sketch mode is on
    private boolean sketch;

    //instantiation for adding in notes
    private NoteNumbers nn = new NoteNumbers();

    //instantiation for guessing;
    Guess guess = new Guess();

    //instantiation for keyboard
    private keyboardWork kb = new keyboardWork();

    //instantiation for deleting with straight rows
    private DeletionRows dr = new DeletionRows();

    //instantiation for userInputs
    private userInputs uI = new userInputs();

    //instantiation for notes
    private SketchNumbers[] sn = new SketchNumbers[81];

    //instantiation for checking
    private CheckIfCorrect cic = new CheckIfCorrect();

    //beautiful colors
    private Color darkgrey = new Color(50,50,50);
    private Color black = new Color(0,0,0);
    private Color lightBlue = new Color(230,230,230);
    private Color selectedColor = new Color(220,220,220);
    private Color red = new Color(171, 46, 48);
    private Color green = new Color(46, 171, 54);
    private Color white = new Color(255,255,255);

    private void set(int pos, int num){
        values[pos] = num;
        positions[pos] = 1;
    }

    public Screen(){
        for(int i = 0;i<values.length;i++){
            values[i] = 0;
        }
        for(int i = 0;i<sn.length;i++){
            int row = (int)(i/9);
            int column = i-row*9;
            sn[i] = new SketchNumbers(row,column,i);
        }
        set(1,2); set(3,4); set(7,1); set(9,5); set(15,6); set(19,7); set(20,4); set(21,6); set(24,9); set(28,4); set(30,5); set(32,2); set(36,9); set(40,4); set(44,3); set(48,9); set(50,3); set(52,7); set(56,1); set(59,7); set(60,5); set(61,6); set(65,8); set(71,1); set(73,3); set(77,6); set(79,4); 
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
    }
    public Dimension getPreferredSize(){
        return new Dimension(650,700);
    }
    public void paintComponent(Graphics g){
        g.setColor(white);
        g.fillRect(0,0,650,700);
        //Sets the correct rows and columns for highlighting
        column = kb.getFirstNum();
        row = kb.getSecondNum();

        //Highlights the correct sections
        g.setColor(lightBlue);
        g.fillRect((int)(column*66.6+25),25,67,600);
        g.fillRect(25,(int)(row*66.6+25),600,67);

        g.setColor(selectedColor);
        g.fillRect((int)(column*66.6+25),(int)(row*66.6+25),67,67);


        //Draws the grid
        g.setColor(darkgrey);
        for(int i = 0;i<9;i++){
            g.drawLine(25,(int)(25+i*66.6),625,(int)(25+i*66.6));
            g.drawLine((int)(25+i*66.6),25,(int)(25+i*66.6),625);
        }
        g.drawLine(25,625,625,625);
        g.drawLine(625,25,625,625);

        g.setColor(black);
        for(int i = 1;i<3;i++){
            g.drawLine(25,(int)(25+i*200),625,(int)(25+i*200));
            g.drawLine((int)(25+i*200),25,(int)(25+i*200),625);
        }

        
        //draws all the text entered in
        uI.drawMe(g,values,positions, selected);


        g.setColor(lightBlue);
        g.fillRect(240,640,200,50);

        g.setFont(big);
        g.setColor(black);
        g.drawString("Solve:", 260, 675);
        if(solve){
            g.setColor(green);
            g.drawString("On",365,675);
        }else{
            g.setColor(red);
            g.drawString("Off",365,675);
        }
        g.setColor(black);
        g.drawString("Clear All",50,675);
        for(int i = 0;i<sn.length;i++){
            sn[i].drawMe(g,values,selected);
        }

        
        
        if(solve){
            sn = nn.addThem(values,sn);

            values = aiun.addIn(values, sn,numGuessed,positions);
            positions = aiun.getPosition();
    
            sn = dr.deleteSome(values, sn);
    
            change--;
    
            if(aiun.getGuess() && numGuessed == 0){
                // sn = nn.addThem(values,sn);
                // aiun.setGuess();
                values = guess.guessSomething(values,positions,sn);
                positions = guess.getPositions();
                posGuessed = guess.positionGuessed();
                numGuessed = guess.numberGuessed();
    
                // values = aiun.addIn(values, sn,numGuessed,positions);
                // positions = aiun.getPosition();
                // change = 10;
                
    
                
            }else if(numGuessed>0 && aiun.getGuess()&&change<7){
                aiun.setGuess();
    
                for(int i = 0;i<positions.length;i++){
                    if(positions[i]==3 || positions[i]==10){
                        values[i]=0;
                        positions[i]=0;
                    }
                }
    
                for(SketchNumbers each: sn){
                    each.clearDonts();
                    each.deleteAll();
                }
                sn = nn.addThem(values,sn);
                sn = dr.deleteSome(values, sn);
    
                
                sn = nn.addThem(values,sn);
                values = guess.guessSomething(values,positions,sn);
                positions = guess.getPositions();
                posGuessed = guess.positionGuessed();
                numGuessed = guess.numberGuessed();
                change = 10;
    
                
                
    
            }
        }
        

        if(cic.check(values)){
            // g.setColor(lightBlue);
            // g.fillRect(,0,800,900);
            g.setColor(black);
            g.drawString("Solved!",275,300);
            numGuessed = -1;
        }


    }

    public void mousePressed(MouseEvent e) {

        if(e.getX()>240&&e.getX()<440&&e.getY()>640&&e.getY()<690){
            if(solve){
                solve = false;
                numGuessed = 0;
                for(SketchNumbers each: sn){
                    each.clearDonts();
                    each.deleteAll();
                }
                for(int i = 0;i<values.length;i++){
                    if(positions[i]!=1){
                        values[i]=0;
                    }
                }
            }
            else
                solve = true;
        }
        if(e.getX()>25&&e.getX()<125&&e.getY()>640&&e.getY()<690){
            for(int i = 0;i<values.length;i++){
                values[i] = 0;
            }
            
        }

        selected = kb.findSelected(e.getX(),e.getY(),selected);
        repaint();
    }
    public void keyPressed(KeyEvent e){
        //for numbers, ascii is 48 above
        if(e.getKeyCode()-48>0 && e.getKeyCode()-48<10 && values[selected]<=9 && !solve){
            values[selected] = e.getKeyCode()-48;
            positions[selected] = 1;
        }
        if(e.getKeyCode() == 8){
            values[selected] = 0;
            
        }
        
        if(e.getKeyCode()==32){
            if(solve){
                solve = false;
                numGuessed = 0;
                for(SketchNumbers each: sn){
                    each.clearDonts();
                    each.deleteAll();
                }
                for(int i = 0;i<values.length;i++){
                    if(positions[i]!=1){
                        values[i]=0;
                    }
                }
            }
            else
                solve = true;
        }


        repaint();

    }

    //Not used but needed
    public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void animate(){
        while(true){
            //wait for .01 second
            try {
                Thread.sleep(5);
            }catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            //repaint the graphics drawn
            repaint();
        }
    }


}