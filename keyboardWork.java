

public class keyboardWork{
    private int firstNum = 0;
    private int secondNum = 0;

    //After a click, finds the correct position in the grid
    public int findSelected(int x, int y, int alreadySelected){
        if(x>625 || x<25 || y>625 || y<25){
            return alreadySelected;
        }
        for(int i = 0;i<9;i++){
            if(i*66.6+25<x && (i+1)*66.6+25>x){
                firstNum = i;
            }
            if(i*66.6+25<y && (i+1)*66.6+25>y){
                secondNum = i;
            }
        }
        return (firstNum+secondNum*9);
    }
    public int getFirstNum(){
        return firstNum;
    }
    public int getSecondNum(){
        return secondNum;
    }
}