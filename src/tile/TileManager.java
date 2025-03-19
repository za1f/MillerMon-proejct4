package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    public TileManager(GamePanel gp){

        this.gp = gp;
        tile = new Tile[117];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/town1.txt");
    }

    public void getTileImage(){

        try {

            for (int i = 0; i < 117; i++){
                String file = "";
                String zeros = "";
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



        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol) {
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
            int tileNum = mapTileNum[worldCol][worldRow];

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
