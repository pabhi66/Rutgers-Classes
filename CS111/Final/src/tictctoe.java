/**
 * Created by Abhi on 12/13/14.
 */
public class tictctoe {
    public static void main(String[] args){
        System.out.print("Enter the length of the grid");
        int n = IO.readInt();
        char[][] board = new char[n][n];

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){

                do{
                    int rand = (int) (Math.random() * 3);
                    if(rand == 0)
                        board[i][j] = 'X';
                    if(rand == 1)
                        board[i][j] = 'O';
                    if(rand == 2)
                        board[i][j] = ' ';

                }while(board[i][j] == 0);
            }
        }

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length;j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        int countX = 0;
        int countO = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == 'X')
                  countX++;
                if(board[i][j] == 'O')
                    countO++;
            }
            while(i==i){
                if(countX == n)
                    System.out.print("X won");
                if(countO == n)
                    System.out.print("O won");
                else
                    System.out.print("game tie");
                break;
            }
        }

    }
}
