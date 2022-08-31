package com.github.bricklou.launchertuto.ui.panels.pages.content;

import com.azuriom.azauth.AuthClient;
import com.azuriom.azauth.exception.AuthException;
import com.github.bricklou.launchertuto.ui.PanelManager;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import re.alwyn974.minecraftserverping.MinecraftServerPing;
import re.alwyn974.minecraftserverping.MinecraftServerPingInfos;
import re.alwyn974.minecraftserverping.MinecraftServerPingOptions;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Infos extends ContentPanel {

    GridPane contentPane = new GridPane();
    public final Path launcherDir = GameDirGenerator.createGameDir("Tiranium", true);
    private Saver saver;



    public String getName() {
        return "infos";
    }

    @Override
    public String getStylesheetPath() {
        return "css/content/infos.css";
    }


    @Override
    public void onShow() {
        super.onShow();
    }





    public void init(PanelManager panelManager) {
        super.init(panelManager);

        saver = new Saver(Paths.get(launcherDir.toString(), "config.properties"));
        saver.load();

        try {
            MinecraftServerPingInfos data = new MinecraftServerPing().getPing(new MinecraftServerPingOptions().setHostname("play.valaron.fr").setPort(25565));
            AuthClient authenticator = new AuthClient("https://valarya.valaron.fr");

            AuthInfos response = authenticator.verify(saver.get("accessToken"), AuthInfos.class);



            // Background
            this.layout.getStyleClass().add("infos-layout");
            this.layout.setPadding(new Insets(40));
            setCanTakeAllSize(this.layout);

            // Content
            contentPane.getStyleClass().add("content-pane");
            setCanTakeAllSize(contentPane);
            this.layout.getChildren().add(contentPane);

            // Titre
            Label title = new Label("Infos");
            title.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 30f));
            title.getStyleClass().add("infos-title");
            setLeft(title);
            setCanTakeAllSize(title);
            setTop(title);
            title.setTextAlignment(TextAlignment.LEFT);
            title.setTranslateY(10d);
            title.setTranslateX(10d);
            contentPane.getChildren().add(title);

            Label infosServeur = new Label("Valarya");
            infosServeur.setFont(Font.font("Helvetica", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 25f));
            infosServeur.getStyleClass().add("infos-title");
            setLeft(infosServeur);
            setTop(infosServeur);
            setCanTakeAllSize(infosServeur);
            setTop(infosServeur);
            infosServeur.setTextAlignment(TextAlignment.LEFT);
            infosServeur.setTranslateY(70d);
            infosServeur.setTranslateX(10d);
            contentPane.getChildren().add(infosServeur);


            Label joueursCo = new Label("Joueurs connecté(s) : " + data.getPlayers().getOnline() + "/" + data.getPlayers().getMax());
            joueursCo.setFont(Font.font("Consolas", FontPosture.REGULAR, 18f));
            joueursCo.getStyleClass().add("infos-title");
            setLeft(joueursCo);
            setTop(joueursCo);
            setCanTakeAllSize(joueursCo);
            setTop(joueursCo);
            joueursCo.setTextAlignment(TextAlignment.LEFT);
            joueursCo.setTranslateY(95d);
            joueursCo.setTranslateX(10d);
            contentPane.getChildren().add(joueursCo);





        } catch (IOException | AuthException e) {
            e.printStackTrace();
        }




    }


}