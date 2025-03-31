package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    public int helper;
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];



    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[300];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap(0, "/maps/town1.txt");
        loadMap(2, "/maps/lab.txt");
        loadMap(1, "/maps/startRoom.txt");
    }

    public void getTileImage(){

        try {
            //117 different tiles for townmap
            for (int i = 0; i < 117; i++){
                String file;
                String zeros;
                if (i < 10){
                    zeros = "00";
                } else if (i < 100) {
                    zeros = "0";
                } else {
                    zeros = "";
                }
                file = "/tiles/town1REAL_" + zeros + i + ".png";
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(file));
                if (i == 4 || i == 88 || i == 87|| i == 85|| i == 78|| i == 84|| i == 83|| i == 82|| i == 104|| i == 105|| i == 108|| i == 110|| i == 112|| i == 113|| i == 116|| i == 115|| i == 96|| i == 95|| i == 94|| i == 93|| i == 92|| i == 91|| i == 90|| i == 89|| i == 102|| i == 103|| i == 42|| i == 43|| i == 44|| i == 45|| i == 46|| i == 47|| i == 59|| i == 49|| i == 50|| i == 51|| i == 52|| i == 37|| i == 3|| i == 9|| i == 28|| i == 36|| i == 26|| i == 25|| i == 24|| i == 23|| i == 22|| i == 21|| i == 19|| i == 18|| i == 15|| i == 14|| i == 12|| i == 11|| i == 10 )
                {
                    tile[i].collison = true;
                }
            }

            for (int i = 117; i < 156; i++){
                String file;
                String zeros;
                if (i < 126){
                    zeros = "0";
                } else {
                    zeros = "";
                }

                file = "/startRoomTiles/startRoomTiles_" + zeros + (i - 116) + ".png";

                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(file));
                if (i == 117  || i == 149 || i == 150 || i == 151 || i == 152 || i == 153 || i == 154 || i == 155 || i == 142 || i == 141 || i == 140 || i == 139 || i == 138 || i == 121 || i == 122 || i == 123 || i == 124 || i == 125 || i == 119 || i == 120)
                {
                    tile[i].collison = true;
                }

            }

            for (int i = 156; i < 215; i++){
                String file;
                String zeros;
                if (i < 165){
                    zeros = "0";
                } else {
                    zeros = "";
                }

                file = "/labTiles/lab_" + zeros + (i - 155) + ".png";

                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(file));
                if (i == 163 || i == 160 || i == 213 || i == 209 || i == 208 || i == 206 || i == 214 || i == 204 || i == 203 || i == 202 || i == 199 || i == 211 || i == 164 || i == 165 || i == 167 || i == 168 || i == 170 || i == 171 || i == 181 || i == 182 || i == 183 || i == 184 || i == 185 || i == 186 || i == 205 || i == 172 || i == 173 || i == 174 || i == 175 || i == 176 || i == 177 || i == 178 || i == 179 || i == 200 || i == 201 || i == 166)
                {
                    tile[i].collison = true;
                }

            }




        }catch (IOException e){
            e.printStackTrace();
        }




    }

    public void loadMap(int map, String filePath){
        gp.currentMap = map;
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            int col = 0;
            int row = 0;
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    String line = br.readLine();
                    while (col < gp.maxWorldCol) {

                        String numbers[] = line.split(",");
                        if (gp.currentMap == 0 && map == gp.currentMap) {
                            helper = 0;
                        } else
                        if (gp.currentMap == 1 && map == gp.currentMap){
                            helper = 116;
                        } else
                        if (gp.currentMap == 2 && map == gp.currentMap){
                            helper = 155;
                        } else {

                        }
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[map][col][row] = num + helper;
                        col++;
                    }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }



            br.close();
        }catch (Exception e){

        }

    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;


            while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
                int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage( tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
                }


                worldCol++;

                if (worldCol == gp.maxWorldCol){
                    worldCol = 0;
                    worldRow++;
                }
            }

            while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
                int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage( tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
                }


                worldCol++;

                if (worldCol == gp.maxWorldCol){
                    worldCol = 0;
                    worldRow++;
                }
            }
        }
    }
