package com.agilewhisperers.bunnysnaketankmaze.systems;

import com.agilewhisperers.bunnysnaketankmaze.MazeGenerator.Maze;
import com.agilewhisperers.bunnysnaketankmaze.entities.Player;
import com.agilewhisperers.bunnysnaketankmaze.entities.Wall;

import java.util.Random;

/**
 * Method use to creates a batch of object.
 */
public class ObjectFactory {
    public static ObjectFactory single_instance;
    private int[][] data;
    private Maze maze;

    private ObjectFactory() {
        maze = new Maze();
        data = new int[32][62];

    }

    public static ObjectFactory getObject() {

        if (single_instance == null) {
            single_instance = new ObjectFactory();
        }
        return single_instance;
    }

    private void buildMaze() {

        //First maze
        int[][] temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + 4][j + 4] = temp[i][j];
            }
        }

        //Second maze
        temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + 4][j + data[0].length / 2 + 2] = temp[i][j];
            }
        }

        //Third maze
        temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + data.length / 2 + 2][j + 4] = temp[i][j];
            }
        }

        //Fourth maze
        temp = maze.getMazeAsData(8, 3);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                data[i + data.length / 2 + 2][j + data[0].length / 2 + 2] = temp[i][j];
            }
        }

        //Outside wall
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (i == 0 || j == 0 || i == data.length - 1 || j == data[0].length - 1) {
                    data[i][j] = 1;
                }
            }
        }

        //Add gate
        data[4][data[0].length / 4] = 0;
        data[4][data[0].length / 4 + 1] = 0;
        data[4][data[0].length / 4 + 2] = 0;
        data[4][data[0].length * 3 / 4] = 0;
        data[4][data[0].length * 3 / 4 - 2] = 0;
        data[4][data[0].length * 3 / 4 - 1] = 0;
        data[data.length / 2 - 3][data[0].length / 4] = 0;
        data[data.length / 2 - 3][data[0].length / 4 + 1] = 0;
        data[data.length / 2 - 3][data[0].length / 4 + 2] = 0;
        data[data.length / 2 - 3][data[0].length * 3 / 4] = 0;
        data[data.length / 2 - 3][data[0].length * 3 / 4 - 2] = 0;
        data[data.length / 2 - 3][data[0].length * 3 / 4 - 1] = 0;
        data[data.length - 5][data[0].length / 4] = 0;
        data[data.length - 5][data[0].length / 4 + 1] = 0;
        data[data.length - 5][data[0].length / 4 + 2] = 0;
        data[data.length - 5][data[0].length * 3 / 4] = 0;
        data[data.length - 5][data[0].length * 3 / 4 - 2] = 0;
        data[data.length - 5][data[0].length * 3 / 4 - 1] = 0;
        data[data.length / 2 + 2][data[0].length / 4] = 0;
        data[data.length / 2 + 2][data[0].length / 4 + 1] = 0;
        data[data.length / 2 + 2][data[0].length / 4 + 2] = 0;
        data[data.length / 2 + 2][data[0].length * 3 / 4] = 0;
        data[data.length / 2 + 2][data[0].length * 3 / 4 - 2] = 0;
        data[data.length / 2 + 2][data[0].length * 3 / 4 - 1] = 0;

        data[data.length / 4][4] = 0;
        data[data.length / 4 + 1][4] = 0;
        data[data.length * 3 / 4 - 1][4] = 0;
        data[data.length * 3 / 4 - 2][4] = 0;
        data[data.length / 4][data[0].length / 2 - 3] = 0;
        data[data.length / 4 + 1][data[0].length / 2 - 3] = 0;
        data[data.length * 3 / 4 - 1][data[0].length / 2 - 3] = 0;
        data[data.length * 3 / 4 - 2][data[0].length / 2 - 3] = 0;
        data[data.length / 4][data[0].length / 2 + 2] = 0;
        data[data.length / 4 + 1][data[0].length / 2 + 2] = 0;
        data[data.length * 3 / 4 - 1][data[0].length / 2 + 2] = 0;
        data[data.length * 3 / 4 - 2][data[0].length / 2 + 2] = 0;
        data[data.length / 4][data[0].length - 5] = 0;
        data[data.length / 4 + 1][data[0].length - 5] = 0;
        data[data.length * 3 / 4 - 1][data[0].length - 5] = 0;
        data[data.length * 3 / 4 - 2][data[0].length - 5] = 0;


        //cleaning
       /* for(int i=1; i<data[0].length-1;i++){
            data[3][i]=0;
            data[2][i]=0;
            data[1][i]=0;
            data[data.length-4][i]=0;
            data[data.length-3][i]=0;
            data[data.length-2][i]=0;
            data[data.length/2+1][i]=0;
            data[data.length/2-1][i]=0;
            data[data.length/2][i]=0;
        }
        for(int j=1;j<data.length-1;j++){
            data[j][1]=0;
            data[j][2]=0;
            data[j][3]=0;
            data[j][data[0].length-4]=0;
            data[j][data[0].length-3]=0;
            data[j][data[0].length-2]=0;
            data[j][data[0].length/2+1]=0;
            data[j][data[0].length/2-1]=0;
            data[j][data[0].length/2]=0;
        }*/

    }

    /**
     * Call at the start of the game. Use to create all gameObjects
     */
    public void start() {
        //Bullet bullet=new Bullet();
        buildMaze();
        spawnPlayer();
        spawnWall();


    }

    public void spawnPlayer() {
        Random random = new Random();
        int x = 0;
        int y = 0;
        do {
            x = random.nextInt(data[0].length);
            y = random.nextInt(data.length);
        } while (data[y][x] == 1);
        Player player = new Player(x, y);
    }

    public void spawnWall() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    new Wall(j, i);
                }
            }
        }
    }


}
