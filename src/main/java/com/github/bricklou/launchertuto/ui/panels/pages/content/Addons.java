package com.github.bricklou.launchertuto.ui.panels.pages.content;

import com.github.bricklou.launchertuto.ui.PanelManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.flowarg.flowupdater.download.json.CurseFileInfo;

import fr.flowarg.flowupdater.download.json.Mod;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Addons extends ContentPanel {
    //private final Saver saver = Launcher.getInstance().getSaver()
    public final Path launcherDir = GameDirGenerator.createGameDir("Valarya", true);
    GridPane contentPane = new GridPane();

    Button tutosbtn = new Button("Infos Addons");

    CheckBox replaymod = new CheckBox("Replay Mod");
    CheckBox dyna = new CheckBox("Dynamic Surrounding");
    CheckBox controllable = new CheckBox("controllable");
    CheckBox wawla = new CheckBox("What Are we looking for");
    CheckBox betterFPS = new CheckBox("Better FPS");






    public final Saver saver = new Saver(Paths.get(launcherDir.toString(), "addons.properties"));

    public static List<Mod> modAddons = new ArrayList<>();


    public static List<CurseFileInfo> curseModAddons = new ArrayList<>();

    public String getName() {
        return "addons";
    }

    @Override
    public String getStylesheetPath() {
        return "css/content/addons.css";
    }


    @Override
    public void onShow() {
        super.onShow();
    }

    public void init(PanelManager panelManager) {

        super.init(panelManager);

        // Background
        this.layout.getStyleClass().add("settings-layout");
        this.layout.setPadding(new Insets(40));
        setCanTakeAllSize(this.layout);
        //   modAddons.add(new Mod("ffmpeg.exe", "60f19fc765fc005be713ffdff01b0f15a834ec6c", 78497792, "https://odysseyus.fr/ffmpeg.exe"));


        // Content
        contentPane.getStyleClass().add("content-pane");
        setCanTakeAllSize(contentPane);
        this.layout.getChildren().add(contentPane);

        // Titre
        Label title = new Label("Addons");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("addons-title");
        setLeft(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.LEFT);
        title.setTranslateY(10d);
        title.setTranslateX(10d);
        contentPane.getChildren().add(title);


        replaymod.getStyleClass().add("addons-mods");
        setLeft(replaymod);
        setTop(replaymod);
        replaymod.setTranslateX(10d);
        replaymod.setIndeterminate(false);
        replaymod.setTranslateY(60d);
        setCanTakeAllSize(replaymod);
        replaymod.setFont(Font.font("Verdana", FontPosture.REGULAR, 13f));
        replaymod.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new Mod("Replaymod.jar", "984489e9ec23c1b4e6c4ff5a00b35eef1be8dde8", "https://odysseyus.fr/mods/replaymod.jar", 13504148));
                System.out.println("ReplayMod");
                saver.set("replay", "true");
                saver.save();


            } else {

                modAddons.remove(new Mod("Replaymod.jar", "984489e9ec23c1b4e6c4ff5a00b35eef1be8dde8", "https://odysseyus.fr/mods/replaymod.jar", 13504148));
                System.out.println("ReplayMod remove");
                saver.remove("replay");
                saver.save();

            }

        });
        contentPane.getChildren().add(replaymod);
        replaymod.setSelected(Boolean.valueOf(saver.get("replay", "false")));


        dyna.getStyleClass().add("addons-mods");
        setLeft(dyna);
        setTop(dyna);
        dyna.setTranslateX(10d);
        dyna.setIndeterminate(false);
        dyna.setTranslateY(90d);
        setCanTakeAllSize(dyna);
        dyna.setFont(Font.font("Verdana", FontPosture.REGULAR, 13f));
        dyna.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                curseModAddons.add(new CurseFileInfo(307806, 2820815)); //OreLib
                curseModAddons.add(new CurseFileInfo(238891, 3497269)); //Dynamic

                System.out.println("Dynamic and OreLib");
                saver.set("dyna", "true");
                saver.save();


            } else {

                curseModAddons.remove(new CurseFileInfo(307806, 2820815)); //OreLib
                curseModAddons.remove(new CurseFileInfo(238891, 3497269)); //Dynamic
                System.out.println("Dynamic and OreLib remove");
                saver.remove("dyna");
                saver.save();

            }

        });
        contentPane.getChildren().add(dyna);
        dyna.setSelected(Boolean.valueOf(saver.get("dyna", "false")));




        controllable.getStyleClass().add("addons-mods");
        setLeft(controllable);
        setTop(controllable);
        controllable.setTranslateX(10d);
        controllable.setIndeterminate(false);
        controllable.setTranslateY(120d);
        setCanTakeAllSize(controllable);
        controllable.setFont(Font.font("Verdana", FontPosture.REGULAR, 13f));
        controllable.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                curseModAddons.add(new CurseFileInfo(317269, 3222475)); //controllable

                System.out.println("controllable");
                saver.set("controllable", "true");
                saver.save();

            } else {
                curseModAddons.remove(new CurseFileInfo(317269, 3222475)); //controllable
                System.out.println("controllable remove");
                saver.remove("controllable");
                saver.save();

            }

        });
        contentPane.getChildren().add(controllable);
        controllable.setSelected(Boolean.valueOf(saver.get("controllable", "false")));

        //Wawla
        wawla.getStyleClass().add("addons-mods");
        setLeft(wawla);
        setTop(wawla);
        wawla.setTranslateX(10d);
        wawla.setIndeterminate(false);
        wawla.setTranslateY(150d);
        setCanTakeAllSize(wawla);
        wawla.setFont(Font.font("Verdana", FontPosture.REGULAR, 13f));
        wawla.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                modAddons.add(new Mod("wawla.jar", "783157c607149875de1e045d72382d370256257c", "https://odysseyus.fr/mods/wawla.jar", 94115));

                System.out.println("controllable");
                saver.set("wawla", "true");
                saver.save();

            } else {
                modAddons.remove(new Mod("wawla.jar", "783157c607149875de1e045d72382d370256257c", "https://odysseyus.fr/mods/wawla.jar", 94115));
                System.out.println("wawla remove");
                saver.remove("wawla");
                saver.save();

            }

        });
        contentPane.getChildren().add(wawla);
        wawla.setSelected(Boolean.valueOf(saver.get("wawla", "false")));


        betterFPS.getStyleClass().add("addons-mods");
        setLeft(betterFPS);
        setTop(betterFPS);
        betterFPS.setTranslateX(10d);
        betterFPS.setIndeterminate(false);
        betterFPS.setTranslateY(180d);
        setCanTakeAllSize(betterFPS);
        betterFPS.setFont(Font.font("Verdana", FontPosture.REGULAR, 13f));
        betterFPS.selectedProperty().addListener((e, old, newValue) -> {
            if (newValue) {

                curseModAddons.add(new CurseFileInfo(229876, 2483393)); //controllable

                System.out.println("betterFPS");
                saver.set("betterFPS", "true");
                saver.save();

            } else {
                curseModAddons.remove(new CurseFileInfo(229876, 2483393)); //controllable
                System.out.println("betterFPS remove");
                saver.remove("betterFPS");
                saver.save();

            }

        });
        contentPane.getChildren().add(betterFPS);
        betterFPS.setSelected(Boolean.valueOf(saver.get("betterFPS", "false")));




        //Explications
        Label titleInfos = new Label("Informations");
        titleInfos.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 22));
        titleInfos.getStyleClass().add("addons-title");
        setLeft(titleInfos);
        setCanTakeAllSize(titleInfos);
        setTop(titleInfos);
        titleInfos.setTextAlignment(TextAlignment.LEFT);
        titleInfos.setTranslateY(250d);
        titleInfos.setTranslateX(10d);
        contentPane.getChildren().add(titleInfos);

        //Explications
        Label infosLabel = new Label("Ces mods ne sont pas nécessaires au fonctionnement d'Odysseyus et servent à améliorer\nvotre expérience de jeu.\nPour toutes informations : https://odysseyus.fr/addons");
        infosLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 14f));
        infosLabel.getStyleClass().add("addons-title");
        setLeft(infosLabel);
        setCanTakeAllSize(infosLabel);
        setTop(infosLabel);
        infosLabel.setTextAlignment(TextAlignment.LEFT);
        infosLabel.setTranslateY(275d);
        infosLabel.setTranslateX(10d);
        contentPane.getChildren().add(infosLabel);

        //boutons
        setCanTakeAllSize(tutosbtn);
        FontAwesomeIconView linkBtn = new FontAwesomeIconView(FontAwesomeIcon.LINK);
        tutosbtn.getStyleClass().add("play-btn");
        tutosbtn.setGraphic(linkBtn);
        setCenterV(tutosbtn);
        setCenterH(tutosbtn);
        setTop(tutosbtn);
        setLeft(tutosbtn);

        tutosbtn.setMaxWidth(150);
        tutosbtn.setTranslateX(10d);
        tutosbtn.setTranslateY(330d);
//50
        tutosbtn.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://odysseyus.fr/addons"));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        contentPane.getChildren().add(tutosbtn);



    }


}
