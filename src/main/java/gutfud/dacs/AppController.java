package gutfud.dacs;

import gutfud.dacs.BUS.*;
import gutfud.dacs.DTO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.controlsfx.control.cell.ImageGridCell;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.nio.file.Path;

public class AppController implements Initializable {
    public AppController() {
    }

    @FXML
    private CheckBox BeefCheckBox, PorkCheckBox, ChickenCheckBox, ShrimpCheckBox, EggCheckBox, RiceCheckBox, NoodlesCheckBox;
    @FXML
    private CheckBox TomatoCheckBox, ChilyCheckBox, BellPepperCheckBox, PotatoCheckBox, CarotCheckBox, OnionsCheckBox, MushroomCheckBox;
    @FXML
    private CheckBox GarlicCheckBox, LeafygreensCheckBox, OilCheckBox, BroccoliCheckBox, FishCheckBox;
    @FXML
    private Button IngreSearchBtt, ClearBtt, NameSearchBtt, SendButton,ChangeBackGroundBtt;
    @FXML
    private VBox UserListVbox, ChatVBOX;
    @FXML
    private TextField SearchTF;
    @FXML
    private BorderPane ChatPane;
    @FXML
    private ListView<Recipe> List;
    @FXML
    private ListView<String> StepLV;
    @FXML
    private ListView<String> IngreIV;
    @FXML
    private ListView MessLIstView;
    @FXML
    private ImageView UserAva,BackGroundIV, recipeimg, MakeRecipeIV, BigImageView, SmallUserAvatar, BigUserAvatar;
    @FXML
    private TextArea recipeTA, RecipeTA2, RecipeTA3, IngreTA, StepTA;
    @FXML
    private AnchorPane ViewPane, RecipeDetailPane, MyProfilePane;
    @FXML
    private GridPane RecipeContainer, SmallImgPane;
    @FXML
    private ScrollPane ViewRecipeDetailScrPane, MakeRecipeScrPane, ViewRecipeScrPane;
    @FXML
    private Button MyProfileBtt,ToFileBtt, ViewRecipebtt, DelRecipeBtt, RateBtt, SaveRecipeBtt, UnSaveRecipeBtt, ReportBtt, ChangeAvatarBtt, SaveBtt, CancelBtt, EditBtt;
    @FXML
    private TextField MessTF, IngreTF, StepTF, IngreQuantityTF, StepOrderTF, RecipeNameTF, RateTF, CmtTF, EmailTF, PhoneTF, UsernameTF;
    @FXML
    private ComboBox<String> TitleComboBox;
    @FXML
    private Label UserLBL, ImgNumlbl, RateLbl, RecipeNameLbl, NumOfSavedLbl, RecipeUserNamelbl, ReportedLbl, UserNameLbl, TitleLbl, NumOfSavedRecipeLbl, PhoneLbl, EmailLbl, DOBlbl;
    @FXML
    private VBox IngreVbox, StepVbox, CmtVbox;
    @FXML
    private DatePicker DOBDatepicker;



    private Stage stage;
    private Socket socket;
    private DataStreamManager manager;
    private Recipe Currrecipe;
    private User user;
    private String ChosedIgre;


    public AppController(DataStreamManager manager, User user) {
        this.manager = manager;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateGridPane("", "", "");
    }

    public void populateGridPane(String ChosedIngre, String ChosedName, String Chosedusername) {
        try {
            IngreSearchBtt.setDisable(false);
            NameSearchBtt.setDisable(false);
            ClearBtt.setDisable(false);
            RecipeContainer.getChildren().clear();
            IngreVbox.getChildren().clear();
            StepVbox.getChildren().clear();
            CmtVbox.getChildren().clear();

            System.out.println(user.toString());
            byte[] imgdata = user.getUserAvatarUrl();
            javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));
            SmallUserAvatar.setImage(img);

            ArrayList<Recipe> RecipeList = new ViewRecipe().ViewRecipe(manager);
            ArrayList<Recipe> NewRecipeList = new ArrayList<>();
            for (Recipe recipe : RecipeList) {
                {
                    if (!ChosedIngre.isEmpty()) {

                        ArrayList<Ingredient> ingrelist = new ViewIngre(manager).ViewIngre(recipe.getRecipeName(), recipe.getUserName());

                        for (Ingredient ingredient : ingrelist) {
                            if (ChosedIngre.toLowerCase().contains(ingredient.getIngreName().toLowerCase()) || ingredient.getIngredientName().equalsIgnoreCase(ChosedIngre)) {
                                System.out.println(recipe);
                                NewRecipeList.add(recipe);
                            }
                        }

                    } else if (!ChosedName.isEmpty()) {
                        if (recipe.getRecipeName().toLowerCase().contains(ChosedName.toLowerCase()) || ChosedName.toLowerCase().contains(recipe.getRecipeName().toLowerCase())) {
                            System.out.println(recipe);
                            NewRecipeList.add(recipe);
                        }
                    } else if (!Chosedusername.isEmpty()) {
                        if (recipe.getUserName().equals(Chosedusername)) {
                            System.out.println(recipe);
                            NewRecipeList.add(recipe);
                        }
                    } else {
                        System.out.println(recipe);
                        NewRecipeList.add(recipe);

                    }
                }

            }


            int col = 0;
            int row = 1;
            ArrayList<Image> imagesList = new ViewRecipe().ViewRecipeAva(manager);
            ArrayList<Image> NewimageList = new ArrayList<>();
            for (Image image : imagesList) {
                for (Recipe recipe : NewRecipeList) {
                    if (image.getRecipeName().equals(recipe.getRecipeName()) && image.getUserName().equals(recipe.getUserName())) {
                        NewimageList.add(image);
                    }
                }
            }

            if (NewimageList.size() == NewRecipeList.size()) {
                for (int i = 0; i < NewimageList.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Recipe.fxml"));
                    VBox recipe = loader.load();
                    RecipeForm recipeForm = loader.getController();
                    System.out.println(NewRecipeList.get(i));
                    System.out.println(NewimageList.get(i));
                    recipeForm.setData(NewRecipeList.get(i), NewimageList.get(i), user.getTitle());

                    int finalI = i;
                    recipe.setOnMouseClicked(event -> {
                        try {
                            Recipe clickedRecipe = NewRecipeList.get(finalI);
                            Currrecipe = clickedRecipe;
                            ViewRecipeDetailScrPane.setDisable(false);
                            ViewRecipeDetailScrPane.setVisible(true);
                            ViewRecipeScrPane.setVisible(false);
                            ViewRecipeScrPane.setDisable(true);


                            ArrayList<Image> imglist = new ViewImage(manager).ViewImage(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Rate> ratelist = new ViewRate(manager).ViewRate(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Ingredient> ingrelist = new ViewIngre(manager).ViewIngre(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Step> steplist = new ViewStep(manager).ViewStep(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Cmt> cmtlist = new ViewCmt(manager).ViewCmt(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());

                            this.ViewRecipeDetail(ingrelist, steplist, cmtlist, ratelist, clickedRecipe, imglist);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    });


                    if (col == 4) {
                        col = 0;
                        ++row;
                    }
                    RecipeContainer.add(recipe, col++, row);
                    GridPane.setMargin(recipe, new Insets(10));


                }
            } else {
                System.out.println(NewimageList.size());
                System.out.println(NewRecipeList.size());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void ErrorOccur(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Alert");
        alert.setHeaderText("Some error occur");
        alert.setContentText(error);

        ButtonType closeButton = new ButtonType("Close");
        alert.getButtonTypes().setAll(closeButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == closeButton) {
            // User clicked "Close"
            System.out.println("Alert closed.");
        }
    }
    @FXML
    public void ChangeBackGround(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files (*.png,*.jpg,*.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        File file = fileChooser.showOpenDialog(stage);

        byte[] fileBytes = new byte[(int) file.length()];
        try {
            fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
            System.out.println("File selected: " + file.getName());
            System.out.println("File size in bytes: " + fileBytes.length);
        } catch (IOException e1) {
            e1.printStackTrace();
            ErrorOccur("There something wrong happen try again later.");
        }
        user.setBackground(fileBytes);
        if (new ChangeBackGround(manager, user.getUserName(), user.getBackground()).ChangeBackGround()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                javafx.scene.image.Image image = new javafx.scene.image.Image(inputStream);
                BackGroundIV.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                ErrorOccur("There something wrong happen try again later.");
            }

        } else {
            ErrorOccur("There something wrong happen try again later.");
            System.out.println("Something wrong");
        }


    }

    @FXML
    public void ChangeAvatar(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files (*.png,*.jpg,*.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        File file = fileChooser.showOpenDialog(stage);

        byte[] fileBytes = new byte[(int) file.length()];
        try {
            fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
            System.out.println("File selected: " + file.getName());
            System.out.println("File size in bytes: " + fileBytes.length);
        } catch (IOException e1) {
            e1.printStackTrace();
            ErrorOccur("There something wrong happen try again later.");
        }
        user.setUserAvatarUrl(fileBytes);
        if (new ChangeAvatar(manager, user.getUserName(), user.getUserAvatarUrl()).ChangeAvatar()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                javafx.scene.image.Image image = new javafx.scene.image.Image(inputStream);
                SmallUserAvatar.setImage(image);
                BigUserAvatar.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                ErrorOccur("There something wrong happen try again later.");
            }

        } else {
            ErrorOccur("There something wrong happen try again later.");
            System.out.println("Something wrong");
        }


    }

    @FXML
    public void EditProfile(ActionEvent event) throws IOException {

        TitleLbl.setText("Title: ");

        PhoneLbl.setText("Phone: ");
        EmailLbl.setText("Email: ");
        DOBlbl.setText("Date of birth: ");


        TitleComboBox.setVisible(true);
        PhoneTF.setVisible(true);
        EmailTF.setVisible(true);
        DOBDatepicker.setVisible(true);
        PhoneTF.setVisible(true);
        EmailTF.setVisible(true);


        Date date = user.getDateOfBirth();

        DOBDatepicker.setVisible(true);
        DOBDatepicker.setValue(date.toLocalDate());


        TitleComboBox.getItems().clear();
        String[] title = {"Newbie", "Homecook", "Seasoned Cook", "Master Chef", "Executive Chef"};
        TitleComboBox.getItems().addAll(title);
        UsernameTF.setText(user.getUserName());
        PhoneTF.setText(user.getPhone());
        EmailTF.setText(user.getEmail());

        EditBtt.setVisible(false);
        SaveBtt.setVisible(true);
        CancelBtt.setVisible(true);
    }

    @FXML
    public void SaveProfile(ActionEvent event) throws IOException {
        LocalDate localDate = DOBDatepicker.getValue();
        String Title = TitleComboBox.getSelectionModel().getSelectedItem();
        if (Title == null
                || PhoneTF.getText() == null
                || EmailTF.getText() == null
                || localDate == null) {
            ErrorOccur("Empty information");
        } else {

            String OldUserName = user.getUserName();
            String Phone = PhoneTF.getText();
            String Email = EmailTF.getText();
            Date DOB = Date.valueOf(localDate);
            if (new ChangeProfile(OldUserName, Title, Phone, Email, DOB, manager).ChangeProfile()) {

                TitleLbl.setText("Title: " + user.getTitle());
                NumOfSavedRecipeLbl.setText("Number of recipe made: " + user.getNumOfRecipe());
                PhoneLbl.setText("Phone: " + user.getPhone());
                EmailLbl.setText("Email: " + user.getEmail());
                DOBlbl.setText("Date of birth: " + user.getDateOfBirth());

                UsernameTF.setVisible(false);
                TitleComboBox.setVisible(false);
                PhoneTF.setVisible(false);
                EmailTF.setVisible(false);
                DOBDatepicker.setVisible(false);
                PhoneTF.setVisible(false);
                EmailTF.setVisible(false);
                DOBDatepicker.setVisible(false);

                SaveBtt.setVisible(false);
                CancelBtt.setVisible(false);
            } else {
                System.out.println("Something wrong");
                ErrorOccur("There something wrong happen try again later.");
            }
        }


    }

    @FXML
    public void CancelSaveProfile(ActionEvent event) {
        UserNameLbl.setText("Username: " + user.getUserName());
        TitleLbl.setText("Title: " + user.getTitle());
        NumOfSavedRecipeLbl.setText("Number of recipe made: " + user.getNumOfRecipe());
        PhoneLbl.setText("Phone: " + user.getPhone());
        EmailLbl.setText("Email: " + user.getEmail());
        DOBlbl.setText("Date of birth: " + user.getDateOfBirth());


        UsernameTF.setVisible(false);
        TitleComboBox.setVisible(false);
        PhoneTF.setVisible(false);
        EmailTF.setVisible(false);
        DOBDatepicker.setVisible(false);
        PhoneTF.setVisible(false);
        EmailTF.setVisible(false);
        DOBDatepicker.setVisible(false);

        SaveBtt.setVisible(false);
        EditBtt.setVisible(true);
        CancelBtt.setVisible(false);
    }


    @FXML
    public void ViewRecipeDetail(ArrayList<Ingredient> ingredientList, ArrayList<Step> stepList, ArrayList<Cmt> cmtList
            , ArrayList<Rate> ratelist, Recipe recipe, ArrayList<Image> imglist) {

        IngreSearchBtt.setDisable(true);
        NameSearchBtt.setDisable(true);
        ClearBtt.setDisable(true);

        System.out.println(Currrecipe);
        if (!RateTF.getText().isEmpty()) {
            RateTF.setText("");
        }
        if (Currrecipe.getReported()) {
            ReportBtt.setVisible(false);
            ReportedLbl.setVisible(true);
        }
        if (Currrecipe.getUserName().equals(user.getUserName())) {

            DelRecipeBtt.setVisible(true);
            DelRecipeBtt.setOnAction(event -> {
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Do you want to proceed?");

                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");
                    alert.getButtonTypes().setAll(yesButton, noButton);

                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent()) {
                        if (result.get() == yesButton) {
                            System.out.println("User clicked Yes.");
                            if (new DelRecipe(user.getUserName(), recipe.getRecipeName(), manager).DelRecipe()) {
                                System.out.println("Deleted");
                                this.populateGridPane("","","");
                            } else {

                                System.out.println("Something wrong");
                                ViewRecipeScrPane.setVisible(true);
                                ViewRecipeDetailScrPane.setVisible(false);
                                ErrorOccur("Some thing wrong happen try again later");
                            }
                        } else if (result.get() == noButton) {
                            System.out.println("User clicked No.");
                            // Handle No button click logic here
                        }
                    } else {
                        // User closed the dialog without clicking a button
                        System.out.println("Alert closed without selection.");
                    }


                } catch (IOException e) {
                    ErrorOccur("Some thing wrong happen try again later");
                }


            });
        }
        ToFileBtt.setOnAction(event -> {

            String IngredientString ="";
            String StepString ="";
            String recipename = recipe.getRecipeName()+" ";
            String username ="Make by: " +recipe.getUserName();
            String Header = recipename + username ;

            String filePath = "D:\\BaiTap_Java\\Java_2";

            File file = new File(filePath, "test.txt");

            for(Ingredient ingredient : ingredientList) {
                IngredientString   = IngredientString +  ingredient.getIngredientName() + " Quantity: "+ingredient.getIngreQuantity() +"\n";
            }
            for(Step step : stepList) {

                StepString   = StepString +  step.getStepOrder() + " : "+step.getContent()+"\n";
            }

            try {
                FileWriter writer = new FileWriter(file);
                String content = Header  +"\n" +"Ingredient " + IngredientString +"\n" + "Step " + StepString;

                writer.write(content);

                writer.close();

                System.out.println("Text written successfully to file: " + filePath);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }



        });



        String ingre = "";
        for (Ingredient ingredient : ingredientList) {
            ingre = ingre + ingredient.getIngreName() + ": " + ingredient.getIngreQuantity() + '\n' + "  ";
        }

        Label ingrelbl = new Label(ingre);
        ingrelbl.setTextFill(Color.WHITE);
        IngreVbox.getChildren().add(ingrelbl);

        String step = " ";
        for (Step Step : stepList) {
            step = step + Step.getStepOrder() + ": " + Step.getContent() + '\n' + "  ";
        }
        Label steplbl = new Label(step);
        StepVbox.getChildren().add(steplbl);
        String cmt = "";
        for (Cmt Cmt : cmtList) {
            cmt = cmt + Cmt.getCmtUserName() + ": " + Cmt.getContent() + '\n';
        }
        Label cmtlbl = new Label(cmt);
        CmtVbox.getChildren().add(cmtlbl);

        long rate = 0;
        for (Rate Rate : ratelist) {
            rate = rate + Rate.getRate();
        }
        if (rate > 0) {
            rate = rate / ratelist.size();
        } else {
            rate = 0;
        }

        RateLbl.setText("Rated: " + rate + "/" + "10");
        RecipeNameLbl.setText(recipe.getRecipeName());
        RecipeUserNamelbl.setText("Make by " + user.getTitle() + ": " + Currrecipe.getUserName());
        NumOfSavedLbl.setText("Number of saved:" + recipe.getNumberOfSaved());
        int col = 0;
        int row = 1;
        for (Image img : imglist) {
            if (col == 6) {
                col = 0;
                ++row;
            }
            byte[] imgdata = img.getImgUrl();
            javafx.scene.image.Image image = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));

            BigImageView.setImage(image);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(false);
            SmallImgPane.add(imageView, col++, row);

            imageView.setOnMouseClicked(event -> {
                javafx.scene.image.Image selectedimg = imageView.getImage();
                BigImageView.setImage(selectedimg);
            });

        }

        try {
            boolean isSaved = new GetSavedRecipeDetails(user.getUserName(), Currrecipe.getRecipeName(), Currrecipe.getUserName(), manager).GetSavedRecipeDetails();
            if (isSaved) {
                System.out.println(isSaved);
                UnSaveRecipeBtt.setDisable(false);
                UnSaveRecipeBtt.setVisible(true);
                SaveRecipeBtt.setDisable(true);
                SaveRecipeBtt.setVisible(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
            ErrorOccur("There something wrong happen try again later.");
        }
        if (Currrecipe.getReported()) {
            ReportBtt.setVisible(false);
            ReportBtt.setDisable(true);
            ReportedLbl.setVisible(true);
        }

        RecipeUserNamelbl.setOnMouseClicked(mouseEvent -> {
            try {
                User ClickedUSer = new GetUser(manager, Currrecipe.getUserName()).GetUser();
                System.out.println(ClickedUSer);
                IngreSearchBtt.setDisable(true);
                NameSearchBtt.setDisable(true);
                MyProfilePane.setVisible(true);
                ViewRecipeScrPane.setVisible(false);
                MakeRecipeScrPane.setVisible(false);
                ViewRecipeDetailScrPane.setVisible(false);
                ChangeBackGroundBtt.setVisible(false);
                EditBtt.setVisible(false);
                ChangeAvatarBtt.setVisible(false);

                UserNameLbl.setText("Username: " + ClickedUSer.getUserName());
                TitleLbl.setText("Title: " + ClickedUSer.getTitle());
                NumOfSavedRecipeLbl.setText("Number of recipe made: " + ClickedUSer.getNumOfRecipe());
                PhoneLbl.setText("Phone: " + ClickedUSer.getPhone());
                EmailLbl.setText("Email: " + ClickedUSer.getEmail());
                DOBlbl.setText("Date of birth: " + ClickedUSer.getDateOfBirth());
                byte[] imgdata = ClickedUSer.getUserAvatarUrl();
                javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));
                BigUserAvatar.setImage(img);
                Circle clip = new Circle(100, 100, 100); // CenterX, CenterY, Radius

                clip.centerXProperty().bind(BigUserAvatar.fitWidthProperty().divide(2));
                clip.centerYProperty().bind(BigUserAvatar.fitHeightProperty().divide(2));
                clip.radiusProperty().bind(BigUserAvatar.fitWidthProperty().divide(2));

                // Set the clip on the ImageView
                BigUserAvatar.setClip(clip);
                byte[] imgbackdata = ClickedUSer.getBackground();
                javafx.scene.image.Image imgback = new javafx.scene.image.Image(new ByteArrayInputStream(imgbackdata));
                BackGroundIV.setImage(imgback);
            }
            catch (IOException e) {
                System.err.println("Error loading user information or images: " + e.getMessage());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });


    }


    @FXML
    public void UnSaveRecipe(ActionEvent event) throws IOException {
        boolean valid = new RemoveSavedRecipDetail(user.getUserName(), Currrecipe.getRecipeName(), Currrecipe.getUserName(), manager).RemoveSavedRecipDetail();
        if (valid) {
            SaveRecipeBtt.setVisible(true);
            SaveRecipeBtt.setDisable(false);
            UnSaveRecipeBtt.setDisable(true);
            UnSaveRecipeBtt.setVisible(false);

        } else {
            System.out.println("Something wrong");
            ErrorOccur("There something wrong happen try again later.");
        }

    }

    @FXML
    public void OpenMyprofilePane(ActionEvent event) {
        System.out.println(user);
        IngreSearchBtt.setDisable(true);
        NameSearchBtt.setDisable(true);
        MyProfilePane.setVisible(true);
        ViewRecipeScrPane.setVisible(false);
        MakeRecipeScrPane.setVisible(false);
        ViewRecipeDetailScrPane.setVisible(false);
        ChangeAvatarBtt.setVisible(true);
        ChangeBackGroundBtt.setVisible(true);
        EditBtt.setVisible(true);
        UserNameLbl.setText("Username: " + user.getUserName());
        TitleLbl.setText("Title: " + user.getTitle());
        NumOfSavedRecipeLbl.setText("Number of recipe made: " + user.getNumOfRecipe());
        PhoneLbl.setText("Phone: " + user.getPhone());
        EmailLbl.setText("Email: " + user.getEmail());
        DOBlbl.setText("Date of birth: " + user.getDateOfBirth());
        byte[] imgdata = user.getUserAvatarUrl();
        javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));
        BigUserAvatar.setImage(img);
        Circle clip = new Circle(100, 100, 100); // CenterX, CenterY, Radius

        clip.centerXProperty().bind(BigUserAvatar.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(BigUserAvatar.fitHeightProperty().divide(2));
        clip.radiusProperty().bind(BigUserAvatar.fitWidthProperty().divide(2));

        // Set the clip on the ImageView
        BigUserAvatar.setClip(clip);
        byte[] imgbackdata = user.getBackground();
        javafx.scene.image.Image imgback = new javafx.scene.image.Image(new ByteArrayInputStream(imgbackdata));
        BackGroundIV.setImage(imgback);
    }

    @FXML
    public void Report(ActionEvent event) throws IOException {
        boolean valid = new Report(Currrecipe.getUserName(), Currrecipe.getRecipeName(), manager).report();
        if (valid) {
            ReportBtt.setVisible(false);
            ReportedLbl.setVisible(true);
        } else {
            System.out.println("Something wrong");
            ErrorOccur("There something wrong happen try again later.");
        }
    }

    @FXML
    public void SaveRecipe(ActionEvent event) throws IOException {
        LocalDate date = LocalDate.now();
        SavedrecipedetailsPK savedrecipedetailsPK = new SavedrecipedetailsPK();
        savedrecipedetailsPK.setRecipeName(Currrecipe.getRecipeName());
        savedrecipedetailsPK.setUserName(user.getUserName());
        savedrecipedetailsPK.setRecipe_UserName(Currrecipe.getUserName());
        Savedrecipedetails savedrecipedetails = new Savedrecipedetails(savedrecipedetailsPK);
        savedrecipedetails.setDateSaved(Date.valueOf(date));
        boolean valid = new AddSavedRecipe(savedrecipedetails, manager).AddSavedRecipe();
        if (valid) {
            NumOfSavedLbl.setText("Number of saved:" + Currrecipe.getNumberOfSaved() + 1);
            UnSaveRecipeBtt.setDisable(false);
            UnSaveRecipeBtt.setVisible(true);
            SaveRecipeBtt.setDisable(true);
            SaveRecipeBtt.setVisible(false);
        } else {
            ErrorOccur("There something wrong happen try again later.");
        }

    }

    @FXML
    public void raterecipe(ActionEvent event) {
        if (RateTF.getText() == null) {
            System.out.println("Empty");
            ErrorOccur("Empty rate number");
        } else {
            try {
                LocalDate date = LocalDate.now();
                String rate = RateTF.getText();
                boolean done = new SaveRate(manager).SaveRate(Integer.parseInt(rate), Currrecipe.getRecipeName(), Currrecipe.getUserName(), user.getUserName(), Date.valueOf(date));
                if (done) {
                    System.out.println("done");
                    RateTF.setText("Your rate: " + Integer.parseInt(RateTF.getText()));
                } else {
                    System.out.println("Some thing wrong");
                }

            } catch (IOException e) {
                e.printStackTrace();
                ErrorOccur("There something wrong happen try again later.");
            }
        }


    }

    @FXML
    public void AddCmt(MouseEvent event) throws IOException {
        String cmtcontent = CmtTF.getText();
        System.out.println(Currrecipe.toString());
        CmtPK cmtPK = new CmtPK();
        cmtPK.setUserName(Currrecipe.getUserName());
        cmtPK.setRecipeName(Currrecipe.getRecipeName());
        cmtPK.setContent(cmtcontent);
        Cmt cmt = new Cmt(cmtPK);
        System.out.println(user.getUserName());
        cmt.setCmtUserName(user.getUserName());
        cmt.setDateCmt(Date.valueOf(LocalDate.now()));

        if (new AddCmt(manager).AddCmt(cmt)) {
            System.out.println(cmt.getContent());
            CmtTF.setText("");
            ArrayList<Cmt> Cmtlist = new ViewCmt(manager).ViewCmt(Currrecipe.getRecipeName(), Currrecipe.getUserName());
            String Cmtcontent = "";
            for (Cmt Cmt : Cmtlist) {
                Cmtcontent = Cmtcontent + Cmt.getCmtUserName() + ": " + Cmt.getContent() + '\n';
            }
            Label cmtlbl = new Label(Cmtcontent);
            CmtVbox.getChildren().clear();
            CmtVbox.getChildren().add(cmtlbl);

        } else {
            System.out.println("Something went wrong");
            ErrorOccur("There something wrong happen try again later.");
        }

    }


    @FXML
    public void OpenMakeRecipeSrcPane(ActionEvent event) {
        ViewRecipeScrPane.setVisible(false);
        MakeRecipeScrPane.setVisible(true);
        MakeRecipeScrPane.setDisable(false);
        MyProfilePane.setVisible(false);
        IngreSearchBtt.setDisable(true);
        NameSearchBtt.setDisable(true);
        ClearBtt.setDisable(true);

    }

    @FXML
    public void OpenViewRecipeSrcPane(ActionEvent event) {
        IngreSearchBtt.setDisable(true);
        NameSearchBtt.setDisable(true);
        ClearBtt.setDisable(true);
        MakeRecipeScrPane.setVisible(false);
        MakeRecipeScrPane.setDisable(true);
        ViewRecipeScrPane.setVisible(true);
        ViewRecipeScrPane.setDisable(false);
        Currrecipe = new Recipe();
        MyProfilePane.setVisible(false);
        populateGridPane("", "", "");

    }

    String Ingre = "";
    ArrayList<String> IngreList = new ArrayList<>();
    ArrayList<String> QuantityList = new ArrayList<>();
    ;
    ArrayList<String> StepList = new ArrayList<>();
    ArrayList<String> OrderList = new ArrayList<>();
    ;
    String step = "";
    ArrayList<File> imgfile = new ArrayList<>();


    // object
    ArrayList<Image> imgList = new ArrayList<>();
    ArrayList<Step> stepList = new ArrayList<>();
    ArrayList<Ingredient> ingreList = new ArrayList<>();
    int currentIndex;

    @FXML
    public void ChoseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files (*.png,*.jpg,*.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        File file = fileChooser.showOpenDialog(stage);
        byte[] fileBytes = new byte[(int) file.length()];
        try {
            fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
            System.out.println("File selected: " + file.getName());
            System.out.println("File size in bytes: " + fileBytes.length);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ImagePK imagePK = new ImagePK();
        imagePK.setImgNumber(currentIndex);
        imagePK.setUserName(user.getUserName());
        imagePK.setRecipeName(RecipeNameTF.getText());
        Image img = new Image(imagePK);
        img.setImgUrl(fileBytes);


        imgfile.add(file);


        MakeRecipeIV.setImage(new javafx.scene.image.Image(file.toURI().toString()));

        imgList.add(img);
        ImgNumlbl.setText("Image : " + currentIndex);
        currentIndex++;
    }

    @FXML
    public void BackImg(ActionEvent event) {
        if (currentIndex > 0) {

            currentIndex--;
            File imgFile = imgfile.get(currentIndex);
            try {
                javafx.scene.image.Image image = new javafx.scene.image.Image(imgFile.toURI().toString());
                MakeRecipeIV.setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading image: " + imgFile.getName());
            }
        }
    }

    @FXML
    public void NextImg(ActionEvent event) {
        if (currentIndex < imgfile.size() - 1) {
            currentIndex++;
            File imgFile = imgfile.get(currentIndex);
            try {
                javafx.scene.image.Image image = new javafx.scene.image.Image(imgFile.toURI().toString());
                MakeRecipeIV.setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading image: " + imgFile.getName());
            }
        }
    }

    @FXML
    public void EditImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files (*.png, *.jpg, *.jpeg)", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                javafx.scene.image.Image newImage = new javafx.scene.image.Image(selectedFile.toURI().toString());


                byte[] newfileBytes = new byte[(int) selectedFile.length()];
                try {
                    newfileBytes = Files.readAllBytes(Paths.get(selectedFile.getPath()));
                    System.out.println("File selected: " + selectedFile.getName());
                    System.out.println("File size in bytes: " + newfileBytes.length);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ImagePK imagePK = new ImagePK();
                imagePK.setImgNumber(currentIndex);
                imagePK.setUserName(user.getUserName());
                imagePK.setRecipeName(RecipeNameTF.getText());
                Image img = new Image(imagePK);
                img.setImgUrl(newfileBytes);

                if (currentIndex < imgfile.size()) { // Check if index is valid
                    imgList.set(currentIndex, img); // Update image at current index
                }

                imgfile.set(currentIndex, selectedFile);
                MakeRecipeIV.setImage(newImage);
            } catch (Exception e) {
                System.out.println("Error loading image: " + selectedFile.getName());
            }
        }
    }

    @FXML
    public void AddIngreToTA(ActionEvent event) {
        Ingre = IngreTF.getText() + ": " + IngreQuantityTF.getText() + '\n';
        IngredientPK ingredientPK = new IngredientPK();
        ingredientPK.setUserName(user.getUserName());
        ingredientPK.setRecipeName(RecipeNameTF.getText());
        Ingredient ingredient = new Ingredient(ingredientPK);
        ingredient.setIngredientName(IngreTF.getText());
        ingredient.setIngreQuantity(IngreQuantityTF.getText());

        ingreList.add(ingredient);
        IngreIV.getItems().add(Ingre);
        IngreTF.setText("");
        IngreQuantityTF.setText("");
    }


    @FXML
    public void ClearStep() throws IOException {
        StepOrderTF.setText("");
        StepTF.setText("");
        StepLV.getItems().clear();
        stepList.clear();
        if (stepList.size() == 0) {
            System.out.println("Deleted");
        }

    }

    @FXML
    public void ClearIngre() throws IOException {
        IngreTF.setText("");
        IngreQuantityTF.setText("");
        IngreIV.getItems().clear();
        ingreList.clear();
        if (ingreList.size() == 0) {
            System.out.println("Deleted");
        }

    }

    @FXML
    public void Clearimg() throws IOException {
        currentIndex = 0;
        ImgNumlbl.setText("Image: ");
        imgList.clear();
        if (imgList.size() == 0) {
            System.out.println("Deleted");
        }

    }

    @FXML
    public void AddStepToTA(ActionEvent event) {
        step = StepOrderTF.getText() + ": " + StepTF.getText() + '\n';

        StepPK stepPK = new StepPK();
        stepPK.setStepOrder(Integer.parseInt((StepOrderTF.getText())));
        stepPK.setUserName(user.getUserName());
        stepPK.setRecipeName(RecipeNameTF.getText());
        Step Step = new Step(stepPK);
        Step.setContent(StepTF.getText());

        StepLV.getItems().add(step);
        stepList.add(Step);
        StepTF.setText("");
        StepOrderTF.setText("");
        System.out.println(stepList.get(0).toString());
    }

    @FXML
    public void EditStep(MouseEvent event) {
        ListView<String> listView = (ListView<String>) event.getSource();
        int selectedindex = listView.getSelectionModel().getSelectedIndex();
        String step = StepOrderTF.getText() + ": " + StepTF.getText() + '\n';
        StepList.set(selectedindex, StepTF.getText());
        OrderList.set(selectedindex, StepOrderTF.getText());
        StepLV.getItems().set(selectedindex, step);
        StepTF.setText("");
        StepOrderTF.setText("");
    }

    @FXML
    public void EditIngre(MouseEvent event) {
        ListView<String> listView = (ListView<String>) event.getSource();
        int selectedindex = listView.getSelectionModel().getSelectedIndex();
        String Ingre = IngreTF.getText() + ": " + IngreQuantityTF.getText() + '\n';
        IngreList.set(selectedindex, IngreTF.getText());
        QuantityList.set(selectedindex, IngreQuantityTF.getText());
        IngreIV.getItems().set(selectedindex, Ingre);
        IngreTF.setText("");
        IngreQuantityTF.setText("");
    }

    @FXML
    public void SendRecipe(ActionEvent event) {
        try {
            if (new AddRecipe(RecipeNameTF.getText(), user.getUserName(), manager).AddRecipe()) {
                user.setNumOfRecipe(user.getNumOfRecipe() + 1);
            }
            new AddIngre(ingreList, manager).AddIngre();
            new AddStep(stepList, manager).AddStep();
            new AddImage(imgList, manager).sendImage();

            MakeRecipeScrPane.setVisible(false);
            MakeRecipeScrPane.setDisable(true);
            ViewRecipeScrPane.setVisible(true);
            ViewRecipeScrPane.setDisable(false);
            Currrecipe = new Recipe();
            MyProfilePane.setVisible(false);
            populateGridPane("", "", "");

            this.Clear();
            ingreList.clear();
            stepList.clear();
            imgList.clear();
            MakeRecipeIV.setImage(new javafx.scene.image.Image("D:\\G01.jpg"));

        } catch (Exception e) {
            this.ErrorOccur("Can't post your recipe try again");
        }

    }

    @FXML
    public void Clear() {
        try {
            this.Clearimg();
            this.ClearStep();
            this.ClearIngre();
            ingreList.clear();
            stepList.clear();
            imgList.clear();
            RecipeNameTF.setText("");
            MakeRecipeIV.setImage(new javafx.scene.image.Image("D:\\G01.jpg"));
        } catch (Exception e) {
            this.ErrorOccur("Something wrong try restart");
        }

    }

    @FXML
    public void settext(MouseEvent event) {
        if (!RateTF.getText().isEmpty()) {
            RateTF.setText("");
        }
    }

    @FXML
    public void ClearCheckBox(ActionEvent event) {
        ChickenCheckBox.setSelected(false);
        BeefCheckBox.setSelected(false);
        PorkCheckBox.setSelected(false);
        ShrimpCheckBox.setSelected(false);
        EggCheckBox.setSelected(false);
        RiceCheckBox.setSelected(false);
        NoodlesCheckBox.setSelected(false);
        FishCheckBox.setSelected(false);
        TomatoCheckBox.setSelected(false);
        ChilyCheckBox.setSelected(false);
        BellPepperCheckBox.setSelected(false);
        PotatoCheckBox.setSelected(false);
        CarotCheckBox.setSelected(false);
        OnionsCheckBox.setSelected(false);
        MushroomCheckBox.setSelected(false);
        GarlicCheckBox.setSelected(false);
        LeafygreensCheckBox.setSelected(false);
        OilCheckBox.setSelected(false);
        BroccoliCheckBox.setSelected(false);
    }

    @FXML
    public void SearchRecipeByCheckBox(ActionEvent event) {

        ChosedIgre = "";
        ArrayList<String> ChosedIngreList = new ArrayList<>();
        if (ChickenCheckBox.isSelected()) {
            ChosedIngreList.add(ChickenCheckBox.getText());
        }
        if (BeefCheckBox.isSelected()) {
            ChosedIngreList.add(BeefCheckBox.getText());
        }
        if (PorkCheckBox.isSelected()) {
            ChosedIngreList.add(PorkCheckBox.getText());
        }
        if (ShrimpCheckBox.isSelected()) {
            ChosedIngreList.add(ShrimpCheckBox.getText());
        }
        if (EggCheckBox.isSelected()) {
            ChosedIngreList.add(EggCheckBox.getText());
        }
        if (RiceCheckBox.isSelected()) {
            ChosedIngreList.add(RiceCheckBox.getText());
        }
        if (NoodlesCheckBox.isSelected()) {
            ChosedIngreList.add(NoodlesCheckBox.getText());
        }
        if (FishCheckBox.isSelected()) {
            ChosedIngreList.add(FishCheckBox.getText());
        }
        if (TomatoCheckBox.isSelected()) {
            ChosedIngreList.add(TomatoCheckBox.getText());
        }
        if (ChilyCheckBox.isSelected()) {
            ChosedIngreList.add(ChilyCheckBox.getText());
        }
        if (BellPepperCheckBox.isSelected()) {
            ChosedIngreList.add(BeefCheckBox.getText());
        }
        if (PotatoCheckBox.isSelected()) {
            ChosedIngreList.add(PotatoCheckBox.getText());
        }
        if (CarotCheckBox.isSelected()) {
            ChosedIngreList.add(CarotCheckBox.getText());
        }
        if (OnionsCheckBox.isSelected()) {
            ChosedIngreList.add(OnionsCheckBox.getText());
        }
        if (MushroomCheckBox.isSelected()) {
            ChosedIngreList.add(MushroomCheckBox.getText());
        }
        if (GarlicCheckBox.isSelected()) {
            ChosedIngreList.add(GarlicCheckBox.getText());
        }
        if (LeafygreensCheckBox.isSelected()) {
            ChosedIngreList.add(LeafygreensCheckBox.getText());
        }
        if (OilCheckBox.isSelected()) {
            ChosedIngreList.add(OilCheckBox.getText());
        }
        if (BroccoliCheckBox.isSelected()) {
            ChosedIngreList.add(BroccoliCheckBox.getText());
        }
        for (String ingre : ChosedIngreList) {
            ChosedIgre = ChosedIgre + ingre;
        }
        populateGridPane(ChosedIgre, "", "");
    }

    @FXML
    public void SearchName(ActionEvent event) {
        String SearchName = SearchTF.getText();
        if (SearchName == null) {
            System.out.println("Empty TF");
        } else {
            populateGridPane("", SearchName, "");
        }
    }

    @FXML
    public void ViewMyRecipe(ActionEvent event) {
        MakeRecipeScrPane.setVisible(false);
        MakeRecipeScrPane.setDisable(true);
        ViewRecipeScrPane.setVisible(true);
        ViewRecipeScrPane.setDisable(false);
        Currrecipe = new Recipe();
        populateGridPane("", "", user.getUserName());
        MyProfilePane.setVisible(false);

    }

    @FXML
    public void ViewSavedRecipe(ActionEvent ev) {
        if (!RateTF.getText().isEmpty()) {
            RateTF.setText("");
        }
        MakeRecipeScrPane.setVisible(false);
        MakeRecipeScrPane.setDisable(true);
        ViewRecipeScrPane.setVisible(true);
        ViewRecipeScrPane.setDisable(false);
        Currrecipe = new Recipe();
        MyProfilePane.setVisible(false);
        try {
            RecipeContainer.getChildren().clear();
            IngreVbox.getChildren().clear();
            StepVbox.getChildren().clear();
            CmtVbox.getChildren().clear();
            System.out.println(user.toString());
            byte[] imgdata = user.getUserAvatarUrl();
            javafx.scene.image.Image img = new javafx.scene.image.Image(new ByteArrayInputStream(imgdata));
            SmallUserAvatar.setImage(img);

            ArrayList<Recipe> RecipeList = new GetSavedRecipe(user.getUserName(), manager).ViewSavedRecipe();
            ArrayList<Recipe> NewRecipeList = new ArrayList<>();
            int col = 0;
            int row = 1;
            ArrayList<Image> imagesList = new ViewRecipe().ViewRecipeAva(manager);
            ArrayList<Image> NewimageList = new ArrayList<>();

            for (Image image : imagesList) {
                for (Recipe recipe : RecipeList) {
                    if (image.getRecipeName().equals(recipe.getRecipeName()) && image.getUserName().equals(recipe.getUserName())) {
                        NewimageList.add(image);
                        NewRecipeList.add(recipe);
                    }
                }
            }

            if (NewimageList.size() == NewRecipeList.size()) {
                for (int i = 0; i < NewimageList.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Recipe.fxml"));
                    VBox recipe = loader.load();
                    RecipeForm recipeForm = loader.getController();
                    System.out.println(NewRecipeList.get(i));
                    System.out.println(NewimageList.get(i));
                    recipeForm.setData(NewRecipeList.get(i), NewimageList.get(i), user.getTitle());

                    int finalI = i;
                    recipe.setOnMouseClicked(event -> {
                        try {

                            Recipe clickedRecipe = NewRecipeList.get(finalI);
                            Currrecipe = clickedRecipe;
                            ViewRecipeDetailScrPane.setDisable(false);
                            ViewRecipeDetailScrPane.setVisible(true);
                            ViewRecipeScrPane.setVisible(false);
                            ViewRecipeScrPane.setDisable(true);


                            ArrayList<Image> imglist = new ViewImage(manager).ViewImage(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Rate> ratelist = new ViewRate(manager).ViewRate(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Ingredient> ingrelist = new ViewIngre(manager).ViewIngre(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Step> steplist = new ViewStep(manager).ViewStep(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());
                            ArrayList<Cmt> cmtlist = new ViewCmt(manager).ViewCmt(clickedRecipe.getRecipeName(), clickedRecipe.getUserName());

                            this.ViewRecipeDetail(ingrelist, steplist, cmtlist, ratelist, clickedRecipe, imglist);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    });


                    if (col == 3) {
                        col = 0;
                        ++row;
                    }
                    RecipeContainer.add(recipe, col++, row);
                    GridPane.setMargin(recipe, new Insets(10));


                }
            } else {
                System.out.println(NewimageList.size());
                System.out.println(NewRecipeList.size());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void OpenChatPane() throws IOException {
        MakeRecipeScrPane.setVisible(false);
        MakeRecipeScrPane.setDisable(false);
        ViewRecipeScrPane.setVisible(false);
        ViewRecipeScrPane.setDisable(false);
        MyProfilePane.setVisible(false);


        ArrayList<User> userlist = new ViewUsers(manager).ViewUsers();
        userlist.removeIf(users -> users.getUserName().equalsIgnoreCase(user.getUserName()));

        User CurrUser = new User();
        for (User ClickedUser : userlist) {
            System.out.println("User: " + ClickedUser);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("User.fxml"));
            HBox UserHbox = loader.load();
            UserForm userForm = loader.getController();
            System.out.println(userForm);
            userForm.setData(ClickedUser);
            UserListVbox.getChildren().add(UserHbox);

            UserHbox.setOnMouseClicked(mouseEvent -> {

            });

        }

    }
}