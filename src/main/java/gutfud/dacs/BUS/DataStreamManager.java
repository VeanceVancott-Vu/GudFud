package gutfud.dacs.BUS;

import gutfud.dacs.DTO.*;

import java.io.*;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DataStreamManager {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    // private ObjectOutputStream oos;
    //  private ObjectInputStream ois;

    // Constructor to allow instantiation
    public DataStreamManager(Socket socket) throws IOException {
        this.socket = socket;
        this.dos = new DataOutputStream(socket.getOutputStream());
        this.dis = new DataInputStream(socket.getInputStream());
    }

    public static DataStreamManager createInstance(Socket socket) throws IOException {
        return new DataStreamManager(socket);
    }

    public synchronized boolean sendLogin(String username, String password) {
        try {
            System.out.println("Sending username: " + username + ": " + password);
            dos.writeUTF(username);
            dos.writeUTF(password);
            dos.flush();
            // Wait for the server's response
            boolean response = dis.readBoolean();
            System.out.println("Login response: " + response);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public synchronized boolean GetSavedRecipeDetails(String username, String recipename, String recipeusername) throws IOException {

        dos.writeUTF(username);
        dos.writeUTF(recipename);
        dos.writeUTF(recipeusername);

        return dis.readBoolean();
    }

    public synchronized User getUser(String Username) throws IOException {
        dos.writeUTF(Username);

        int byteArraySize = dis.readInt();
        byte[] receivedByteArray = new byte[byteArraySize];
        dis.readFully(receivedByteArray);

        int backbyteArraySize = dis.readInt();
        byte[] BackreceivedByteArray = new byte[backbyteArraySize];
        dis.readFully(BackreceivedByteArray);

        String username = dis.readUTF();
        String email = dis.readUTF();
        String title = dis.readUTF();
        String pass = dis.readUTF();
        String phone = dis.readUTF();
        Date dob = Date.valueOf(dis.readUTF());
        int numofrecipe = dis.readInt();

        User user = new User();
        user.setUserName(username);
        user.setPass(pass);
        user.setUserAvatarUrl(receivedByteArray);
        user.setBackground(BackreceivedByteArray);
        user.setEmail(email);
        user.setPhone(phone);
        user.setDateOfBirth(dob);
        user.setTitle(title);
        user.setNumOfRecipe(numofrecipe);

        return user;

    }

    public synchronized void sendAction(String action) throws IOException {
        System.out.println("Sending action: " + action);
        dos.writeUTF(action);
        dos.flush();
    }

    public synchronized boolean SaveCmt(Cmt cmt) throws IOException {
        System.out.println(cmt.getUserName());
        dos.writeUTF(cmt.getUserName());
        dos.writeUTF(cmt.getRecipeName());
        dos.writeUTF(cmt.getContent());
        dos.writeUTF(String.valueOf(cmt.getDateCmt()));
        dos.writeUTF(cmt.getCmtUserName());

        return dis.readBoolean();
    }

    public synchronized boolean AddSavedRecipe(Savedrecipedetails savedrecipedetails) throws IOException {
        dos.writeUTF(savedrecipedetails.getUserName());
        dos.writeUTF(savedrecipedetails.getRecipeName());
        dos.writeUTF(String.valueOf(savedrecipedetails.getDateSaved()));
        dos.writeUTF(savedrecipedetails.getRecipe_UserName());

        System.out.println(savedrecipedetails.toString());


        return dis.readBoolean();
    }

    public synchronized boolean SaveRate(String recipename, String username, int rate, String rateduername, Date daterated) throws IOException {
        dos.writeUTF(recipename);
        dos.writeUTF(username);
        dos.writeInt(rate);
        dos.writeUTF(rateduername);
        dos.writeUTF(String.valueOf(daterated));

        return dis.readBoolean();
    }

    public synchronized boolean ForgotPass(String pass, String email) throws IOException {
        dos.writeUTF(pass);
        dos.writeUTF(email);
        return dis.readBoolean();
    }

    public synchronized ArrayList<Recipe> ViewRecipe() throws IOException {
        ArrayList<Recipe> list = new ArrayList<>();
        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");

                break;
            }
            String RecipeName = dis.readUTF();
            String UserName = dis.readUTF();
            int NumOfSaved = dis.readInt();
            String DateOfPublish = dis.readUTF();
            boolean reported = dis.readBoolean();

            RecipePK recipePK = new RecipePK();
            recipePK.setRecipeName(RecipeName);
            recipePK.setUserName(UserName);

            Recipe recipe = new Recipe(recipePK);
            recipe.setNumberOfSaved(NumOfSaved);
            recipe.setDatePublish(Date.valueOf(DateOfPublish));
            recipe.setReported(reported);

            list.add(recipe);
        }
        return list;
    }

    public synchronized ArrayList<Cmt> ViewCmt(String recipename, String username) throws IOException {

        ArrayList<Cmt> cmtlist = new ArrayList<>();
        dos.writeUTF(recipename);
        dos.writeUTF(username);

        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");
                break;
            }
            String RecipeName = dis.readUTF();
            String UserName = dis.readUTF();
            String Content = dis.readUTF();
            String Datecmt = dis.readUTF();
            String CmtUserName = dis.readUTF();

            CmtPK cmtPK = new CmtPK();
            cmtPK.setUserName(UserName);
            cmtPK.setRecipeName(RecipeName);
            cmtPK.setContent(Content);

            Cmt cmt = new Cmt(cmtPK);
            cmt.setDateCmt(Date.valueOf(Datecmt));
            cmt.setCmtUserName(CmtUserName);

            cmtlist.add(cmt);

        }


        return cmtlist;
    }

    public synchronized ArrayList<Step> ViewStep(String recipename, String username) throws IOException {

        ArrayList<Step> steplist = new ArrayList<>();
        dos.writeUTF(recipename);
        dos.writeUTF(username);

        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");

                break;
            }
            String RecipeName = dis.readUTF();
            String UserName = dis.readUTF();
            String Content = dis.readUTF();
            int Order = dis.readInt();

            StepPK stepPK = new StepPK();
            stepPK.setRecipeName(RecipeName);
            stepPK.setStepOrder(Order);
            stepPK.setUserName(UserName);
            Step step = new Step(stepPK);
            step.setContent(Content);

            steplist.add(step);

        }


        return steplist;
    }

    public synchronized ArrayList<Ingredient> ViewIngre(String recipename, String username) throws IOException {
        ArrayList<Ingredient> ingrelist = new ArrayList<>();
        dos.writeUTF(recipename);
        dos.writeUTF(username);
        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");

                break;
            }
            String RecipeName = dis.readUTF();
            String UserName = dis.readUTF();
            String IngreName = dis.readUTF();
            String IngreQuantity = dis.readUTF();

            IngredientPK ingredientPK = new IngredientPK();
            ingredientPK.setRecipeName(RecipeName);
            ingredientPK.setUserName(UserName);
            ingredientPK.setIngredientName(IngreName);

            Ingredient ingredient = new Ingredient(ingredientPK);
            ingredient.setIngreQuantity(IngreQuantity);


            ingrelist.add(ingredient);
        }


        return ingrelist;
    }

    public synchronized ArrayList<Image> ViewImage(String recipename, String username) throws IOException {
        ArrayList<Image> imglist = new ArrayList<>();
        dos.writeUTF(recipename);
        dos.writeUTF(username);
        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");
                break;
            }
            int byteArraySize = dis.readInt();
            byte[] receivedByteArray = new byte[byteArraySize];
            dis.readFully(receivedByteArray);

            String RecipeName = dis.readUTF();
            String Username = dis.readUTF();
            int ImgNum = dis.readInt();

            ImagePK imagePK = new ImagePK();
            imagePK.setRecipeName(RecipeName);
            imagePK.setUserName(Username);
            imagePK.setImgNumber(ImgNum);
            Image image = new Image(imagePK);
            image.setImgUrl(receivedByteArray);
            imglist.add(image);
        }
        return imglist;

    }

    public synchronized ArrayList<Rate> ViewRate(String recipename, String username) throws IOException {
        ArrayList<Rate> list = new ArrayList<>();

        dos.writeUTF(recipename);
        dos.writeUTF(username);

        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");
                break;
            }
            int Rate = dis.readInt();
            String daterated = dis.readUTF();
            String RatedUserName = dis.readUTF();
            String RecipeName = dis.readUTF();
            String UserName = dis.readUTF();
            gutfud.dacs.DTO.RatePK ratePK = new RatePK();
            ratePK.setRatedUserName(RatedUserName);
            ratePK.setRecipeName(RecipeName);
            ratePK.setUserName(UserName);
            gutfud.dacs.DTO.Rate rate = new Rate(ratePK);
            rate.setRate(Rate);
            rate.setDateRated(Date.valueOf(daterated));

            list.add(rate);


        }
        return list;


    }

    public synchronized ArrayList<Image> ViewRecipeAva() throws IOException {
        ArrayList<Image> list = new ArrayList<>();
        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");
                break;
            }
            int byteArraySize = dis.readInt();
            byte[] receivedByteArray = new byte[byteArraySize];
            dis.readFully(receivedByteArray);


            String RecipeName = dis.readUTF();
            String Username = dis.readUTF();
            int ImgNum = dis.readInt();

            ImagePK imagePK = new ImagePK();
            imagePK.setRecipeName(RecipeName);
            imagePK.setUserName(Username);
            imagePK.setImgNumber(ImgNum);
            Image image = new Image(imagePK);
            image.setImgUrl(receivedByteArray);
            list.add(image);
        }
        return list;
    }

    public synchronized void sendIngre(Ingredient ingredient) throws IOException {
        System.out.println("Sending Ingredient: " + ingredient.getIngredientName() + ": " + ingredient.getIngreQuantity());
        dos.writeUTF(ingredient.getIngredientName());
        dos.writeUTF(ingredient.getIngreQuantity());
        dos.writeUTF(ingredient.getRecipeName());
        dos.writeUTF(ingredient.getUserName());
        dos.flush();
    }

    public synchronized void sendStep(Step step) throws IOException {
        System.out.println("Sending step: " + step.getStepOrder() + ": " + step.getContent());
        System.out.println(step.getRecipeName());
        dos.writeInt(step.getStepOrder());
        dos.writeUTF(step.getContent());
        dos.writeUTF(step.getRecipeName());
        dos.writeUTF(step.getUserName());
        dos.flush();
    }

    public synchronized void sendImage(Image img) throws IOException {
        System.out.println("Sending Image size: " + ": " + img.getImgUrl().length);
        dos.writeInt(img.getImgUrl().length);
        dos.write(img.getImgUrl());

        dos.writeInt(img.getImgNumber());
        dos.writeUTF(img.getRecipeName());
        System.out.println('\t' + img.getRecipeName());
        dos.writeUTF(img.getUserName());
        dos.flush();
    }

    public synchronized boolean sendRecipe(String recipename, String username) throws IOException {
        System.out.println("Sending: " + recipename + " Username: " + username);
        dos.writeUTF(recipename);
        dos.writeUTF(username);
        dos.flush();
        return dis.readBoolean();
    }


    public synchronized boolean sendBytes(byte[] data, String username) throws IOException {
        System.out.println("Sending bytes: " + data.length + " bytes");
        dos.writeInt(data.length); // Send data length
        dos.write(data); // Send data

        dos.writeUTF(username);

        dos.flush();

        return dis.readBoolean();
    }

    public synchronized boolean sendReport(String username, String recipename) throws IOException {
        dos.writeUTF(username);
        dos.writeUTF(recipename);
        return dis.readBoolean();
    }

    public synchronized void close() {
        try {
            if (dos != null) dos.close();
            if (dis != null) dis.close();
            if (socket != null) socket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean RemoveSavedRecipDetail(String username, String recipename, String recipeusername) throws IOException {

        dos.writeUTF(username);
        dos.writeUTF(recipename);
        dos.writeUTF(recipeusername);

        return dis.readBoolean();
    }

    public synchronized boolean sendChangeProfile(String username, String title, String phone, String email, Date dob) throws IOException {

        dos.writeUTF(username);
        dos.writeUTF(title);
        dos.writeUTF(phone);
        dos.writeUTF(email);
        dos.writeUTF(String.valueOf(dob));
        return dis.readBoolean();
    }

    public synchronized ArrayList<Recipe> ViewSavedRecipe(String username) throws IOException {
        dos.writeUTF(username);

        ArrayList<Recipe> list = new ArrayList<>();
        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");

                break;
            }
            String RecipeName = dis.readUTF();
            String UserName = dis.readUTF();
            int NumOfSaved = dis.readInt();
            String DateOfPublish = dis.readUTF();
            boolean reported = dis.readBoolean();

            RecipePK recipePK = new RecipePK();
            recipePK.setRecipeName(RecipeName);
            recipePK.setUserName(UserName);

            Recipe recipe = new Recipe(recipePK);
            recipe.setNumberOfSaved(NumOfSaved);
            recipe.setDatePublish(Date.valueOf(DateOfPublish));
            recipe.setReported(reported);

            list.add(recipe);
        }
        return list;
    }

    public synchronized boolean DelRecipe(String username, String recipename) throws IOException {

        dos.writeUTF(username);
        dos.writeUTF(recipename);

        return dis.readBoolean();
    }

    public synchronized ArrayList<User> ViewUsers() throws IOException {
        ArrayList<User> ArrList = new ArrayList<>();

        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");
                break;
            }

            int byteArraySize = dis.readInt();
            byte[] receivedByteArray = new byte[byteArraySize];
            dis.readFully(receivedByteArray);

            String username = dis.readUTF();
            String email = dis.readUTF();
            String title = dis.readUTF();
            String pass = dis.readUTF();
            String phone = dis.readUTF();
            Date dob = Date.valueOf(dis.readUTF());
            int numofrecipe = dis.readInt();

            User user = new User();
            user.setUserName(username);
            user.setPass(pass);
            user.setUserAvatarUrl(receivedByteArray);
            user.setEmail(email);
            user.setPhone(phone);
            user.setDateOfBirth(dob);
            user.setTitle(title);
            user.setNumOfRecipe(numofrecipe);

            System.out.println(user);
            ArrList.add(user);
        }

        return ArrList;
    }

    public synchronized ArrayList<Messages> ViewMess(String CurrUserName,String WantToTextUserName) throws IOException {
        ArrayList<Messages> ArrList = new ArrayList<>();
        dos.writeUTF(CurrUserName);
        dos.writeUTF(WantToTextUserName);

        while (true) {
            String receivedData = dis.readUTF();
            if (receivedData.equals("end")) {
                System.out.println("Client sent termination message. Closing connection.");
                break;
            }

            String SenderID = dis.readUTF();
            String MessageText = dis.readUTF();
            String TimeStamp = dis.readUTF();

            Messages mess = new Messages();
            mess.setSenderId(SenderID);
            mess.setMessageText(MessageText);
            mess.setTimeStamp(Timestamp.valueOf(TimeStamp));

            ArrList.add(mess);
        }

        return ArrList;
    }

    public synchronized boolean SaveMess(String sendername, String recivername,String  MessText) throws IOException {

        dos.writeUTF(sendername);
        dos.writeUTF(recivername);
        dos.writeUTF(MessText);

        return dis.readBoolean();
    }

    public synchronized void SendMess(String messText) throws IOException {
        BufferedReader  reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter  writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(messText);
    }

}
