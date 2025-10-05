package gutfud.dacs.BUS;

import gutfud.dacs.DTO.Image;

import java.io.*;
import java.util.ArrayList;

public class AddImage {
    private DataStreamManager manager;
    private ArrayList<Image> imgList;

    public AddImage(ArrayList<Image> imgList,DataStreamManager manager) {
        this.manager = manager;
        this.imgList = imgList;
    }

    public void sendImage() {
        for(Image img : imgList)
        try {
            manager.sendAction("Add-image");
            manager.sendImage(img);
            System.out.println("Image sent: " + " \n"+img.getImgNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
