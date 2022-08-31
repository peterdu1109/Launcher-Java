package com.github.bricklou.launchertuto;

import com.azuriom.azauth.AuthClient;
import com.azuriom.azauth.exception.AuthException;
import com.github.bricklou.launchertuto.ui.PanelManager;
import com.github.bricklou.launchertuto.ui.panels.pages.App;
import com.github.bricklou.launchertuto.ui.panels.pages.Login;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


import java.nio.file.Path;
import java.nio.file.Paths;

public class Launcher extends Application {

    private PanelManager panelManager;
    private static Launcher instance;
    private final ILogger logger;
    public final Path launcherDir = GameDirGenerator.createGameDir("Tiranium", true);
    private final Saver saver;
    private AuthInfos authInfos = null;


    public Launcher() {
        instance = this;
        this.logger = new Logger("[Tiranium Launcher]", Paths.get(this.launcherDir.toString(), "launcher.log"));
        if (!this.launcherDir.toFile().exists()) {
            if (!this.launcherDir.toFile().mkdir()) {
                this.logger.err("Impossible de créer le dossier .Tiranium");
            }
        }

        saver = new Saver(Paths.get(launcherDir.toString(), "config.properties"));
        saver.load();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.logger.info("Starting Launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(new Login());

        if (this.isUserAlreadyLoggedIn()) {
            logger.info("Hello " + authInfos.getUsername());

            this.panelManager.showPanel(new App());

        } else {
            this.panelManager.showPanel(new Login());
        }

    }

    public boolean isUserAlreadyLoggedIn() {


        if (saver.get("accessToken") != null ) {

            AuthClient authenticator = new AuthClient("https://hopeful-kare.78-198-63-78.plesk.page");



            try {

                AuthInfos response = authenticator.verify(saver.get("accessToken"), AuthInfos.class);


                this.setAuthInfos(new AuthInfos(
                        response.getUsername(),
                        response.getAccessToken(),
                        response.getUuid()

                ));



                return true;
            } catch (AuthException ignored) {
                saver.remove("accessToken");
                saver.remove("clientToken");
                saver.save();
            }
        }

        return false;
    }



    public void setAuthInfos(AuthInfos authInfos) {
        this.authInfos = authInfos;
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
    }

    public ILogger getLogger() {
        return logger;
    }

    public static Launcher  getInstance() {
        return instance;
    }

    public Saver getSaver() {
        return saver;

    }

    public Path getLauncherDir() {
        return launcherDir;
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    public void hideWindow() {
        this.panelManager.getStage().hide();
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }

}