import java.util.*;

class Solution {
    class Program {
        int programSize;
        Cell[][] cells;
        ArrayList<String> result;
        
        Program(int programSize) {
            this.programSize = programSize;
            this.cells = new Cell[programSize][programSize];
            this.result = new ArrayList<String>();
        }
        
        void init() {
            for(int i=1;i<programSize;i++) {
                for(int j=1;j<programSize;j++) {
                    cells[i][j] = new Cell();
                }
            }
        }
        
        void update(int r, int c, String value) {
            cells[r][c].value = value;
        }
        
        void update(String value1, String value2) {
            for(int i=1;i<programSize;i++) {
                for(int j=1;j<programSize;j++) {
                    if(cells[i][j].value.equals(value1)) {
                        cells[i][j].value = value2;
                    }
                }
            }
        }
        
        void merge(int r1, int c1, int r2, int c2) {
            Cell toBeMerged = cells[r2][c2];
            for(int i=1;i<programSize;i++) {
                for(int j=1;j<programSize;j++) {
                    if(cells[i][j] == toBeMerged) {
                        cells[i][j] = cells[r1][c1];
                    }
                }
            }
            
            if(cells[r1][c1].value.equals("EMPTY")) cells[r1][c1].value = toBeMerged.value;
        }
        
        void unmerge(int r, int c) {
            Cell cell = cells[r][c];
            String value = cell.value;
            for(int i=1;i<programSize;i++) {
                for(int j=1;j<programSize;j++) {
                    if(cells[i][j]==cell) {
                        cells[i][j] = new Cell();
                    }
                }
            }
            cells[r][c].value = value;
        }
        
        void print(int r, int c) {
            result.add(cells[r][c].value);
        }
        
        String[] answer () {
            return result.toArray(new String[0]);
        }
    }
    
    class Cell {
        String value;
        Cell() {
            this.value = "EMPTY";
        }
    }
    public String[] solution(String[] commands) {
        int programSize = 51;
        Program program = new Program(programSize);
        program.init();
        
        int r,c;
        for(String command : commands) {
            String[] op = command.split(" ");
            switch(op[0]) {
                case "UPDATE" : 
                    if(op.length==4) {
                        r = Integer.parseInt(op[1]);
                        c = Integer.parseInt(op[2]);
                        program.update(r,c,op[3]);
                    }
                    else if(op.length==3) {
                        program.update(op[1], op[2]);
                    }
                    break;
                case "MERGE" :
                    int r1 = Integer.parseInt(op[1]);
                    int c1 = Integer.parseInt(op[2]);
                    int r2 = Integer.parseInt(op[3]);
                    int c2 = Integer.parseInt(op[4]);
                    program.merge(r1,c1,r2,c2);
                    break;
                case "UNMERGE" :
                    r = Integer.parseInt(op[1]);
                    c = Integer.parseInt(op[2]);
                    program.unmerge(r,c);
                    break;
                case "PRINT" :
                    r = Integer.parseInt(op[1]);
                    c = Integer.parseInt(op[2]);
                    program.print(r,c);
                    break;
                default : break;
            }
        }        
        
        return program.answer();
    }
}