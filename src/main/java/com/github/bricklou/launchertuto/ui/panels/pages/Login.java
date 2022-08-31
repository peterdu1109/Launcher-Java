package com.github.bricklou.launchertuto.ui.panels.pages;

import com.github.bricklou.launchertuto.Launcher;
import com.github.bricklou.launchertuto.ui.PanelManager;
import com.github.bricklou.launchertuto.ui.panel.Panel;
import fr.theshark34.openlauncherlib.util.Saver;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import com.azuriom.azauth.AuthClient;
import com.azuriom.azauth.exception.AuthException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.naming.AuthenticationException;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends Panel {
    GridPane loginCard = new GridPane();

    Saver saver = Launcher.getInstance().getSaver();
    AtomicBoolean offlineAuth = new AtomicBoolean(false);

    TextField userField = new TextField();
    PasswordField passwordField = new PasswordField();
    Label userErrorLabel = new Label();
    Label passwordErrorLabel = new Label();
    Button btnLogin = new Button("Connexion");
    // CheckBox authModeChk = new CheckBox("Mode crack");



    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/login.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        // Background
        this.layout.getStyleClass().add("login-layout");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setMinWidth(350);
        columnConstraints.setMaxWidth(350);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());
        this.layout.add(loginCard, 0, 0);

        // Background image
        GridPane bgImage = new GridPane();
        setCanTakeAllSize(bgImage);
        bgImage.getStyleClass().add("bg-image");
        this.layout.add(bgImage, 1, 0);

        // Login card
        setCanTakeAllSize(this.layout);
        loginCard.getStyleClass().add("login-card");
        setLeft(loginCard);
        setCenterH(loginCard);
        setCenterV(loginCard);

        /*
         * Login sidebar
         */
        Label title = new Label("LauncherJava launcher");
        title.setFont(Font.font("ARIAL", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("login-title");
        setCenterH(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setTranslateY(30d);
        loginCard.getChildren().add(title);


        //E-Mail/Pseudo
        setCanTakeAllSize(userField);
        setCenterV(userField);
        setCenterH(userField);
        userField.setPromptText("Adresse E-Mail de LauncherJava");
        userField.setMaxWidth(300);
        userField.setTranslateY(-70d);
        userField.getStyleClass().add("login-input");
        userField.textProperty().addListener((_a, oldValue, newValue) ->
                this.updateLoginBtnState(userField, userErrorLabel));

        // User error
        setCanTakeAllSize(userErrorLabel);
        setCenterV(userErrorLabel);
        setCenterH(userErrorLabel);
        userErrorLabel.getStyleClass().add("login-error");
        userErrorLabel.setTranslateY(-45d);
        userErrorLabel.setMaxWidth(280);
        userErrorLabel.setTextAlignment(TextAlignment.LEFT);

        // Password
        setCanTakeAllSize(passwordField);
        setCenterV(passwordField);
        setCenterH(passwordField);
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setTranslateY(-15d);
        passwordField.getStyleClass().add("login-input");
        passwordField.textProperty().addListener((_a, oldValue, newValue) -> this.updateLoginBtnState(passwordField, passwordErrorLabel));


        // User error
        setCanTakeAllSize(passwordErrorLabel);
        setCenterV(passwordErrorLabel);
        setCenterH(passwordErrorLabel);
        passwordErrorLabel.getStyleClass().add("login-error");
        passwordErrorLabel.setTranslateY(10d);
        passwordErrorLabel.setMaxWidth(280);
        passwordErrorLabel.setTextAlignment(TextAlignment.LEFT);


        // Login button
        setCanTakeAllSize(btnLogin);
        setCenterV(btnLogin);
        setCenterH(btnLogin);
        btnLogin.setDisable(true);
        btnLogin.setMaxWidth(300);
        btnLogin.setTranslateY(40d);
        btnLogin.getStyleClass().add("login-log-btn");
        btnLogin.setOnMouseClicked(e -> {
            try {
                this.authenticate(userField.getText(), passwordField.getText());
            } catch (AuthenticationException | AuthException ex) {
                ex.printStackTrace();
            }
        });

        ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/logo.jpg"));
        imageView.setPreserveRatio(true);
        imageView.getStyleClass().add("ms-login-btn");
        imageView.setFitHeight(165);
        setLeft(imageView);
        setTop(imageView);
        imageView.setTranslateY(81);
        imageView.setTranslateX(90);
        imageView.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI("Url register"));
            }   catch (IOException | URISyntaxException ex) {

                ex.printStackTrace();
            }
        });

        //Explications
        Label inscritText1 = new Label("Pas encore inscrit ?");
        inscritText1.setFont(Font.font("Verdana", FontPosture.REGULAR, 12f));
        inscritText1.getStyleClass().add("info-inscription");
        setLeft(inscritText1);
        setCanTakeAllSize(inscritText1);
        setTop(inscritText1);
        inscritText1.setTextAlignment(TextAlignment.LEFT);
        inscritText1.setTranslateY(410);
        inscritText1.setTranslateX(27d);

        Label inscritText2 = new Label("Clique ici!");
        inscritText2.setFont(Font.font("Verdana", FontPosture.REGULAR, 12f));
        inscritText2.getStyleClass().add("info-inscription2");
        setLeft(inscritText2);
        setCanTakeAllSize(inscritText2);
        setTop(inscritText2);
        inscritText2.setTextAlignment(TextAlignment.LEFT);
        inscritText2.setTranslateY(410);
        inscritText2.setTranslateX(146d);
        inscritText2.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI("Url register"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });



/*
        Separator separator = new Separator();
        setCanTakeAllSize(separator);
        setCenterH(separator);
        setCenterV(separator);
        separator.getStyleClass().add("login-separator");
        separator.setMaxWidth(300);
        separator.setTranslateY(110d);
        //*login With* label
        Label loginWithLabel = new Label("Ou se connecter avec:".toUpperCase());
        setCanTakeAllSize(loginWithLabel);
        setCenterV(loginWithLabel);
        setCenterH(loginWithLabel);
        loginWithLabel.setFont(Font.font(loginWithLabel.getFont().getFamily(), FontWeight.BOLD, FontPosture.REGULAR, 14d));
        loginWithLabel.getStyleClass().add("login-with-label");
        loginWithLabel.setTranslateY(130d);
        loginWithLabel.setMaxWidth(280d);
     ImageView view = new ImageView(new Image("images/microsoft.png"));
        view.setPreserveRatio(true);
        view.setFitHeight(30d);
        setCanTakeAllSize(msLoginBtn);
        setCenterH(msLoginBtn);
        setCenterV(msLoginBtn);
        msLoginBtn.getStyleClass().add("ms-login-btn");
        msLoginBtn.setMaxWidth(300);
        msLoginBtn.setTranslateY(165d);
        msLoginBtn.setGraphic(view);
        msLoginBtn.setOnMouseClicked(e ->
                this.authenticateMS());
 */
        loginCard.getChildren().addAll(userField, userErrorLabel, passwordField, passwordErrorLabel, btnLogin, inscritText1, inscritText2, imageView);
    }

    public void updateLoginBtnState(TextField textField, Label errorLabel) {
        if (textField.getText().length() == 0) {
            errorLabel.setText("Le champ ne peut pas etre vide :/");
        } else {
            errorLabel.setText("");
        }

        btnLogin.setDisable(!(userField.getText().length() > 0 && (offlineAuth.get() || passwordField.getText().length() > 0)));
    }


    public void authenticate(String user, String password) throws AuthenticationException, AuthException {
        AuthClient authenticator = new AuthClient("url/api");


        try {

            AuthInfos infos = authenticator.login(user, password, () -> {
                // Called when 2FA is enabled
                String code = null;

                TextInputDialog inputdialog = new TextInputDialog("");
                inputdialog.setContentText("A2F: ");
                inputdialog.setHeaderText("A2F requis");
                inputdialog.show();


                while (code == null || code.isEmpty()) {



                    code = inputdialog.getEditor().getText();






                }

                return code;
            }, AuthInfos.class);


            saver.set("accessToken", infos.getAccessToken());
            saver.set("Username", infos.getUsername());
            saver.set("UUID", infos.getUuid());
            saver.save();

            Launcher.getInstance().setAuthInfos(infos);

            this.logger.info("Hello " + infos.getUsername());

            panelManager.showPanel(new App());


            //inputdialog.getEditor().getText()

        } catch(AuthException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur est survenu lors de la connexion");
            alert.setContentText(e.getMessage());
            alert.show();

        }
    }


    public TextField getUserField() {
        return userField;
    }
}