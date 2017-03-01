/**
 * Created by Abhi on 11/17/14.
 */

public class testProject {
    public static void main(String[] args){

        System.out.println("Enter the size of the array n * n: ");
        int size = IO.readInt(); //size of the array
        System.out.println("Enter the threshold percentage from 1 to 100: ");
        int threshold = IO.readInt(); //reads the threshold
        System.out.println("Enter the maximum number of rounds your simulation should run before giving up: ");
        int maxRound = IO.readInt(); //max # of rounds the simulation runs
        System.out.println("Enter the frequency: the number of time your board should be printed: ");
        int frequency = IO.readInt(); //board should be printed at this frequency
        System.out.println("Enter the percentage of X agents from 1 to 100: ");
        int xAgents = IO.readInt(); //number of X agents being passed in the tissue
        System.out.println("Enter the percentage of blank cells from 1 to 100: ");
        int blankCells = IO.readInt(); //number of Blank Cells being passed in the tissue

        char[][] tissue = new char[size][size]; //create a tissue wih given size
        int numOfRounds =  0; //number of round counter
        int moved = 0; //counts number of moved each time the method is called
        int satisfiedCells = 0; //counts the number of satisfied cells in th tissue


        assignCellTypes(tissue,blankCells,xAgents); //assign cells in the tissue
        System.out.println("This is the initial tissue"); //prints this
        printTissue(tissue);// prints the tissue

        //loop to move all the rounds until the board is satisfied or the max round has reached
        while(!boardSatisfied(tissue,threshold)) {

            //compares the number or rounds to max round and breaks the loop if num of rounds is more then max rounds
            if (numOfRounds >= maxRound) {
                break;
            }
            moved += moveAllUnsatisfied(tissue, threshold); //adds all the cells that are moved
            numOfRounds++;//adds one to round
            //if frequency == num of rounds print tissue
            if (frequency == numOfRounds) {
                System.out.println(); //leaves a space between each tissue
                printTissue(tissue); //print tissue
                frequency += frequency; //add frequency to frequency
            }
        }
        System.out.println("This is the final tissue:"); //prints this
        printTissue(tissue); //prints the final tissue

        //if tissue is satisfied do this
        if(boardSatisfied(tissue,threshold)){
            System.out.println("the tissue is satisfied");
            System.out.println("the number of rounds to satisfy a tissue: ");
            IO.outputIntAnswer(numOfRounds);
            System.out.println("total number of movements: ");
            IO.outputIntAnswer(moved);
        }
        else{ //if tissue is not satisfied than do this
            System.out.println("The tissue is not satisfied");
            //go through the loop and count all the satisfied cells but not the blank cells
            for(int row=0; row<tissue.length; row++){
                for(int col=0; col<tissue[row].length; col++){
                    if(isSatisfied(tissue,row,col,threshold) && tissue[row][col] != ' '){
                        satisfiedCells++;
                    }
                }
            }
            System.out.println("total satisfied cells in the tissue: ");
            IO.outputIntAnswer(satisfiedCells);
            System.out.println("total number of movements: ");
            IO.outputIntAnswer(moved);
        }



    }
    public static void printTissue(char[][] tissue){
        //Your code goes here
        //I created nested for loop to iterate through the 2d array, and gets the cells from the specific array and prints that
        //first loops iterates rows
        //second loop iterates the columns
        for(int row=0; row<tissue.length; row++){
            for(int col=0; col<tissue[0].length; col++){
                System.out.print("'" + tissue[row][col] + "'" + "\t"); //prints the array
            }
            System.out.println(); //enters the new line
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

        //Your code goes here

        //finds the number ob blanks that goes in the array
        double blankCount = (tissue.length * tissue[0].length) * (percentBlank / 100.0);
        blankCount = Math.ceil(blankCount);
        //finds the number of x that goes in the array
        double xCount = ((double) tissue.length * tissue[0].length - blankCount) * (percentX/100.0) ;
        xCount = Math.ceil(xCount);
        //finds the number of o that goes in the array
        double oCount = tissue.length * tissue[0].length - (blankCount + xCount);


        //use for loop to place all the element in the array
        //nested for loop to iterate each element in the array
        for(int row=0; row<tissue.length; row++)
            for (int col = 0; col < tissue[row].length; col++) {
                //loop that assign X,O,and ' ' to the the cells randomly
                do {
                    int x = (int) (Math.random() * 3);
                    if (x == 0 && xCount > 0) {
                        tissue[row][col] = 'X';
                        xCount--;
                    }
                    if (x == 1 && oCount > 0) {
                        tissue[row][col] = 'O';
                        oCount--;
                    }
                    if (x == 2 && blankCount > 0) {
                        tissue[row][col] = ' ';
                        blankCount--;
                    }
                }while(tissue[row][col] == 0);
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


        double percentSatiesfied = 0;
        int xCount = 0;
        int oCount = 0;
        int blankCount = 0;
        double xPer = 0;
        double oPer = 0;
        if(tissue[row][col] == ' ')
            return true;

        //for checking the outside cells
        if(row == 0 || row == tissue.length-1 || col == 0 || col == tissue[0].length-1) {
            {
                //checking the first cell at [0][0]
                if (row == 0 && col == 0) {
                    for (int i = 0; i <= 1; i++) {
                        for (int j = 0; j <= 1; j++) {
                            if (tissue[row][col] == 'X') {
                                if (tissue[row + i][col + j] == 'X')
                                    xCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                            if (tissue[row][col] == 'O') {
                                if (tissue[row + i][row + j] == 'O')
                                    oCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                        }
                    }
                }
                //checking the cell in first row and last col
                else if (row == 0 && col == tissue[0].length - 1) {
                    for (int i = 0; i <= 1; i++) {
                        for (int j = -1; j <= 0; j++) {
                            if (tissue[row][col] == 'X') {
                                if (tissue[row + i][col + j] == 'X')
                                    xCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                            if (tissue[row][col] == 'O') {
                                if (tissue[row + i][col + j] == 'O')
                                    oCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                        }
                    }
                }

                //checking the cell in last row and first col
                else if (row == tissue.length - 1 && col == 0) {
                    for (int i = -1; i <= 0; i++) {
                        for (int j = 0; j <= 1; j++) {
                            if (tissue[row][col] == 'X') {
                                if (tissue[row + i][col + j] == 'X')
                                    xCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                            if (tissue[row][col] == 'O') {
                                if (tissue[row + i][col + j] == 'O')
                                    oCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                        }
                    }
                }

                //checking the cell in last row and last col[row-1][col-1]
                else if (row == tissue.length - 1 && col == tissue[tissue.length - 1].length - 1) {
                    for (int i = -1; i <= 0; i++) {
                        for (int j = -1; j <= 0; j++) {
                            if (tissue[row][col] == 'X') {
                                if (tissue[row + i][col + j] == 'X')
                                    xCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                            if (tissue[row][col] == 'O') {
                                if (tissue[row + i][col + j] == 'O')
                                    oCount++;
                                if(tissue[row+i][col+j] == ' ')
                                    blankCount++;
                            }
                        }
                    }
                }
                //find the is the cell satisfies the threshold
                if (tissue[row][col] == 'X') {
                    if(xCount == 1 && threshold == 0)
                        return true;
                    if (xCount == 1)
                        return false;
                    xPer = (double) (xCount - 1) / (3-blankCount) * 100;
                    if (xPer >= threshold)
                        return true;
                }
                if (tissue[row][col] == 'O') {
                    if(oCount == 1 && threshold == 0)
                        return true;
                    if (oCount == 1)
                        return false;
                    oPer = (double) (oCount - 1) / (3-blankCount) * 100;
                    if (oPer >= threshold)
                        return true;
                }
            }
            // checking the first row but not first and last cell in first row
            if(row == 0 && (col > 0 && col < tissue[0].length -1)){
                for (int i = 0; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(tissue[row][col] == 'X') {
                            if (tissue[row+i][col+j] == 'X')
                                xCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                        if(tissue[row][col] == 'O'){
                            if(tissue[row+i][col+j] == 'O')
                                oCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                    }
                }
            }

            //checking the last row but not first and last cell in last row
            else if(row == tissue.length -1 && (col > 0 && col <tissue[0].length -1)){
                for (int i = -1; i <= 0; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(tissue[row][col] == 'X') {
                            if (tissue[row+i][col+j] == 'X')
                                xCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                        if(tissue[row][col] == 'O'){
                            if(tissue[row+i][col+j] == 'O')
                                oCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                    }
                }
            }

            //checking the first col but not the first and last cell in that
            else if(col == 0 && (row > 0 && row < tissue.length -1)){
                for(int i=-1; i<=1; i++){
                    for(int j=0; j<=1; j++){
                        if(tissue[row][col] == 'X') {
                            if (tissue[row+i][col+j] == 'X')
                                xCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                        if(tissue[row][col] == 'O'){
                            if(tissue[row+i][col+j] == 'O')
                                oCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                    }
                }
            }

            //checking the last col but not the first and last cell in that col
            else if(col == tissue[0].length -1 && (row > 0 && row < tissue.length-1)){
                for(int i=-1; i<=1; i++){
                    for(int j=-1; j<=0; j++){
                        if(tissue[row][col] == 'X') {
                            if (tissue[row+i][col+j] == 'X')
                                xCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                        if(tissue[row][col] == 'O'){
                            if(tissue[row+i][col+j] == 'O')
                                oCount++;
                            if(tissue[row+i][col+j] == ' ')
                                blankCount++;
                        }
                    }
                }
            }
            if(tissue[row][col] == 'X') {
                if(xCount == 1 && threshold == 0)
                    return true;
                if(xCount == 1)
                    return  false;
                xPer = (double) (xCount-1) / (5-blankCount) * 100;
                if(xPer >= threshold)
                    return true;
            }
            if(tissue[row][col] == 'O') {
                if(oCount == 1 && threshold == 0)
                    return true;
                if(oCount == 1)
                    return  false;
                oPer = (double) (oCount-1) / (5-blankCount) * 100;
                if (oPer >= threshold)
                    return true;
            }
        }

        //for checking the inside cells
        if(!(row == 0 || row == tissue.length-1 || col == 0 || col == tissue[0].length-1)){
            for(int i=-1; i<=1; i++){
                for(int j=-1; j<=1; j++){

                    if(tissue[row][col] == 'X') {
                        if (tissue[row+i][col+j] == 'X')
                            xCount++;
                        if(tissue[row+i][col+j] == ' ')
                            blankCount++;
                    }
                    if(tissue[row][col] == 'O'){
                        if(tissue[row+i][col+j] == 'O')
                            oCount++;
                        if(tissue[row+i][col+j] == ' ')
                            blankCount++;
                    }
                }
            }
            if(tissue[row][col] == 'X') {
                if(xCount == 1 && threshold == 0)
                    return true;
                if(xCount == 1)
                    return  false;
                xPer = (double) (xCount-1) / (8-blankCount) * 100;
                if(xPer >= threshold)
                    return true;
            }
            if(tissue[row][col] == 'O') {
                if(oCount == 1 && threshold == 0)
                    return true;
                if(oCount == 1)
                    return  false;
                oPer = (double) (oCount-1) / (8-blankCount) * 100;
                if (oPer >= threshold)
                    return true;
            }
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

        for(int i=0; i<tissue.length; i++){
            for(int j=0; j<tissue[i].length; j++){
                if(!(isSatisfied(tissue,i,j,threshold)))
                    return false;
            }
        }
        return true;
    }


    /**
     * Given a tissue sample, move all unsatisfied agents to a vacant cell
     *
     * @param tissue a 2-D character array that has been initialized
     * @param threshold the percentage of like agents that must surround the agent to be satisfied
     * @return an integer representing how many cells were moved in this round
     **/
    public static int moveAllUnsatisfied(char[][] tissue, int threshold){

        /*char[][] arr = new char[tissue.length][tissue[0].length];
		char[][] newArr = new char[tissue.length][tissue[0].length];
		int count = 0;
		char temp;
		char[] temp1 = new char[tissue.length * tissue[0].length];

		for(int i=0; i<tissue.length; i++){
			for(int j=0; j<tissue[0].length; j++){
				if((isSatisfied(tissue,i,j,threshold)) == false){
					//newArr[i][j] = tissue[i][j];
					temp1[i] = tissue[i][j];
					System.out.println(temp1[i]);
					System.out.println(i + " " + j + " false cell");
					for(int k=0; k<tissue.length; k++){
						for(int l=0; l<tissue[k].length; l++){
							if(tissue[k][l] == ' '){
								tissue[i][j] = newArr[k][l];
								newArr[k][l] = temp1[i];
							}
							break;
						}
						//break;
					}
				}
				//break;
			}
			break;
		}

		System.out.println("------------------");
		printTissue(tissue);
		System.out.println("------------------");
		printTissue(newArr);
		System.out.println("------------------");
		printNewTissue(temp1);

		return count;*/

		/*char[][] arr = new char[tissue.length][tissue[0].length];
		char[][] newArr = new char[tissue.length][tissue[0].length];
		int count = 0;
		char temp;
		char temp1;
		int i=0;
		int j=0;
		int row=0;
		int col=0;
		for(i=0; i<tissue.length; i++){
			for(j=0; j<tissue[0].length; j++){
				if((isSatisfied(tissue,i,j,threshold)) == false){
					temp = tissue[i][j];
					System.out.println(i + " " + j + " false cell");
					int k;
					int l;
					for(k=0; k<tissue.length; k++){
						for(l=0; l<tissue.length; l++){
							if(tissue[k][l] != ' '){
								tissue[i][j] = arr[k][l];
								arr[k][l] = tissue[k][l];
							}
							if(tissue[k][l] == ' '){
								System.out.println(k + " " + " empty");
								tissue[i][j] = arr[k][l];
								arr[k][l] = temp;
								//break;
							}
							//break;
						}
					}
				}
			}
		}
		System.out.println("------------------");
		printTissue(tissue);
		System.out.println("------------------");
		printTissue(arr);
		return  count;*/

        //-----------------------------------------------------------------------
        //BEST ONE ALMOST WORKING
		/*
		char temp = ' ';
		int count = 0;
		char[][] arr = new char[tissue.length][tissue[0].length];
		for(int i=0; i<tissue.length; i++){
			for(int j=0; j<tissue[i].length;j++){
				if(isSatisfied(tissue,i,j,threshold) && tissue[i][j] != ' '){
					arr[i][j] = tissue[i][j];
				}
				if(!isSatisfied(tissue,i,j,threshold)){
					temp = tissue[i][j];
					outer:
					for(int k=0; k<tissue.length; k++){
						for(int l=0; l<tissue[k].length; l++){
							if(tissue[k][l] == ' '){
								tissue[i][j] = arr[k][l];
								arr[k][l] = temp;
								break outer;
							}
						}
					}
				}
				}
			}
		System.out.println(temp);
		System.out.println("-----------");
		printTissue(tissue);
		System.out.println("-----------");
		printTissue(arr);
		return count;*/

		/*char temp = ' ';
		char[] temp1 = new char[tissue.length * tissue[0].length];
		int count = 0;
		char[][] arr = new char[tissue.length][tissue[0].length];
		do {
			for (int i = 0; i < tissue.length; i++) {
				for (int j = 0; j < tissue[i].length; j++) {
					//if(isSatisfied(tissue,i,j,threshold) && tissue[i][j] != ' '){
					//	arr[i][j] = tissue[i][j];
					//}
					if (!isSatisfied(tissue, i, j, threshold)) {
						temp1[i] = tissue[i][j];
						System.out.println(i + " " + j + " false");
						System.out.println(temp1[i]);
						outer:
						for (int k = 0; k < tissue.length; k++) {
							for (int l = 0; l < tissue[k].length; l++) {
								if (tissue[k][l] == ' ') {
									//tissue[i][j] = arr[k][l];
									//for(int a=0; a<temp1.length;a++) {
									arr[k][l] = temp1[i];
									//}
									break outer;
								}
							}
						}
					}
				}
			}
		}while (boardSatisfied(tissue,threshold));
		System.out.println(temp);
		System.out.println("-----------");
		printTissue(tissue);
		System.out.println("-----------");
		printTissue(arr);
		return  count;*/

		/*int count = 0;
		int[][] storeFalse = new int[tissue.length][tissue[0].length];
		int[][] storeBlank = new int[tissue.length][tissue[0].length];
		char falseCell = ' ';
		char blankCell = ' ';
		char temp = ' ';
			for (int i = 0; i < tissue.length; i++) {
				for (int j = 0; j < tissue[i].length; j++) {
					/*if ((isSatisfied(tissue, i, j, threshold)) == false) {
						falseCell = tissue[i][j];
						tissue[i][j] = ' ';
						System.out.println(i + " " + j + " false cell");
						System.out.println(falseCell);
						break;
					}
					for (int k = 0; k < tissue.length; k++) {
						for (int l = 0; l < tissue[k].length; l++) {
							if (tissue[k][l] == ' ') {
								blankCell = tissue[k][l];
								tissue[k][l] = falseCell;
								break;
							}
						}
						break;
					}*/
					/*if(tissue[i][j] == ' '){
						for(int k=0; k<tissue.length; k++){
							for(int l=0; l<tissue.length; l++){
								if(!isSatisfied(tissue,k,l,threshold)){
									System.out.println(i + " " + j + " false cell");
									tissue[i][j] = tissue[k][l];
								}
							}
							break;
						}
					}
				}
			}
		System.out.println("-------------");
		printTissue(tissue);
		return count;*/

		/*char[][] temp1 = new char[tissue.length][ tissue[0].length];
		int count = 0;
		char[][] arr = new char[tissue.length][tissue[0].length];
		for (int i = 0; i < tissue.length; i++) {
			for (int j = 0; j < tissue[i].length; j++) {
				if(isSatisfied(tissue,i,j,threshold)){
					arr[i][j] = tissue[i][j];
				}
				if (!isSatisfied(tissue, i, j, threshold)) {
					temp1[i][j] = tissue[i][j];
					count++;
				}
			}
		}
		int sq = arr.length * arr.length;
		for (int k = 0; k < arr.length; k++) {
			for (int l = 0; l < arr[k].length; l++) {
				if (temp1[k][l] != '\u0000' && temp1[k][l] != ' ') {
					int x = k * arr.length + l;

					label:
					for(int i=0; i<arr.length; i++){
						for(int j=0; j<arr[0].length; j++){
							int curr = (x + i * arr.length + j + 1)%sq;
							int ii  = curr / arr.length;
							int jj = curr % arr.length;
							if(arr[ii][jj] == ' ' || arr[ii][jj] == 0 ){
								// if(k != i || l != j) {
								arr[ii][jj] = temp1[k][l];
								break label;
								//}
							}
						}
					}
				}
			}
		}
		printTissue(arr);
		return  count;*/

        char[][] temp1 = new char[tissue.length][ tissue[0].length];
        int count = 0;
        int moved = 0;
        int countBlank = 0;
        char[][] arr = new char[tissue.length][tissue[0].length];



        for(int i=0; i<tissue.length; i++){
            for(int j=0; j<tissue[i].length; j++){
                if(tissue[i][j] == ' '){
                    countBlank++;
                    break;
                }
            }
        }
        if(countBlank == 0)
            return 0;
        for (int i = 0; i < tissue.length; i++) {
            for (int j = 0; j < tissue[i].length; j++) {
                if(isSatisfied(tissue,i,j,threshold)){
                    arr[i][j] = tissue[i][j];
                }
                if (!isSatisfied(tissue, i, j, threshold)) {
                    temp1[i][j] = tissue[i][j];
                    count++;
                }
            }
        }
        int sq = arr.length * arr.length;
        for (int k = 0; k < arr.length; k++) {
            for (int l = 0; l < arr[k].length; l++) {
            if (temp1[k][l] != '\u0000' && temp1[k][l] != ' ') {
                int x = k * arr.length + l;

                label:
                    for(int i=0; i<arr.length; i++){
                        for(int j=0; j<arr[0].length; j++){
                            int curr = (x + i * arr.length + j + 1)%sq;
                            int ii  = curr / arr.length;
                            int jj = curr % arr.length;
                            if(arr[ii][jj] == ' ' || arr[ii][jj] == 0 ){
                               // if(k != i || l != j) {
                                    arr[ii][jj] = temp1[k][l];
                                    moved++;
                                    break label;
                                //}
                            }
                        }
                    }
                }
            }
        }
        for(int i=0; i<tissue.length; i++){
            for(int j=0; j<tissue[i].length; j++){
                tissue[i][j] = arr[i][j];
            }
        }
        for(int i=0; i<tissue.length; i++){
            for(int j=0; j<tissue[i].length; j++){
                if(tissue[i][j] == ' ' || tissue[i][j] == 0){
                    tissue[i][j] = ' ';
                }
            }
        }


        return moved;
    }

   }