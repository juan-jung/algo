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
            for(int i = 1; i < programSize; i++) {
                for(int j = 1; j < programSize; j++) {
                    cells[i][j] = new Cell(i, j);
                }
            }
        }
        
        void update(int r, int c, String value) {
            Cell cell = find(cells[r][c]);
            cell.value = value;
        }
        
        void update(String value1, String value2) {
            for(int i = 1; i < programSize; i++) {
                for(int j = 1; j < programSize; j++) {
                    Cell cell = find(cells[i][j]);
                    if(cell.value.equals(value1)) {
                        cell.value = value2;
                    }
                }
            }
        }
        
        void merge(int r1, int c1, int r2, int c2) {
            Cell cell1 = find(cells[r1][c1]);
            Cell cell2 = find(cells[r2][c2]);
            if(cell1 != cell2) {
                if(!cell1.value.equals("EMPTY")) {
                    cell2.parent = cell1;
                } else {
                    cell1.parent = cell2;
                }
            }
        }
        
        void unmerge(int r, int c) {
            Cell root = find(cells[r][c]);
            String rootValue = root.value;
            
            List<Cell> mergedCells = new ArrayList<>();
            for(int i = 1; i < programSize; i++) {
                for(int j = 1; j < programSize; j++) {
                    if(find(cells[i][j]) == root) {
                        mergedCells.add(cells[i][j]);
                    }
                }
            }
            
            for(Cell cell : mergedCells) {
                cell.parent = cell;
                cell.value = "EMPTY";
            }
            cells[r][c].value = rootValue;
        }
        
        void print(int r, int c) {
            Cell cell = find(cells[r][c]);
            result.add(cell.value.equals("EMPTY") ? "EMPTY" : cell.value);
        }
        
        String[] answer() {
            return result.toArray(new String[0]);
        }
        
        Cell find(Cell cell) {
            if(cell != cell.parent) {
                cell.parent = find(cell.parent);
            }
            return cell.parent;
        }
    }
    
    class Cell {
        int r, c;
        String value;
        Cell parent;
        
        Cell(int r, int c) {
            this.r = r;
            this.c = c;
            this.value = "EMPTY";
            this.parent = this;
        }
    }
    
    public String[] solution(String[] commands) {
        int programSize = 51;
        Program program = new Program(programSize);
        program.init();
        
        for(String command : commands) {
            String[] op = command.split(" ");
            switch(op[0]) {
                case "UPDATE":
                    if(op.length == 4) {
                        int r = Integer.parseInt(op[1]);
                        int c = Integer.parseInt(op[2]);
                        program.update(r, c, op[3]);
                    } else if(op.length == 3) {
                        program.update(op[1], op[2]);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(op[1]);
                    int c1 = Integer.parseInt(op[2]);
                    int r2 = Integer.parseInt(op[3]);
                    int c2 = Integer.parseInt(op[4]);
                    program.merge(r1, c1, r2, c2);
                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(op[1]);
                    int c = Integer.parseInt(op[2]);
                    program.unmerge(r, c);
                    break;
                case "PRINT":
                    int r3 = Integer.parseInt(op[1]);
                    int c3 = Integer.parseInt(op[2]);
                    program.print(r3, c3);
                    break;
                default:
                    break;
            }
        }
        
        return program.answer();
    }
}
