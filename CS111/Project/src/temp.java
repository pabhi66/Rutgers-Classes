/**
 * Created by Abhi on 11/21/14.
 */
public class temp {
        public static void main(String[] args){

		/* The following two lines will prompt the user to indicate the dimensions for the square tissue grid
	    * and reads their input.
	    */

            char[][] p = {{'O','O',' ','X','X'},

                    {'O',' ',' ',' ','X'},

                    {'X','O','O','O','X'},

                    {'X','O','O','O','X'},

                    {'X','O','O','O','X'}};


            System.out.println(isSatisfied(p, 4, 1, 80));
        }

        /**
         * Given a tissue sample, prints the cell make up in grid form
         *
         * @param tissue a 2-D character array representing a tissue sample
         *
         ***/
        public static void printTissue(char[][] tissue){



            int row = 0;
            int col = 0;



            for (row=0;row<tissue.length;row++){



                for (col = 0;col<tissue[0].length;col++){
                    System.out.print(tissue[row][col]); // Prints that index of the array
                }
                System.out.println(); // Once one row has been printed, this allows me to go to the next line
            }
        }

        /**
         * Given a blank tissue sample, populate it with the correct cell makeup given the parameters.
         * Cell type 'X' will be represented by the character 'X'
         * Cell type 'O' will be represented by the character 'O'
         * Vacant spaces will be represented by the character ' '
         *
         * Phase I: alternate X and O cells throughout, vacant cells at the "end" (50% credit)
         *		e.g.:	'X' 'O' 'X' 'O' 'X'
         *				'O' 'X' 'O' 'X' 'O'
         *				'X' 'O' 'X' 'O' 'X'
         *				' ' ' ' ' ' ' ' ' '
         *				' ' ' ' ' ' ' ' ' '
         *
         * Phase II: Random assignment of all cells (100% credit)
         *
         * @param tissue a 2-D character array that has been initialized
         * @param percentBlank the percentage of blank cells that should appear in the tissue
         * @param percentX Of the remaining cells, not blank, the percentage of X cells that should appear in the tissue. Round up if not a whole number
         *
         **/
        public static void assignCellTypes(char[][] tissue, int percentBlank, int percentX){



            double Xnum, Onum, Blanknum, a;
            int row, col;



            if ((percentBlank<0)||(percentX<0)||(percentBlank>100)||(percentX>100)||(percentBlank+percentX)>100){
                System.out.print("invalid percentages");
                System.exit(1);
            }

            if (tissue.length != tissue[0].length){
                System.out.println("invalid dimensions");
                System.exit(1);
            }

		/* the following three assignments calculate the amount of x's o's and spaces by doing the following:
		 * first, it takes the percentage and divides it by 100 to make it a decimals. Then it multiplies it by the amount
		 * of tiles. Since there is no percentage for o's, the amount of o's is equal to the total amount of tiles
		 * minus the amount of x's and spaces.
		 */

            Blanknum = Math.ceil(((double) percentBlank/100.0)*( (double) tissue.length* (double) tissue.length));
            Xnum = Math.ceil(((double) percentX/100.0)*( ((double) tissue.length* (double) tissue.length)-(Blanknum)));
            Onum = (((double) tissue.length* (double) tissue.length)-(Blanknum + Xnum));

		/* The following assignments set up choices for my randomizer to choose from depending on which elements
		 * I still have at least one of
		 */

            String xoblank = "XO ";
            String xo = "XO ";
            String xblank = "X ";
            String oblank = "O ";


            main: while (true){

                if ((Xnum>0)&&(Onum>0)&&(Blanknum>0)){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    a= (int) (((Math.random()*(3-1)) + 1)-.00000000000000000001);
                    tissue[row][col] = xoblank.charAt((int) a);
                    if(tissue[row][col] == 'X')
                        Xnum--;
                    else if(tissue[row][col] == 'O')
                        Onum--;
                    else
                        Blanknum--;
                }
                else if ((Xnum>0)&&(Onum>0)){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    a = (int) (((Math.random()*(2-1)) + 1)-.00000000000000000001);
                    tissue[row][col] = xo.charAt((int) a);
                    if(tissue[row][col] == 'X')
                        Xnum--;
                    else
                        Onum--;
                }
                else if ((Xnum>0)&&(Blanknum>0)){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    a = (int) (((Math.random()*(2-1)) + 1)-.00000000000000000001);
                    tissue[row][col] = xblank.charAt((int) a);
                    if(tissue[row][col] == 'X')
                        Xnum--;
                    else
                        Blanknum--;
                }
                else if ((Onum>0)&&(Blanknum>0)){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    a = (int) (((Math.random()*(2-1)) + 1)-.00000000000000000001);
                    tissue[row][col] = oblank.charAt((int) a);
                    if(tissue[row][col] == 'O')
                        Onum--;
                    else
                        Blanknum--;
                }
                else if (Xnum>0){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    tissue[row][col] = 'X';
                    Xnum--;
                }
                else if (Onum>0){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    tissue[row][col] = 'O';
                    Onum--;
                }
                else if (Blanknum>0){
                    for (;;){
                        row = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        col = (int) (Math.random()*(tissue.length-.00000000000000000001));
                        if (tissue[row][col] == '\u0000')
                            break;
                    }
                    tissue[row][col] = ' ';
                    Blanknum--;
                }
                if ((Xnum<=0)&&(Onum<=0)&&(Blanknum<=0))
                    break main;
            }
        }
        /**
         * Given a tissue sample, and a (row,col) index into the array, determines if the agent at that location is satisfied.
         * Note: Blank cells are always satisfied (as there is no agent)
         *
         * @param tissue a 2-D character array that has been initialized
         * @param row the row index of the agent
         * @param col the col index of the agent
         * @param threshold the percentage of like agents that must surround the agent to be satisfied
         * @return boolean indicating if given agent is satisfied
         *
         **/
        public static boolean isSatisfied(char[][] tissue, int row, int col, int threshold){
            if(tissue[row][col]==' ')
                return true;

            double count = 0;
            char agent = tissue[row][col]; //element == X, O, blank
            int n = 0;
            if(row<0 || row>tissue.length-1 || col<0 || col>tissue[0].length-1 || threshold<0 || threshold>100){
                IO.reportBadInput();
            }


            if(col-1>=0 && tissue[row][col-1] != ' '){
                n++;
                if(row>=0 && row<tissue.length && col-1>=0 && col-1<tissue.length && agent==tissue[row][col-1] ){
                    count++;
                }
            }if(row-1>=0 && col-1>=0 && tissue[row-1][col-1]!= ' '){
                n++;
                if(row-1>=0 && row-1<tissue.length && col-1>=0 && col-1<tissue.length && agent == tissue[row-1][col-1]){
                    count++;
                }
            }if(row-1>=0 && tissue[row-1][col]!= ' '){
                n++;
                if(row-1>=0 && row-1<tissue.length && col>=0 && col<tissue.length && agent == tissue[row-1][col]){
                    count++;
                }
            }if(row-1>=0 && col+1<=tissue.length-1 && tissue[row-1][col+1] != ' '){
                n++;
                if(row-1>=0 && row-1<tissue.length && col+1>=0 && col+1<tissue.length && agent == tissue[row-1][col+1]){
                    count++;
                }
            }if(col+1<=tissue.length-1 && tissue[row][col+1] != ' '){
                n++;
                if(row>=0 && row<tissue.length && col+1>=0 && col+1<tissue.length && agent == tissue[row][col+1]){
                    count++;
                }
            }if(row+1<=tissue.length-1 && col+1<=tissue.length-1 && tissue[row+1][col+1] != ' '){
                n++;
                if(row+1>=0 && row+1<tissue.length && col+1>=0 && col+1<tissue.length && agent == tissue[row+1][col+1]){
                    count++;
                }
            }if(row+1<=tissue.length-1 && tissue[row+1][col] != ' '){
                n++;
                if(row+1>=0 && row+1<tissue.length && col>=0 && col<tissue.length && agent == tissue[row+1][col]){
                    count++;
                }
            }if(row+1<=tissue.length-1 && col-1>=0 && tissue[row+1][col-1] != ' '){
                n++;
                if(row+1>=0 && row+1<tissue.length && col-1>=0 && col-1<tissue.length && agent == tissue[row+1][col-1]){
                    count++;
                }
            }

            if(count >= (Math.ceil((threshold*n)/100))){
                return true;
            }
            return false;
        }
        /**
         * Given a tissue sample, determines if all agents are satisfied.
         * Note: Blank cells are always satisfied (as there is no agent)
         *
         * @param tissue a 2-D character array that has been initialized
         * @return boolean indicating whether entire board has been satisfied (all agents)
         **/
        public static boolean boardSatisfied(char[][] tissue, int threshold){
            for(int i=0;i<tissue.length;i++){
                for(int j=0;j<tissue[i].length;j++){
                    if(isSatisfied(tissue,i,j,threshold)==false){
                        return false;
                    }

                }

            }
            return true;
        }
    }


