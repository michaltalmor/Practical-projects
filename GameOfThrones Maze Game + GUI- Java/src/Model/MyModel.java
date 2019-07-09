package Model;

import Client.Client;
import Client.IClientStrategy;
import IO.MyDecompressorInputStream;
import Server.*;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aviadjo on 6/14/2017.
 */
public class MyModel extends Observable implements IModel {

    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private Maze myMaze;
    private MyMazeGenerator myMazeGenerator;
    private Server mazeGeneratingServer;
    private Server mazeSolvingServer;

    private int characterPositionRow;
    private int characterPositionColumn;

    private int targetPositionRow;
    private int targetPositionColumn;

    private ArrayList<AState> mazeSolutionSteps;
    private boolean showSolution;




    public MyModel() {
        //Raise the servers
        mazeGeneratingServer = new Server(5400,10000,new ServerStrategyGenerateMaze());
        mazeSolvingServer=new Server(5401,10000,new ServerStrategySolveSearchProblem());
        showSolution=false;
    }

    public void startServers() {
        mazeGeneratingServer.start();
        mazeSolvingServer.start();
    }

    public void stopServers() {
        mazeGeneratingServer.stop();
        mazeSolvingServer.stop();

    }
    private int[][]maze;


    @Override
    public void generateMaze(int width, int height) {
        //Generate maze
        threadPool.execute(() -> {
            //generateRandomMaze(width,height);
            CommunicateWithServer_MazeGenerating(width,height);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showSolution=false;
            setChanged();
            notifyObservers();
        });
    }

    public void solveMaze(ActionEvent actionEvent) {
        //Generate maze
        threadPool.execute(() -> {
            CommunicateWithServer_SolveSearchProblem();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showSolution=actionEvent.isConsumed();
            setChanged();
            notifyObservers();
        });
    }

    private int[][] generateRandomMaze(int width, int height) {
        /*Random rand = new Random();
        maze = new int[width][height];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = Math.abs(rand.nextInt() % 2);
            }
        }
        return maze;*/
        myMazeGenerator = new MyMazeGenerator();
        myMaze = myMazeGenerator.generate(width, height); //check the sizes
        maze = myMaze.getMazeArray();
        characterPositionRow=myMaze.getStartPosition().getRowIndex();
        characterPositionColumn=myMaze.getStartPosition().getColumnIndex();
        targetPositionRow=myMaze.getGoalPosition().getRowIndex();
        targetPositionColumn=myMaze.getGoalPosition().getColumnIndex();
        return maze;
    }

    @Override
    public int[][] getMaze() {
        return maze;
    }

    @Override
    public void moveCharacter(KeyCode movement) {
        switch (movement) {
            case UP:
                if(characterPositionRow-1 >=0 &&
                        maze[characterPositionRow-1][characterPositionColumn]==0 ) {
                    characterPositionRow--;
                }
                break;
            case DOWN:
                if(characterPositionRow+1 < maze.length &&
                        maze[characterPositionRow+1][characterPositionColumn]==0 ) {
                    characterPositionRow++;
                }
                break;
            case RIGHT:
                if(characterPositionColumn+1 < maze[0].length &&
                        maze[characterPositionRow][characterPositionColumn+1]==0 ) {
                    characterPositionColumn++;
                }
                break;
            case LEFT:
                if(characterPositionColumn-1 >= 0 &&
                        maze[characterPositionRow][characterPositionColumn-1]==0 ) {
                    characterPositionColumn--;
                }
                break;

            case NUMPAD8:
                if(characterPositionRow-1 >=0 &&
                        maze[characterPositionRow-1][characterPositionColumn]==0 ) {
                    characterPositionRow--;
                }
                break;
            case NUMPAD2:
                if(characterPositionRow+1 < maze.length &&
                        maze[characterPositionRow+1][characterPositionColumn]==0 ) {
                    characterPositionRow++;
                }
                break;
            case NUMPAD6:
                if(characterPositionColumn+1 < maze[0].length &&
                        maze[characterPositionRow][characterPositionColumn+1]==0 ) {
                    characterPositionColumn++;
                }
                break;
            case NUMPAD4:
                if(characterPositionColumn-1 >= 0 &&
                        maze[characterPositionRow][characterPositionColumn-1]==0 ) {
                    characterPositionColumn--;
                }
                break;

            case NUMPAD7:
                if(characterPositionRow-1 >=0 && characterPositionColumn-1 >= 0 &&
                        (maze[characterPositionRow-1][characterPositionColumn]==0 &&
                        maze[characterPositionRow-1][characterPositionColumn-1]==0) ||
                        (maze[characterPositionRow][characterPositionColumn-1]==0 &&
                                maze[characterPositionRow-1][characterPositionColumn-1]==0)){


                            characterPositionRow--;
                            characterPositionColumn--;
                }

                break;

            case NUMPAD1:
                if(characterPositionRow+1< maze.length && characterPositionColumn-1 >= 0 &&
                        (maze[characterPositionRow+1][characterPositionColumn]==0 &&
                                maze[characterPositionRow+1][characterPositionColumn-1]==0) ||
                        (maze[characterPositionRow][characterPositionColumn-1]==0 &&
                                maze[characterPositionRow+1][characterPositionColumn-1]==0)) {
                    characterPositionRow++;
                    characterPositionColumn--;
                }
                break;
            case NUMPAD9:
                if(characterPositionRow-1>=0 && characterPositionColumn+1 < maze[0].length &&
                        (maze[characterPositionRow-1][characterPositionColumn]==0 &&
                                maze[characterPositionRow-1][characterPositionColumn+1]==0) ||
                        (maze[characterPositionRow][characterPositionColumn+1]==0 &&
                                maze[characterPositionRow-1][characterPositionColumn+1]==0)) {
                    characterPositionRow--;
                    characterPositionColumn++;
                }
                break;
            case NUMPAD3:
                if(characterPositionRow+1<maze.length && characterPositionColumn+1 < maze[0].length &&
                        (maze[characterPositionRow+1][characterPositionColumn]==0 &&
                                maze[characterPositionRow+1][characterPositionColumn+1]==0) ||
                        (maze[characterPositionRow][characterPositionColumn+1]==0 &&
                                maze[characterPositionRow+1][characterPositionColumn+1]==0)) {
                    characterPositionRow++;
                    characterPositionColumn++;
                }
                break;

        }
        setChanged();
        notifyObservers();
    }

    @Override
    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    @Override
    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }
    @Override
    public int getTargetPositionRow() {

        return targetPositionRow;
    }
    @Override
    public int getTargetPositionColumn()
    {
        return targetPositionColumn;
    }
    public ArrayList getMazeSolutionSteps(){
        return mazeSolutionSteps;
    }

    public boolean getShowSolution(){
        return showSolution;
    }

    @Override
    public void Save(File file) {
        try {
            ObjectOutputStream fileWriter = null;

            fileWriter = new ObjectOutputStream(new FileOutputStream(file));
            Object []savedMaze=new Object[3];
            savedMaze[0]=myMaze;
            savedMaze[1]=characterPositionRow;
            // savedMaze[1]=70;
            savedMaze[2]=characterPositionColumn;
            //savedMaze[2]=5;
            fileWriter.writeObject(savedMaze);
            fileWriter.close();
        } catch (IOException ex) {
            //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);

        }


    }

    @Override
    public void Load(File file) {
        try {
            ObjectInputStream fileReader = null;

            fileReader = new ObjectInputStream(new FileInputStream(file));
            Object []savedMaze=new Object[3];
            savedMaze= (Object[])( fileReader.readObject());
            fileReader.close();
            myMaze=(Maze) savedMaze[0];
            maze=myMaze.getMazeArray();
            characterPositionRow=(int)(savedMaze[1]);
            characterPositionColumn=(int)(savedMaze[2]);;
            targetPositionRow=myMaze.getGoalPosition().getRowIndex();
            targetPositionColumn=myMaze.getGoalPosition().getColumnIndex();
//maybe move here the finish=true
            showSolution=false;
            setChanged();
            notifyObservers();



        } catch (IOException ex) {
            //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private  void CommunicateWithServer_MazeGenerating(int width, int height) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {

                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);

                        toServer.flush();
                        int[] mazeDimensions = new int[]{width, height};
                        toServer.writeObject(mazeDimensions); //send maze dimensions to server
                        toServer.flush();
                        byte[] compressedMaze = (byte[]) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        byte[] decompressedMaze = new byte[12 + (width*height) /*CHANGE SIZE ACCORDING TO YOU MAZE SIZE*/]; //allocating byte[] for the decompressed maze -
                        is.read(decompressedMaze); //Fill decompressedMaze with bytes
                        myMaze = new Maze(decompressedMaze);

                        characterPositionRow=myMaze.getStartPosition().getRowIndex();
                        characterPositionColumn=myMaze.getStartPosition().getColumnIndex();
                        targetPositionRow=myMaze.getGoalPosition().getRowIndex();
                        targetPositionColumn=myMaze.getGoalPosition().getColumnIndex();
                        maze=myMaze.getMazeArray();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    private void CommunicateWithServer_SolveSearchProblem( ) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);

                        toServer.flush();
                        //Maze actionEventMaze = new Maze(new byte[10]);

                        toServer.writeObject(myMaze); //send maze to server
                        toServer.flush();
                        Solution mazeSolution = (Solution) fromServer.readObject(); //read generated maze (compressed with MyCompressor) from server

                       mazeSolutionSteps = mazeSolution.getSolutionPath();
                       // for (int i = 0; i < mazeSolutionSteps.size(); i++) {
                        //    System.out.println(String.format("%s. %s", i, mazeSolutionSteps.get(i).toString()));
                        //}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close() {
        try {
            threadPool.shutdown();
            threadPool.awaitTermination(3, TimeUnit.SECONDS);
            stopServers();
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
