public class DeletionRows{
    private int[] squareNums = new int[9];
    public SketchNumbers[] deleteSome(int[] values,SketchNumbers[] notes){
        for(int a = 0;a<10;a++){
            for(int i = 0;i<3;i++){
                for(int k = 0;k<3;k++){
                    for(int c = 0;c<squareNums.length;c++){
                        squareNums[c]=0;
                    }
    
                    //3x3 grid
                    for(int j = 0;j<3;j++){
                        for(int l = 0;l<3;l++){
                            if(notes[l+k*3+j*9+i*27].numberIn(a) && values[l+k*3+j*9+i*27]==0){
                                squareNums[j*3+l]=a;
                            }                    }
                    }
                    
                    
                    
                    if(Count(squareNums,a)==2){
                        //gets the positions of each of the numbers selected
                        int[] position = {0,0};
                        for(int o=0;o<squareNums.length;o++){
                            if(squareNums[o]==a){
                                if(position[0]==0){
                                    position[0]=o;
                                }else{
                                    position[1]=o;
                                }
                            }
                        }
                        if(position[0]>position[1]){
                            int temp = position[0];
                            position[0]=position[1];
                            position[1]=temp;
                        }
                        
                        if((position[0]+3==position[1]) || (position[0]+6 == position[1])){
                            
                            //this gets if they are in a row next to each other
                            
                            int columns = (k*3+position[0]%3);
                            
                            

                            int row1 = (i*27+position[0]/3*9)/9;
                            int row2 = (i*27+position[1]/3*9)/9;
                            

                            for(int j = 0;j<9;j++){
                                if(j!=row1 && j!=row2){
                                    notes[columns+j*9].removeNum(a);
                                }
                            }
                        }

                        if((position[0]+1==position[1] && position[0]%3!=2) || position[0]+2==position[1] && position[0]%3==0){
                            //this gets if they are in a row next to each other
                            
                            int rows = (i*27+position[0]/3*9)/9;

                            

                            int columns1 = (k*3+position[0]%3);
                            int columns2 = (k*3+position[1]%3);

                            for(int j = 0;j<9;j++){
                                if(j!=columns1 && j!=columns2){
                                    notes[rows*9+j].removeNum(a);
                                }
                            }
                        }
                    
                    }
                }
            }
        }
        return notes;
    }

    private int Count(int[] array, int a){
        int count = 0;
        for(int each: array){
            if(each==a){
                count++;
            }
        }
        return count;
    }
}