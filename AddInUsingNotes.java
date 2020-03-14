

public class AddInUsingNotes{
    private int[] positions;
    private int[] groupArray = new int[9];
    private boolean guess;
    public int[] addIn(int[] values, SketchNumbers[] notes, int guessedNum, int[] positions){
        guess = false;
        outer: for(int a = 1;a<11;a++){
            for(int i = 0;i<3;i++){
                for(int k = 0;k<3;k++){
                    for(int y = 0;y<groupArray.length;y++){
                        groupArray[y]=0;
                    }
                    //3x3 grid
                    for(int j = 0;j<3;j++){
                        for(int l = 0;l<3;l++){
                            if(notes[l+k*3+j*9+i*27].numberIn(a) && values[l+k*3+j*9+i*27]==0){
                                groupArray[j*3+l]=a;
                            }
                        }
                    }
                    
                    int position = 0;
                    if(checkIn(groupArray,a)==1){
                        for(int c = 0;c<groupArray.length;c++){
                            if(groupArray[c]==a){
                                position = c;

                            }
                        }
                        values[k*3+i*27+position%3+position/3*9]=a;
                        if(guessedNum!=0){
                            positions[k*3+i*27+position%3+position/3*9]=3;
                        }else{
                            if(guessedNum!=0){
                                positions[k*3+i*27+position%3+position/3*9]=0;
                            }
                        }
                        this.positions = positions;
                        guess = false;
                        break outer;
                    }
                    


                }                
            }
            
            if(a==10){
                guess = true;
            }
        }

        return values;
    }

    public int checkIn(int[] gArray, int a){
        int count = 0;
        for(int l = 0;l<gArray.length;l++){
            if(gArray[l]==a){
                count++;
            }
        }
        return count;
    }
    public boolean getGuess(){
        return guess;
    }
    public int[] getPosition(){
        return positions;
    }
    public void setGuess(){
        guess = false;
    }
}