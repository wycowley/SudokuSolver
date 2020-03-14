

public class NoteNumbers{
    private int[] groupArray = new int[9];
    private boolean noGo;

    public SketchNumbers[] addThem(int[] values, SketchNumbers[] notes){
        for(int a=1;a<10;a++){
            //deals with squares
            for(int i = 0;i<3;i++){
                for(int k = 0;k<3;k++){
                    //3x3 grid
                    for(int j = 0;j<3;j++){
                        for(int l = 0;l<3;l++){
                            groupArray[j*3+l] = values[l+k*3+j*9+i*27];
                        }
                    }

                    
                    if(checkIn(groupArray,a)){
                        for(int j = 0;j<3;j++){
                            for(int l = 0;l<3;l++){
                                notes[l+k*3+j*9+i*27].removeNum(a);
                            }
                        }
                    }else{
                        for(int j = 0;j<3;j++){
                            for(int l = 0;l<3;l++){
                                notes[l+k*3+j*9+i*27].addNumber(a);
                            }
                        }
                    }
                }                
            }
            //deals with rows
            for(int i = 0;i<9;i++){
                for(int k = 0;k<9;k++){
                    groupArray[k] = values[k+i*9];
                    
                }
                if(checkIn(groupArray,a)){
                    for(int b = 0;b<9;b++){
                        notes[b+i*9].removeNum(a);
                    }
                }else{
                    for(int b = 0;b<9;b++){
                        notes[b+i*9].addNumber(a);
                    }
                }
                
            }
            //deals with columns
            for(int i = 0;i<9;i++){
                for(int k = 0;k<9;k++){
                    groupArray[k] = values[k*9+i];
                    
                }
                if(checkIn(groupArray,a)){
                    for(int b = 0;b<9;b++){
                        notes[b*9+i].removeNum(a);
                    }
                }else{
                    for(int b = 0;b<9;b++){
                        notes[b*9+i].addNumber(a);
                    }
                }
                
            }

        }


        return notes;

    }


    public boolean checkIn(int[] gArray, int a){
        for(int l = 0;l<groupArray.length;l++){
            if(gArray[l]==a){
                return true;
            }
        }
        return false;
    }
}

