import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GameLogic implements PlayableLogic{

    private ConcretePlayer player1, player2;
    private ConcretePiece[][] board = new ConcretePiece[11][11];
    private ConcretePiece[] pieces = new ConcretePiece[37];
    private Position[][] positions = new Position[11][11];

    private static boolean winner1;

    private Position kingP;
    private boolean player2turn;
    private int lp=0;

    public GameLogic(){
        kingP= new Position(5,5);
        player1=new ConcretePlayer(true);
        player2=new ConcretePlayer(false);
        board = createBoard();
        player2turn = true;
        for (int i=0; i<11; i++)
            for (int j=0; j<11; j++)
                positions[i][j]=new Position(i, j);
    }

    public ConcretePiece[][] createBoard(){
        ConcretePiece[][] temp = new ConcretePiece[11][11];
//        temp[3][0]= new Pawn(player2, "♟", 7);
//        temp[4][0]= new Pawn(player2, "♟", 9);
//        temp[5][0]= new Pawn(player2, "♟", 11);
//        temp[6][0]= new Pawn(player2, "♟", 15);
//        temp[7][0]= new Pawn(player2, "♟", 17);
//        temp[5][1]= new Pawn(player2, "♟", 12);
//        temp[3][10]= new Pawn(player2, "♟", 8);
//        temp[4][10]= new Pawn(player2, "♟",10);
//        temp[5][10]= new Pawn(player2, "♟",14);
//        temp[6][10]= new Pawn(player2, "♟",16);
//        temp[7][10]= new Pawn(player2, "♟", 18);
//        temp[5][9]= new Pawn(player2, "♟", 13);
//        temp[10][3]= new Pawn(player2, "♟", 20);
//        temp[10][4]= new Pawn(player2, "♟",21);
//        temp[10][5]= new Pawn(player2, "♟",22);
//        temp[10][6]= new Pawn(player2, "♟",23);
//        temp[10][7]= new Pawn(player2, "♟",24);
//        temp[9][5]= new Pawn(player2, "♟",19);
//        temp[0][3]= new Pawn(player2, "♟",1);
//        temp[0][4]= new Pawn(player2, "♟",2);
//        temp[0][5]= new Pawn(player2, "♟",3);
//        temp[0][6]= new Pawn(player2, "♟",4);
//        temp[0][7]= new Pawn(player2, "♟",5);
//        temp[1][5]= new Pawn(player2, "♟",6);
//        temp[5][3]= new Pawn(player1, "♙",5);
//        temp[5][7]= new Pawn(player1, "♙", 9);
//        temp[4][4]= new Pawn(player1, "♙",2);
//        temp[5][4]= new Pawn(player1, "♙", 6);
//        temp[6][4]= new Pawn(player1, "♙", 10);
//        temp[4][6]= new Pawn(player1, "♙", 4);
//        temp[5][6]= new Pawn(player1, "♙", 8);
//        temp[6][6]= new Pawn(player1, "♙", 12);
//        temp[3][5]= new Pawn(player1, "♙", 1);
//        temp[4][5]= new Pawn(player1, "♙", 3);
//        temp[5][5]= new Pawn(player1, "♔", 7);
//        temp[6][5]= new Pawn(player1, "♙", 11);
//        temp[7][5]= new Pawn(player1, "♙", 13);
        temp[0][3]= new Pawn(player2, "♟", 7);
        temp[0][4]= new Pawn(player2, "♟", 9);
        temp[0][5]= new Pawn(player2, "♟", 11);
        temp[0][6]= new Pawn(player2, "♟", 15);
        temp[0][7]= new Pawn(player2, "♟", 17);
        temp[1][5]= new Pawn(player2, "♟", 12);
        temp[10][3]= new Pawn(player2, "♟", 8);
        temp[10][4]= new Pawn(player2, "♟",10);
        temp[10][5]= new Pawn(player2, "♟",14);
        temp[10][6]= new Pawn(player2, "♟",16);
        temp[10][7]= new Pawn(player2, "♟", 18);
        temp[9][5]= new Pawn(player2, "♟", 13);
        temp[3][10]= new Pawn(player2, "♟", 20);
        temp[4][10]= new Pawn(player2, "♟",21);
        temp[5][10]= new Pawn(player2, "♟",22);
        temp[6][10]= new Pawn(player2, "♟",23);
        temp[7][10]= new Pawn(player2, "♟",24);
        temp[5][9]= new Pawn(player2, "♟",19);
        temp[3][0]= new Pawn(player2, "♟",1);
        temp[4][0]= new Pawn(player2, "♟",2);
        temp[5][0]= new Pawn(player2, "♟",3);
        temp[6][0]= new Pawn(player2, "♟",4);
        temp[7][0]= new Pawn(player2, "♟",5);
        temp[5][1]= new Pawn(player2, "♟",6);
        temp[3][5]= new Pawn(player1, "♙",5);
        temp[7][5]= new Pawn(player1, "♙", 9);
        temp[4][4]= new Pawn(player1, "♙",2);
        temp[4][5]= new Pawn(player1, "♙", 6);
        temp[4][6]= new Pawn(player1, "♙", 10);
        temp[6][4]= new Pawn(player1, "♙", 4);
        temp[6][5]= new Pawn(player1, "♙", 8);
        temp[6][6]= new Pawn(player1, "♙", 12);
        temp[5][3]= new Pawn(player1, "♙", 1);
        temp[5][4]= new Pawn(player1, "♙", 3);
        temp[5][5]= new Pawn(player1, "♔", 7);
        temp[5][6]= new Pawn(player1, "♙", 11);
        temp[5][7]= new Pawn(player1, "♙", 13);
        return temp;
    }

    @Override
    public boolean move(Position a, Position b) {
        if (board[a.getX()][a.getY()].getOwner().isPlayerOne() != player2turn) {
            boolean flag = true;
            if (board[b.getX()][b.getY()] == null) {
                if (a.getX() == b.getX()) {
                    for (int i = Math.min(a.getY(), b.getY()) + 1; i < Math.max(a.getY(), b.getY()); i++)
                        if (board[a.getX()][i] != null)
                            flag = false;
                } else if (a.getY() == b.getY()) {
                    for (int i = Math.min(a.getX(), b.getX()) + 1; i < Math.max(a.getX(), b.getX()); i++)
                        if (board[i][a.getY()] != null)
                            flag = false;
                }
                else flag=false;
            } else flag = false;
            if (flag) {
                if (!board[a.getX()][a.getY()].steppedOn(a))
                    positions[a.getX()][a.getY()].addStep();
                if (!board[a.getX()][a.getY()].steppedOn(b))
                    positions[b.getX()][b.getY()].addStep();
                if (board[a.getX()][a.getY()].getSteps().isEmpty())
                    board[a.getX()][a.getY()].addStep(a);
                board[a.getX()][a.getY()].addStep(b);
            }
            if (flag && a.getX() == kingP.getX() && a.getY() == kingP.getY()) {
                kingP = b;
                board[b.getX()][b.getY()] = board[a.getX()][a.getY()];
                board[a.getX()][a.getY()] = null;
                player2turn = !player2turn;
            } else if (flag) {
                if (!(b.getY() == 10 && b.getX() == 10 || b.getY() == 0 && b.getX() == 0 || b.getY() == 10 && b.getX() == 0 || b.getY() == 0 && b.getX() == 10)) {
                    board[b.getX()][b.getY()] = board[a.getX()][a.getY()];
                    board[a.getX()][a.getY()] = null;
                    player2turn = !player2turn;
                    eat(b);
                } else return false;
            }
            if (isGameFinished()){
                StepsComparator s = new StepsComparator(board, pieces, winner1);
                s.PrintArraySteps();
                KillComparator c = new KillComparator(board, winner1, pieces);
                c.PrintArrayKills();
                DistanceComparator d = new DistanceComparator(board,pieces,winner1);
                d.PrintPlayerDistance();
                SquarsComparator sq = new SquarsComparator(positions);
                sq.printArray();
            }
            //isGameFinished();
            return flag;
        }
        if (isGameFinished()){
            StepsComparator s = new StepsComparator(board, pieces, winner1);
            s.PrintArraySteps();
            KillComparator c = new KillComparator(board, winner1, pieces);
            c.PrintArrayKills();
            DistanceComparator d = new DistanceComparator(board,pieces,winner1);
            d.PrintPlayerDistance();
            SquarsComparator sq = new SquarsComparator(positions);
            sq.printArray();
        }
        //isGameFinished();
        return false;
    }

    public void  closePositions(Position [] arr, Position p){
        boolean flag = false;
        arr[0]=new Position(p.getX(), p.getY()+1);
        arr[1]=new Position(p.getX(), p.getY()-1);
        arr[2]=new Position(p.getX()+1, p.getY());
        arr[3]=new Position(p.getX()-1, p.getY());

    }

    public void eat(Position p){
        Position [] arr = new Position[4];
        closePositions(arr, p);
        Position [] tempArr = new Position[4];
        for (int i = 0; i< arr.length; i++){
            Position temp = arr[i];
            if (temp.getX()<=10 && temp.getX()>=0 && temp.getY()>=0 && temp.getY()<=10) {
                if (board[temp.getX()][temp.getY()] != null  && !board[temp.getX()][temp.getY()].getType().equals("♔")) {
                    closePositions(tempArr, temp);
                    if (board[temp.getX()][temp.getY()].getOwner().isPlayerOne() != board[p.getX()][p.getY()].getOwner().isPlayerOne()) {
                        if (tempArr[i].getX() <= 10 && tempArr[i].getX() >= 0 && tempArr[i].getY() >= 0 && tempArr[i].getY() <= 10) {
                            if (board[tempArr[i].getX()][tempArr[i].getY()] != null)
                            {
                                if (board[temp.getX()][temp.getY()].getOwner().isPlayerOne() != board[tempArr[i].getX()][tempArr[i].getY()].getOwner().isPlayerOne()){
                                    board[p.getX()][p.getY()].setKills();
                                    pieces[lp]= new Pawn(board[temp.getX()][temp.getY()]);
                                    lp++;
                                    board[temp.getX()][temp.getY()] = null;

                                }

                            }
                            else if (tempArr[i].getY()==10 && tempArr[i].getX()==10 || tempArr[i].getY()==0 && tempArr[i].getX()==0 || tempArr[i].getY()==10 && tempArr[i].getX()==0 || tempArr[i].getY()==0 && tempArr[i].getX()==10){
                                board[p.getX()][p.getY()].setKills();
                                pieces[lp]= new Pawn(board[temp.getX()][temp.getY()]);
                                lp++;
                                board[temp.getX()][temp.getY()] = null;

                            }
                        }
                        else {
                            board[p.getX()][p.getY()].setKills();
                            pieces[lp]= new Pawn(board[temp.getX()][temp.getY()]);
                            lp++;
                            board[temp.getX()][temp.getY()] = null;}
                        }
                }
            }
        }
    }




    @Override
    public Piece getPieceAtPosition(Position position) {
        return board[position.getX()][position.getY()];
    }

    @Override
    public Player getFirstPlayer() {
        return player1;
    }

    @Override
    public Player getSecondPlayer() {
        return player2;
    }

    @Override
    public boolean isGameFinished() {
        if (kingP.getY()==10 && kingP.getX()==10 || kingP.getY()==0 && kingP.getX()==0 || kingP.getY()==10 && kingP.getX()==0 || kingP.getY()==0 && kingP.getX()==10) {
            player1.addWIn();
            winner1 = true;

            return true;
        }
        else {
            Position [] arr = new Position[4];
            closePositions(arr, kingP);
            boolean flag = true;
            for (int i=0; i< arr.length; i++) {
                Position temp = arr [i];
                if (temp.getX()<=10 && temp.getX()>=0 && temp.getY()>=0 && temp.getY()<=10)
                    if (board[temp.getX()][temp.getY()] == null || board[temp.getX()][temp.getY()].getOwner().isPlayerOne())
                        flag=false;
            }
            if (flag){
                player2.addWIn();
                winner1 = false;
            }
            return flag;
        }
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return player2turn;
    }

    @Override
    public void reset() {
        board= createBoard();
        kingP.setX(5);
        kingP.setY(5);
        player2turn=true;
    }

    @Override
    public void undoLastMove() {
    }

    @Override
    public int getBoardSize() {
        return 11;
    }






}
