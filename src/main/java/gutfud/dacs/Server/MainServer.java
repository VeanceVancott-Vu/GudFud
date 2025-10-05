package gutfud.dacs.Server;

import gutfud.dacs.DTO.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainServer {
    private static List<Socket> connectedClients = new ArrayList<>();  // List to store connected Sockets

    public static void handleClient(Socket socket) {
        try (DataInputStream svdis = new DataInputStream(socket.getInputStream());
             DataOutputStream svdos = new DataOutputStream(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        ) {

            while (!socket.isClosed()) {
                String action = svdis.readUTF();
                System.out.println("Action received: " + action);

                switch (action) {
//                    case"Get-user":
//                    {
//                        handleGetUser(svdis,svdos);
//                        break;
//                    }
                    case"Change-background":
                        handleChangeBackGround(svdis,svdos);
                        break;
                    case "Send-mess": {
                        handleSendMess(svdis, svdos, reader, writer);
                        break;
                    }
                    case "Save-mess": {
                        handleSaveMess(svdis, svdos);
                        break;
                    }
                    case "View-mess": {
                        handleViewMess(svdis, svdos);
                        break;
                    }
                    case "View-users": {
                        handleViewUsers(svdis, svdos);
                        break;
                    }
                    case "Delete-recipe": {
                        handleDelRecipe(svdis, svdos);
                        break;
                    }
                    case "View-savedrecipe": {
                        handleViewSavedRecipe(svdis, svdos);
                        break;
                    }
                    case "Change-profile": {
                        handleChangeProfile(svdis, svdos);
                        break;
                    }
                    case "Change-avatar": {
                        handleChangeAvatar(svdis, svdos);
                        break;
                    }
                    case "Report": {
                        handleReport(svdis, svdos);
                        break;
                    }
                    case "Remove-savedrecipdetail": {
                        handleRemoveSavedRecipeDetails(svdis, svdos);
                        break;
                    }
                    case "Get-savedrecipedetails": {
                        handleGetSavedRecipeDetails(svdis, svdos);
                        break;
                    }
                    case "Add-SavedRecipe": {
                        handleAddSavedRecipe(svdis, svdos);
                        break;
                    }
                    case "Get-user": {
                        handleGetUser(svdis, svdos);
                        break;
                    }
                    case "Save-cmt":
                        handleSaveCmt(svdis, svdos);
                        break;
                    case "Save-rate":
                        handleSaveRate(svdis, svdos);
                        break;
                    case "View-cmt":
                        handleViewCmt(svdis, svdos);
                        break;
                    case "View-step":
                        handleViewStep(svdis, svdos);
                        break;
                    case "View-ingredient":
                        handleViewIngredient(svdis, svdos);
                        break;
                    case "View-rate":
                        handleViewRate(svdis, svdos);
                        break;
                    case "View-image":
                        handleViewImage(svdis, svdos);
                        break;
                    case "View-recipeAva":
                        handleViewRecipeAva(svdis, svdos);
                        break;
                    case "View-recipe":
                        handleViewRecipe(svdis, svdos);
                        break;
                    case "Add-step":
                        handleAddstep(svdis, svdos);
                        break;
                    case "Add-recipe":
                        handleAddrecipe(svdis, svdos);
                        break;
                    case "Add-ingredient":
                        handleAddingredient(svdis, svdos);
                        break;
                    case "Login":
                        handleLogin(svdis, svdos);
                        break;
                    case "Register":
                        handleRegister(svdis, svdos);
                        break;
                    case "Forgot-pass":
                        handleForgotPass(svdis, svdos);
                        break;
                    case "Add-image":
                        handleSaveImage(svdis);
                        break;
                    default:
                        System.out.println("Unknown action: " + action);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void handleChangeBackGround(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        int imageLength = svdis.readInt();
        System.out.println("Image length: " + imageLength);

        // Read the image data
        byte[] receivedByteArray = new byte[imageLength];
        int totalBytesRead = 0;
        while (totalBytesRead < imageLength) {
            int bytesRead = svdis.read(receivedByteArray, totalBytesRead, imageLength - totalBytesRead);
            if (bytesRead == -1) {
                throw new IOException("End of stream reached before reading the image completely");
            }
            totalBytesRead += bytesRead;
        }
        String username = svdis.readUTF();
        svdos.writeBoolean( new SvChangeBackGround(username,receivedByteArray).ChangeBackGround());
    }

    private static void handleSendMess(DataInputStream svdis, DataOutputStream svdos, BufferedReader reader, PrintWriter writer) throws IOException {
        String message = svdis.readUTF();
        while ((message = reader.readLine()) != null) {
            System.out.println("Client message: " + message);
            // Broadcast message to all connected clients
            broadcastMessage(message);
        }

}

    private static void broadcastMessage(String message) {
        for (Socket clientSocket : connectedClients) {
            try (PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {  // Auto-close the writer
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
                // Optionally handle exceptions for individual clients (e.g., remove them from the list)
                connectedClients.remove(clientSocket);  // Remove problematic client on exception
            }
        }
    }


    private static void handleSaveMess(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String Sender = svdis.readUTF();
        String Reciver = svdis.readUTF();
        String messtext = svdis.readUTF();

        svdos.writeBoolean(new SvSaveMess(Sender,Reciver,messtext).SaveMess());

    }

    private static void handleViewMess(DataInputStream svdis, DataOutputStream svdos) throws IOException {

        String CurrUserName = svdis.readUTF();
        String WantToTextUserName = svdis.readUTF();

        ArrayList<Messages> arrlist = new ArrayList<>();
        arrlist = new SvViewMess(CurrUserName,WantToTextUserName).ViewMess();
        for(Messages mess: arrlist)
        {
            System.out.println(mess.getSenderId()+ "  " +mess.getMessageText());
            svdos.writeUTF("Continue");
            svdos.writeUTF(mess.getSenderId());
            svdos.writeUTF(mess.getMessageText());
            svdos.writeUTF(String.valueOf(mess.getTimeStamp()));

        }
        svdos.writeUTF("end");
    }

    private static void handleDelRecipe(DataInputStream svdis, DataOutputStream svdos) throws IOException{
        String username = svdis.readUTF();
        String recipename = svdis.readUTF();

        svdos.writeBoolean( new SvDelRecipe().SvDelRecipe(username,recipename));
    }

    private static void handleChangeProfile(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF();
        String title = svdis.readUTF();
        String phone = svdis.readUTF();
        String email = svdis.readUTF();
        String dob = svdis.readUTF();

        svdos.writeBoolean( new SvChangeProfile(username,title,phone,email,dob).ChangeProfile());
    }

    private static void handleChangeAvatar(DataInputStream svdis, DataOutputStream svdos)throws IOException {
        int imageLength = svdis.readInt();
        System.out.println("Image length: " + imageLength);

        // Read the image data
        byte[] receivedByteArray = new byte[imageLength];
        int totalBytesRead = 0;
        while (totalBytesRead < imageLength) {
            int bytesRead = svdis.read(receivedByteArray, totalBytesRead, imageLength - totalBytesRead);
            if (bytesRead == -1) {
                throw new IOException("End of stream reached before reading the image completely");
            }
            totalBytesRead += bytesRead;
        }
        String username = svdis.readUTF();
        svdos.writeBoolean( new SvChangeAvatar(username,receivedByteArray).ChangeAvatar());

    }

    private static void handleReport(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF();
        String recipename = svdis.readUTF();

        svdos.writeBoolean( new SvReport(username,recipename).report());
    }

    private static void handleRemoveSavedRecipeDetails(DataInputStream svdis, DataOutputStream svdos) throws IOException {

        String username = svdis.readUTF();
        String recipename = svdis.readUTF();
        String recipeusername=svdis.readUTF();

        svdos.writeBoolean(new SvRemoveSavedRecipeDetails().RemoveSavedRecipeDetails(username,recipename,recipeusername));
    }

    private static void handleGetSavedRecipeDetails(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF();
        String recipename = svdis.readUTF();
        String recipeusername=svdis.readUTF();

       svdos.writeBoolean( new SvGetSavedRecipeDetails().GetSavedRecipeDetails(username,recipename,recipeusername));
    }

    private static void handleAddSavedRecipe(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String UserName = svdis.readUTF();
        String RecipeName = svdis.readUTF();
        String date = svdis.readUTF();
        String Recipe_UserName = svdis.readUTF();

        SavedrecipedetailsPK savedrecipedetailsPK = new SavedrecipedetailsPK();
        savedrecipedetailsPK.setRecipe_UserName(Recipe_UserName);
        savedrecipedetailsPK.setRecipeName(RecipeName);
        savedrecipedetailsPK.setUserName(UserName);
        Savedrecipedetails savedrecipedetails = new Savedrecipedetails(savedrecipedetailsPK);
        savedrecipedetails.setDateSaved(Date.valueOf(date));

        System.out.println(savedrecipedetails.toString());
       boolean valid = new SvAddSavedRecipe(savedrecipedetails).SavedRecipe();

        svdos.writeBoolean(valid);


    }
    private static void handleViewUsers(DataInputStream svdis, DataOutputStream svdos) throws IOException {

        ArrayList<User> arrlist = new ArrayList<>();
        arrlist = new SvGetUser().getuserlist();
        for(User user: arrlist)
        {
            System.out.println(user.toString());
            svdos.writeUTF("Continue");

            if(user.getUserAvatarUrl() == null)
            {
                File file = new File("src/main/java/Images/AvatarDefault.jpg");
                byte[] fileBytes = new byte[(int) file.length()];
                fileBytes  = Files.readAllBytes(Paths.get(file.getPath()));
                user.setUserAvatarUrl(fileBytes);
            }

            svdos.writeInt(user.getUserAvatarUrl().length);
            svdos.write(user.getUserAvatarUrl());


            svdos.writeUTF(user.getUserName());
            svdos.writeUTF(user.getEmail());
            svdos.writeUTF(user.getTitle());
            svdos.writeUTF(user.getPass());
            svdos.writeUTF(user.getPhone());
            svdos.writeUTF(String.valueOf(user.getDateOfBirth()));
            svdos.writeInt(user.getNumOfRecipe());

            System.out.println(user);


        }
        svdos.writeUTF("end");
    }

    private static void handleGetUser(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF();


        User user = new SvGetUser(username).getuser();

        if(user.getUserAvatarUrl() == null)
        {
            File file = new File("src/main/java/Images/AvatarDefault.jpg");
            byte[] fileBytes = new byte[(int) file.length()];
            fileBytes  = Files.readAllBytes(Paths.get(file.getPath()));
            user.setUserAvatarUrl(fileBytes);
        }
        if(user.getBackground() == null)
        {
            File file = new File("src/main/java/Images/food-cooking-background-on-white-table-2JDWXK5.jpg");
            byte[] fileBytes = new byte[(int) file.length()];
            fileBytes  = Files.readAllBytes(Paths.get(file.getPath()));
            user.setBackground(fileBytes);
        }

        svdos.writeInt(user.getUserAvatarUrl().length);
        svdos.write(user.getUserAvatarUrl());


        svdos.writeInt(user.getBackground().length);
        svdos.write(user.getBackground());

        svdos.writeUTF(user.getUserName());
        svdos.writeUTF(user.getEmail());
        svdos.writeUTF(user.getTitle());
        svdos.writeUTF(user.getPass());
        svdos.writeUTF(user.getPhone());
        svdos.writeUTF(String.valueOf(user.getDateOfBirth()));
        svdos.writeInt(user.getNumOfRecipe());




    }


        private static void handleSaveCmt(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF() ;
        String recipename = svdis.readUTF();
        String content = svdis.readUTF();
        String datecmt = svdis.readUTF();
        String cmtusername = svdis.readUTF();

        CmtPK cmtPK = new CmtPK();
        cmtPK.setContent(content);
        cmtPK.setRecipeName(recipename);
        cmtPK.setUserName(username);
        Cmt cmt = new Cmt(cmtPK);
        cmt.setDateCmt(Date.valueOf(datecmt));
        cmt.setCmtUserName(cmtusername);

        svdos.writeBoolean(new SvAddCmt(cmt).AddCmt());
    }

    private static void handleSaveRate(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String recipename =  svdis.readUTF();
        String username = svdis.readUTF();
        int rate = svdis.readInt();
        String rateusername = svdis.readUTF();
        String datecmt = svdis.readUTF();
        svdos.writeBoolean(new SvSaveRate(recipename,username,rate,rateusername,datecmt).SaveRate());
    }

    private static void handleViewCmt(DataInputStream svdis,DataOutputStream svdos)throws IOException
    {

        String recipename = svdis.readUTF();
        String username = svdis.readUTF();
        ArrayList<Cmt> list;
        list = new SvViewCmt(recipename,username).ViewCmt();
        for(Cmt cmt: list)
        {
            System.out.println(cmt.toString());
            svdos.writeUTF("Continue");

            svdos.writeUTF(cmt.getRecipeName());
            svdos.writeUTF(cmt.getUserName());
            svdos.writeUTF(cmt.getContent());
            svdos.writeUTF(String.valueOf(cmt.getDateCmt()));
            svdos.writeUTF(cmt.getCmtUserName());

        }
        svdos.writeUTF("end");


    }


    private static void handleViewStep(DataInputStream svdis,DataOutputStream svdos)throws IOException
    {
        String recipename = svdis.readUTF();
        String username = svdis.readUTF();
        ArrayList<Step> list;
        list = new SvViewStep(recipename,username).ViewStep();
        for(Step step: list)
        {
            svdos.writeUTF("Continue");
            svdos.writeUTF(step.getRecipeName());
            svdos.writeUTF(step.getUserName());
            svdos.writeUTF(step.getContent());
            svdos.writeInt(step.getStepOrder());

        }
        svdos.writeUTF("end");


    }


    private static void handleViewIngredient(DataInputStream svdis,DataOutputStream svdos)throws IOException
    {
        String recipename = svdis.readUTF();
        String username = svdis.readUTF();
        ArrayList<Ingredient> list;
        list = new SvViewIngredient(recipename,username).ViewIngre();
        for(Ingredient ingredient: list)
        {
            svdos.writeUTF("Continue");
            svdos.writeUTF(ingredient.getRecipeName());
            svdos.writeUTF(ingredient.getUserName());
            svdos.writeUTF(ingredient.getIngredientName());
            svdos.writeUTF(ingredient.getIngreQuantity());

        }
        svdos.writeUTF("end");


    }
    private static void handleViewRate(DataInputStream svdis,DataOutputStream svdos)throws IOException
    {
        String recipename = svdis.readUTF();
        String username = svdis.readUTF();

        ArrayList<Rate> list;
        list = new SvViewRate(recipename,username).ViewRate();
        for(Rate rate: list)

        {
            System.out.println(rate.toString());
            svdos.writeUTF("Continue");
            svdos.writeInt(rate.getRate());
            svdos.writeUTF(String.valueOf(rate.getDateRated()));
            svdos.writeUTF(rate.getRatedUserName());
            svdos.writeUTF(rate.getRecipeName());
            svdos.writeUTF(rate.getUserName());

        }
        svdos.writeUTF("end");

    }
    private static void handleViewImage(DataInputStream svdis,DataOutputStream svdos)throws IOException
    {
        String recipename = svdis.readUTF();
        String username = svdis.readUTF();
        ArrayList<Image> imglist = new SvViewImage(recipename,username).GetImage();
        for (Image img : imglist) {
            svdos.writeUTF("Continue");
            svdos.writeInt(img.getImgUrl().length);
            svdos.write(img.getImgUrl());
            svdos.writeUTF(img.getRecipeName());
            svdos.writeUTF(img.getUserName());
            svdos.writeInt(img.getImgNumber());
            System.out.println(img);
        }
        svdos.writeUTF("end");
    }
    private static void handleViewSavedRecipe(DataInputStream svdis, DataOutputStream svdos) throws IOException
    {
        String username = svdis.readUTF();
        ArrayList<Recipe> recipeList = new SvViewRecipe().ViewSavedRecipe(username);
        for(Recipe recipe:recipeList)
        {
            svdos.writeUTF("Continue");
            svdos.writeUTF(recipe.getRecipeName());
            svdos.writeUTF(recipe.getUserName());
            if(recipe.getNumberOfSaved() == null)
            {
                recipe.setNumberOfSaved(0);
            }
            svdos.writeInt(recipe.getNumberOfSaved());
            if(recipe.getDatePublish() == null)
            {
                recipe.setDatePublish(Date.valueOf("2024-05-19"));
            }
            svdos.writeUTF(String.valueOf(recipe.getDatePublish()));
            svdos.writeBoolean(recipe.getReported());
            System.out.println(recipe);
        }
        svdos.writeUTF("end");


    }
    private static void handleViewRecipe(DataInputStream svdis, DataOutputStream svdos) throws IOException
    {
        ArrayList<Recipe> recipeList = new SvViewRecipe().ViewRecipe();
        for(Recipe recipe:recipeList)
        {
            svdos.writeUTF("Continue");
            svdos.writeUTF(recipe.getRecipeName());
            svdos.writeUTF(recipe.getUserName());
            if(recipe.getNumberOfSaved() == null)
            {
                recipe.setNumberOfSaved(0);
            }
            svdos.writeInt(recipe.getNumberOfSaved());
            if(recipe.getDatePublish() == null)
            {
                recipe.setDatePublish(Date.valueOf("2024-05-19"));
            }
            svdos.writeUTF(String.valueOf(recipe.getDatePublish()));
            svdos.writeBoolean(recipe.getReported());
            System.out.println(recipe);
        }
        svdos.writeUTF("end");


    }
    private static void handleViewRecipeAva(DataInputStream svdis, DataOutputStream svdos) throws IOException {

        ArrayList<Image> imgList = new SvViewRecipe().ViewRecipeAva();
        for (Image img : imgList) {
            svdos.writeUTF("Continue");
            svdos.writeInt(img.getImgUrl().length);
            svdos.write(img.getImgUrl());
            svdos.writeUTF(img.getRecipeName());
            svdos.writeUTF(img.getUserName());
            svdos.writeInt(img.getImgNumber());
            System.out.println(img + "   " + img.getImgNumber());
        }
        svdos.writeUTF("end");
    }
    private static void handleLogin(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF();
        String password = svdis.readUTF();
        System.out.println("Received login credentials: " + username + " / " + password);
        boolean loginSuccess = new SvLogin(username, password).CheckLogin();
        svdos.writeBoolean(loginSuccess);
        svdos.flush();
    }
    private static void handleAddrecipe( DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String RecipeName = svdis.readUTF();
        String UserName = svdis.readUTF();
        svdos.writeBoolean(  new SvAddRecipe(RecipeName,UserName).AddRecipe());
    }
    private static void handleAddingredient( DataInputStream svdis, DataOutputStream svdos) throws IOException, ClassNotFoundException {

        String IngreName = svdis.readUTF();
        String IngreQuantity = svdis.readUTF();
        String RecipeName = svdis.readUTF();
        String UserName = svdis.readUTF();
        IngredientPK ingredientPK = new IngredientPK();
        ingredientPK.setRecipeName(RecipeName);
        ingredientPK.setUserName(UserName);
        ingredientPK.setIngredientName(IngreName);
        Ingredient ingredient = new Ingredient(ingredientPK);
        ingredient.setIngreQuantity(IngreQuantity);

        System.out.println("Ingredient: "+IngreName+" Quantity: "+IngreQuantity);
        new SvAddIngredient(ingredient).AddIngre();
    }
    private static void handleAddstep( DataInputStream svdis, DataOutputStream svdos) throws IOException, ClassNotFoundException {

        int StepOrder = svdis.readInt();
        String StepContent  = svdis.readUTF();
        String RecipeName = svdis.readUTF();
        String UserName = svdis.readUTF();

        StepPK stepPK = new StepPK();
        stepPK.setRecipeName(RecipeName);
        stepPK.setUserName(UserName);
        stepPK.setStepOrder(StepOrder);

        Step step = new Step(stepPK);
        step.setContent(StepContent);

        System.out.println("RecipeName "+RecipeName+"/ "+step.getRecipeName());
        System.out.println("Step: "+StepOrder+": "+StepContent);
        new SvAddStep(step).AddStep();
    }

    private static void handleRegister(DataInputStream svdis, DataOutputStream svdos) throws IOException {
        String username = svdis.readUTF();
        String password = svdis.readUTF();
        String phone = svdis.readUTF();
        Date dob = Date.valueOf(svdis.readUTF());
        String email = svdis.readUTF();
        String title = svdis.readUTF();
        int numOfRecipe = svdis.readInt();

        System.out.println("Received registration data: " + username + ", " + email);

        User user = new User();
        user.setUserName(username);
        user.setPass(password);
        user.setPhone(phone);
        user.setDateOfBirth(dob);
        user.setUserAvatarUrl(null);
        user.setEmail(email);
        user.setTitle(title);
        user.setNumOfRecipe(0);

        boolean registerSuccess = new SvRegister().Register(user);
        svdos.writeBoolean(registerSuccess);
        svdos.flush();
    }

    private static void handleForgotPass(DataInputStream svdis, DataOutputStream svdos) throws IOException {

        String email = svdis.readUTF();
        String password = svdis.readUTF();
        System.out.println("Received forgot-pass data: " + email);
        boolean forgotPassSuccess = new SvForgotPass( password, email).SvForgotPass();
        svdos.writeBoolean(forgotPassSuccess);
        svdos.flush();
    }

    private static void handleSaveImage(DataInputStream svdis) {
        try {
            // Read the image length
            int imageLength = svdis.readInt();
            System.out.println("Image length: " + imageLength);

            // Read the image data
            byte[] receivedByteArray = new byte[imageLength];
            int totalBytesRead = 0;
            while (totalBytesRead < imageLength) {
                int bytesRead = svdis.read(receivedByteArray, totalBytesRead, imageLength - totalBytesRead);
                if (bytesRead == -1) {
                    throw new IOException("End of stream reached before reading the image completely");
                }
                totalBytesRead += bytesRead;
            }

            // Read the other details
            int ImgNumber = svdis.readInt();
            String RecipeName = svdis.readUTF();
            String UserName = svdis.readUTF();

            // Create the image object and set its properties
            ImagePK imagePK = new ImagePK();
            imagePK.setRecipeName(RecipeName);
            imagePK.setUserName(UserName);
            imagePK.setImgNumber(ImgNumber);
            Image image = new Image(imagePK);
            image.setImgUrl(receivedByteArray);

            // Output for debugging
            System.out.println();
            System.out.println(image.getImgNumber());
            System.out.println(image.getRecipeName());

            // Save the image
            new SvAddImage(image).SvAddImage();
            System.out.println("Image received and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (ServerSocket svSock = new ServerSocket(7400)) {
            System.out.println("Server started, waiting for clients...");
            while (true) {
                Socket socket = svSock.accept();
                System.out.println("Client connected!");
                connectedClients.add(socket);  // Add connected Socket to the list
                // Handle each client in a new thread
                new Thread(() -> {
                    handleClient(socket);
                    try {
                        socket.close();
                        connectedClients.remove(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
