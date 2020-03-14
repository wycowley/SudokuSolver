public class Guess{
    private int guessedNum;
    private int posNum;
    private int[] positions;
    public int[] guessSomething(int[] values, int[] position, SketchNumbers[] notes){
        for(int i = 0;i<notes.length;i++){
            int[] nums = notes[i].getNumber();
            int count = 0;
            for(int k=0;k<nums.length;k++){
                if(nums[k]!=0){
                    count++;
                }
            }
            if(count>1 && count<9&& (int)(Math.random()*15)==0 && values[i]==0){
                posNum = i;
                break;
            }
        }
        
        int[] nums = notes[posNum].getNumber();
        for(int each: nums){
            if(each!=0 && (int)(Math.random()*2)==0){
                guessedNum = each;
            }

        }
        values[posNum] = guessedNum;
        
        position[posNum] = 10;
        this.positions = position;
        return values;

    }

    public int positionGuessed(){

        return posNum;
    }

    public int numberGuessed(){
        return guessedNum;
    }
    public int[] getPositions(){
        return positions;
    }
}